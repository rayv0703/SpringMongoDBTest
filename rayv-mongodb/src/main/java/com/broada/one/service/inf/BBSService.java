package com.broada.one.service.inf;

import com.broada.one.data.vo.request.A003_Request;
import com.broada.one.data.vo.request.A004_Request;
import com.broada.one.data.vo.response.A004_Response;

public interface BBSService {
    void saveBBS(A004_Request request);

    void saveReview(A003_Request request);

    A004_Response getBBSDetails(String id);
}
