package com.broada.one.data.vo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class A005_Request {
    //经度
    @ApiModelProperty(name = "当前位置经度",required = true)
    private Double lat;
    //纬度
    @ApiModelProperty(name = "当前位置纬度",required = true)
    private Double lon;
    //商家数量
    @ApiModelProperty(name = "商家数量")
    private Integer num;
    //最大距离
    @ApiModelProperty(name = "最大距离")
    private Double maxDistance;
}
