package com.broada.one.service.impl;

import com.broada.one.data.domain.Shop;
import com.broada.one.data.domain.ShopInfo;
import com.broada.one.data.vo.request.A005_Request;
import com.broada.one.data.vo.response.A005_Response;
import com.broada.one.service.inf.ShopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@Transactional
@Slf4j
public class ShopServiceImpl implements ShopService {
    @Autowired
    private MongoTemplate mongoTemplate;

    private Integer num = Integer.MAX_VALUE;
    private Double maxDistance = Double.MAX_VALUE;

    @Override
    public A005_Response searchShop(A005_Request request) {
        A005_Response outVo = new A005_Response();
        int count = 0;
        if (null == request.getLon() && null == request.getLat()) {
            log.error("经纬度值为空");
        }
        Point point = new Point(request.getLon(), request.getLat());
        if (null !=request.getNum()){
            num = request.getNum();
        }
        if (null != request.getMaxDistance()) {
            maxDistance = request.getMaxDistance();
        }
        NearQuery query = NearQuery.near(point).num(num).query(new Query()).maxDistance(new Distance(maxDistance, Metrics.KILOMETERS));
        GeoResults<Shop> results = mongoTemplate.geoNear(query, Shop.class);
        Iterator<GeoResult<Shop>> iterator = results.iterator();
        List<ShopInfo> shopList = new ArrayList<>();
        while(iterator.hasNext()){
            ShopInfo shopInfo = new ShopInfo();
            GeoResult<Shop> next = iterator.next();
            Shop shop = next.getContent();
            Distance distance = next.getDistance();
            double mile = distance.getValue();
            BeanUtils.copyProperties(shop,shopInfo);
            shopInfo.setDistance(mile);
            shopList.add(shopInfo);
            count ++;
        }
        outVo.setShopList(shopList);
        outVo.setCount(count);
        return outVo;
    }
}
