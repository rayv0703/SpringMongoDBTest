package com.broada.one.controller;

import com.broada.one.data.vo.request.A001_Request;
import com.broada.one.data.vo.response.A001_Response;
import com.broada.one.data.vo.request.A002_Request;
import com.broada.one.data.domain.EmpInf;
import com.broada.one.service.inf.EmpService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "使用MongoDB进行简单的增删改查", tags = {"使用MongoDB进行简单的增删改查"})
@RestController
@RequestMapping("/emp")
public class EmpController {

    @Autowired
    private EmpService empService;

    //private static Logger logger = LoggerFactory.getLogger(EmpController.class);

    static Logger logger = LogManager.getLogger(EmpController.class);


    @ApiOperation(value = "新增员工信息", notes = "")
    @PostMapping(value = "")
    public void saveEmpInf(@RequestBody EmpInf emp) {
        try {
            empService.save(emp);
            //System.out.println(emp.toString());
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
    }

    @ApiOperation(value = "根据ID查询员工", notes = "")
    @GetMapping(value = "/{id}")
    public EmpInf findOne(@PathVariable long id) {
        EmpInf emp = new EmpInf();

        try {
            emp = empService.findOne(id);
            logger.info(emp.toString());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("通过ID查询失败");
        }
        return emp;
    }

    @ApiOperation(value = "修改员工信息", notes = "")
    @PutMapping(value = "")
    public void updateEmp(@RequestBody EmpInf emp) {
        try {
            empService.update(emp);
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
    }

    @ApiOperation(value = "通过ID删除员工信息", notes = "")
    @DeleteMapping(value = "/{id}")
    public void deleteById(@PathVariable long id) {
        try {
            empService.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
    }
    @ApiOperation(value = "进行复杂查询",notes = "")
    @RequestMapping(value = "/queryListByPage",method = RequestMethod.POST)
    public A001_Response queryListByPage(@RequestBody A001_Request request) {
        A001_Response outVo = new A001_Response();
        try {
            outVo = empService.queryListByPage(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return outVo;
    }
    @ApiOperation(value = "批量进行添加员工信息",notes = "")
    @RequestMapping(value = "saveEmpInfoBt",method = RequestMethod.POST)
    public void saveEmpInfoBt(@RequestBody A002_Request request){
        try {
            empService.saveEmpInfoBt(request);
        } catch (Exception e) {
            logger.info("批量导入emp数据出错");
        }
    }
}
