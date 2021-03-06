package com.broada.one.controller;

import com.broada.one.data.vo.request.A005_Request;
import com.broada.one.data.vo.response.A005_Response;
import com.broada.one.service.inf.ShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "搜索周边商铺接口", tags = {"搜索周边商铺接口"})
@RestController
@RequestMapping("/shop")

public class ShopController {

    @Autowired
    private ShopService shopService;

    static Logger logger = LogManager.getLogger(EmpController.class);

    @ApiOperation(value = "搜索周边店铺",notes = "搜索周边店铺")
    @RequestMapping(value = "/searchShop",method = RequestMethod.POST)
    public A005_Response searchShop(@RequestBody A005_Request request){
        A005_Response outVo = new A005_Response();
        try {
            outVo = shopService.searchShop(request);
        } catch (Exception e) {
            logger.error("查询周边商户出错");
        }
        return outVo;
    }
}
