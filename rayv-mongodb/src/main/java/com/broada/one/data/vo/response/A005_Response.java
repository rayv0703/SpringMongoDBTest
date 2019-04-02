package com.broada.one.data.vo.response;

import com.broada.one.data.domain.Shop;
import com.broada.one.data.domain.ShopInfo;
import lombok.Data;

import java.util.List;

@Data
public class A005_Response {

    private List<ShopInfo> shopList;
    private Integer count;
}
