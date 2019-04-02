package com.broada.one.service.inf;

import com.broada.one.data.vo.request.A005_Request;
import com.broada.one.data.vo.response.A005_Response;

public interface ShopService {
    A005_Response searchShop(A005_Request request);
}
