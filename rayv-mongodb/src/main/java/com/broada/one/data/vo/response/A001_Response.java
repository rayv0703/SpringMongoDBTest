package com.broada.one.data.vo.response;

import com.broada.one.data.domain.EmpInf;
import lombok.Data;

import java.util.List;
@Data
public class A001_Response {

    private List<EmpInf> empList;
    private long totalCount;
    private int page;
    private int size;
    private int pageNo;
}
