package com.broada.one.service.impl;

import com.broada.one.controller.EmpController;
import com.broada.one.data.vo.response.A002_Response;
import com.broada.one.data.vo.response.A003_Response;
import com.broada.one.data.domain.ComputerInfo;
import com.broada.one.service.inf.ComputerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hyperic.sigar.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ComputerServiceImpl implements ComputerService {

    @Autowired
    private MongoTemplate template;

    static Logger logger = LogManager.getLogger(EmpController.class);

    /**
     * 定时存储电脑相关信息
     */
    @Scheduled(fixedRate = 5000)
    private void storeComputerInfo() throws SigarException {
        Sigar sigar = new Sigar();
        CpuPerc cpuPerc = sigar.getCpuPerc();
        ComputerInfo computer = new ComputerInfo();
        computer.setCpuCombined(cpuPerc.getCombined());
        computer.setCpuUser(cpuPerc.getUser());
        computer.setCpuSys(cpuPerc.getSys());
        computer.setCpuWait(cpuPerc.getWait());
        computer.setCpuIdle(cpuPerc.getIdle());

        // 内存信息
        Mem mem = sigar.getMem();
        computer.setMemTotal(mem.getTotal());
        computer.setMemUsed(mem.getUsed());
        computer.setMemFree(mem.getFree());


        // 文件系统
        Map<String, Object> fileSysMsgMap = new HashMap<>();
        FileSystem[] list = sigar.getFileSystemList();
        fileSysMsgMap.put("FileSysCount", list.length);
        List<String> msgList = null;
        for (FileSystem fs : list) {
            msgList = new ArrayList<>();
            msgList.add(fs.getDevName() + "总大小:    " + sigar.getFileSystemUsage(fs.getDirName()).getTotal() + "KB");
            msgList.add(fs.getDevName() + "剩余大小:    " + sigar.getFileSystemUsage(fs.getDirName()).getFree() + "KB");
            fileSysMsgMap.put(fs.getDevName(), msgList);
        }
        computer.setFileSysMsgMap(fileSysMsgMap);
        template.save(computer);

        logger.info("完成一次储存计算机信息,computerInfo:" + computer.toString());
    }

    @Override
    public A002_Response findLastInfo() {
        A002_Response outVo = new A002_Response();
        //新增查询条件
        Query query = new Query();

        query.with(new Sort(new Sort.Order(Sort.Direction.DESC, "_id"), new Sort.Order(Sort.Direction.DESC, "-1")));
        ComputerInfo info = template.findOne(query, ComputerInfo.class);
        BeanUtils.copyProperties(info, outVo);
        return outVo;
    }

    @Override
    public A003_Response findLast5() {
        A003_Response outVo = new A003_Response();
        NumberFormat per = NumberFormat.getPercentInstance();
        double cpuSumCombined = 0;
        double cpuSumIlde = 0;
        //新增查询条件
        Query query = new Query();
        query.with(new Sort(new Sort.Order(Sort.Direction.DESC, "_id"), new Sort.Order(Sort.Direction.DESC, "-1"))).limit(5);
        List<ComputerInfo> list = template.find(query, ComputerInfo.class);
        for (ComputerInfo info : list) {
            cpuSumCombined += info.getCpuCombined();
            cpuSumIlde += info.getCpuIdle();
        }
        ComputerInfo lastComputer = list.get(4);
        BeanUtils.copyProperties(lastComputer,outVo);

        String cpuCombinedStr = per.format(cpuSumCombined / (list.size()));
        String cpuIdleStr = per.format(cpuSumIlde / (list.size()));
        outVo.setCpuAvgCombined(cpuCombinedStr);
        outVo.setCpuAvgIdle(cpuIdleStr);

        return outVo;
    }
}
