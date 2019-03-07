package com.broada.one.controller;

import com.broada.one.data.vo.response.A002_Response;
import com.broada.one.data.vo.response.A003_Response;
import com.broada.one.service.inf.ComputerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "统计计算机的各项数据", tags = {"统计计算机的各项数据"})
@RestController
@RequestMapping("/computer")
public class ComputerController {

    @Autowired
    private ComputerService computerService;

    static Logger logger = LogManager.getLogger(EmpController.class);

    @ApiOperation(value = "查询最后一次的数据", notes = "")
    @RequestMapping(value = "/findLast", method = RequestMethod.GET)
    public A002_Response findLast() {
        A002_Response outVo = new A002_Response();
        try {
            outVo = computerService.findLastInfo();
        } catch (Exception e) {
            logger.error("查询最后一次计算机信息失败");
        }
        return outVo;
    }

    @ApiOperation(value = "查询最后5次的相关数据", notes = "")
    @RequestMapping(value = "/findLast5", method = RequestMethod.GET)
    public A003_Response findLast5() {
        A003_Response outVo = new A003_Response();
        try {
            outVo = computerService.findLast5();
        } catch (Exception e) {
            logger.error("查询最后5次计算机信息失败");
        }
        return outVo;
    }

}
