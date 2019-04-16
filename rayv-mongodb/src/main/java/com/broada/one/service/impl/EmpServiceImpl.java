package com.broada.one.service.impl;

import com.broada.one.data.vo.request.A001_Request;
import com.broada.one.data.vo.response.A001_Response;
import com.broada.one.data.vo.request.A002_Request;
import com.broada.one.data.domain.EmpInf;
import com.broada.one.data.repo.EmpRepository;
import com.broada.one.service.inf.EmpService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
@Transactional
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpRepository repo;

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 使用MongoDB添加员工数据
     * @param emp
     */
    @Override
    public void save(EmpInf emp) {
        repo.insert(emp);
    }

    /**
     * 使用MongoDB查询数据
     * @param id
     * @return
     */
    @Override
    public EmpInf findOne(long id) {
        EmpInf emp = new EmpInf();
        Optional<EmpInf> optional = repo.findById(id);
        if (optional.isPresent()) {
            emp = optional.get();
        }
        return emp;
    }

    /**
     * 使用MongoDB修改员工数据
     * @param emp
     */
    @Override
    public void update(EmpInf emp) {
        mongoTemplate.save(emp);
    }

    /**
     * 使用MongoDB删除员工数据
     * @param id
     */
    @Override
    public void deleteById(long id) {
        repo.deleteById(id);
    }

    @Override
    public A001_Response queryListByPage(A001_Request request) {
        A001_Response outVo = new A001_Response();
        EmpInf emp = new EmpInf();
        BeanUtils.copyProperties(request, emp);
        //新增查询条件
        Query query = new Query();
        Criteria criteria = new Criteria();
        //默认分页大小为第一页,大小为10
        int page = 1;
        int size = 10;
        if (emp.getId() != 0) {
            criteria.and("id").is(emp.getId());
        }
        //criteria.and().is  精确查询
        if (StringUtils.isNotEmpty(emp.getName())) {
            criteria.and("name").is(emp.getName());
        }
        //范围查询  gt和lt
        if (request.getAgeBeg() != 0 && request.getAgeEnd() != 0) {
            criteria.and("age").gte(request.getAgeBeg()).lte(request.getAgeEnd());
        }
        //模糊查询  通过正则表达式
        if (StringUtils.isNotEmpty(emp.getAddr())) {
            Pattern pattern = Pattern.compile("^.*" + emp.getAddr() + ".*$", Pattern.CASE_INSENSITIVE);
            criteria.and("addr").regex(pattern);
        }
        //如果 没传入分页信息,为默认值
        if (request.getPage() != 0) {
            page = request.getPage();
        }
        if (request.getSize() != 0) {
            size = request.getSize();
        }
        //由于MongoDB第一页开始是由0计算,所以减去1
        Pageable pageable = PageRequest.of(page - 1, size);
        query.addCriteria(criteria).with(pageable);
        //进行降序排列
        query.with(new Sort(new Sort.Order(Sort.Direction.DESC, "_id"), new Sort.Order(Sort.Direction.DESC, "-1")));
        List<EmpInf> list = mongoTemplate.find(query, EmpInf.class);
        long totalCount = mongoTemplate.count(query, EmpInf.class);
        outVo.setEmpList(list);
        outVo.setPage(page);
        outVo.setSize(size);
        outVo.setTotalCount(totalCount);
        outVo.setPageNo(new Long((totalCount + size - 1) / size).intValue());
        return outVo;
    }


}
