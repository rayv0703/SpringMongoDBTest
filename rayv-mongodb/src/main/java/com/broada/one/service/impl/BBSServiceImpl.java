package com.broada.one.service.impl;

import com.broada.one.controller.EmpController;
import com.broada.one.data.domain.*;
import com.broada.one.data.vo.request.A003_Request;
import com.broada.one.data.vo.request.A004_Request;
import com.broada.one.data.vo.response.A004_Response;
import com.broada.one.service.inf.BBSService;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

@Transactional
@Service
public class BBSServiceImpl implements BBSService {

    @Autowired
    private MongoTemplate mongoTemplate;

    //@Autowired
    //private GridFsTemplate gdsTemplate;

    static Logger logger = LogManager.getLogger(EmpController.class);

    @Override
    public void saveBBS(A004_Request request) {
        BBSInfo bbsInfo = new BBSInfo();
        BeanUtils.copyProperties(request, bbsInfo);
        //设置唯一主键,可以根据主键进行查找
        if (StringUtils.isEmpty(request.getId())) {
            bbsInfo.setId(UUID.randomUUID().toString());
        }
        mongoTemplate.insert(bbsInfo);
    }

    @Override
    public void saveReview(A003_Request request) {
        Review review = new Review();
        BeanUtils.copyProperties(request, review);
        //新增查询条件
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.and("id").is(request.getId());
        query.addCriteria(criteria);
        BBSInfo bbsInfo = mongoTemplate.findOne(query, BBSInfo.class);
        if (null == bbsInfo) {
            logger.info("id为" + request.getId() + "结果为空");
        }
        List<Review> reviewList = bbsInfo.getReviewList();
        if (null != reviewList && !reviewList.isEmpty()) {

            review.setLevel("" + (reviewList.size() + 1));
            reviewList.add(review);
            bbsInfo.setReviewList(reviewList);
        } else {
            review.setLevel("1");
            List<Review> list = new ArrayList<Review>();
            list.add(review);
            bbsInfo.setReviewList(list);
        }
        mongoTemplate.save(bbsInfo);
    }

    @Override
    public A004_Response getBBSDetails(String id) {
        A004_Response outVo = new A004_Response();
        //新增查询条件
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.and("id").is(id);
        query.addCriteria(criteria);
        BBSInfo bbsInfo = mongoTemplate.findOne(query, BBSInfo.class);

        if (null == bbsInfo) {
            logger.info("id为" + id + "结果为空");
        }
        BeanUtils.copyProperties(bbsInfo, outVo);
        return outVo;
    }
}
