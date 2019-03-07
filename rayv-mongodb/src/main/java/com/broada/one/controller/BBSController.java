package com.broada.one.controller;

import com.broada.one.data.vo.request.A003_Request;
import com.broada.one.data.vo.request.A004_Request;
import com.broada.one.data.vo.response.A004_Response;
import com.broada.one.service.inf.BBSService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "论坛维护接口", tags = {"论坛维护接口"})
@RequestMapping("/BBS")
public class BBSController {

    @Autowired
    private BBSService bbsService;

    static Logger logger = LogManager.getLogger(EmpController.class);

    @ApiOperation(value = "新增BBS信息", notes = "")
    @PostMapping(value = "/saveBBS")
    public void saveBBS(@RequestBody A004_Request request){
        try {
            bbsService.saveBBS(request);
        } catch (Exception e) {
            logger.error("保存BBS错误");
        }
    }
    @ApiOperation(value = "新增评论信息",notes = "")
    @PostMapping(value = "/saveReview")
    public void saveReview(@RequestBody A003_Request request){
        try {
            bbsService.saveReview(request);
        } catch (Exception e) {
            logger.error("保存评论出错");
        }
    }
    @ApiOperation(value = "通过id查询文章及评论",notes = "")
    @GetMapping(value = "/getBBSDetails")
    public A004_Response getBBSDetails(@RequestParam(name = "articleId",required = true)String id){
        A004_Response outVo = new A004_Response();
        try {
            outVo=bbsService.getBBSDetails(id);
        } catch (Exception e) {
            logger.error("查询文章id为"+id);
            logger.error("通过id查询文章及评论出错");
        }
        return outVo;
    }
}
