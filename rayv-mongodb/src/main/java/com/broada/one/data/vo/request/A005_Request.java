package com.broada.one.data.vo.request;

import lombok.Data;
import lombok.NonNull;

@Data
public class A005_Request {
    //经度
    @NonNull
    private Double lat;
    //纬度
    @NonNull
    private Double lon;
    //商家数量
    private Integer num;
    //最大距离
    private Double maxDistance;
}
