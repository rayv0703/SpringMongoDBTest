package com.broada.one.service.inf;

import com.broada.one.data.vo.request.A001_Request;
import com.broada.one.data.vo.response.A001_Response;
import com.broada.one.data.vo.request.A002_Request;
import com.broada.one.data.domain.EmpInf;

public interface EmpService {
    void save(EmpInf emp);

    EmpInf findOne(long id);

    void update(EmpInf emp);

    void deleteById(long id);


    A001_Response queryListByPage(A001_Request request);

    void saveEmpInfoBt(A002_Request request);
}
