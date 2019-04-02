package com.broada.one.controller;

import com.broada.one.data.vo.request.A005_Request;
import com.broada.one.data.vo.response.A005_Response;
import com.broada.one.service.inf.ShopService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shop")
@Slf4j
public class ShopController {

    @Autowired
    private ShopService shopService;

    @ApiOperation(value = "搜索周边店铺",notes = "搜索周边店铺")
    @RequestMapping(value = "/searchShop",method = RequestMethod.POST)
    public A005_Response searchShop(@RequestBody A005_Request request){
        A005_Response outVo = new A005_Response();
        try {
            outVo = shopService.searchShop(request);
        } catch (Exception e) {
            log.error("查询周边商户出错");
        }
        return outVo;
    }
}
