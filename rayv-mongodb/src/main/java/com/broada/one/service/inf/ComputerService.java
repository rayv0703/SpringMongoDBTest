package com.broada.one.service.inf;

import com.broada.one.data.vo.response.A002_Response;
import com.broada.one.data.vo.response.A003_Response;

public interface ComputerService {

    A002_Response findLastInfo();

    A003_Response findLast5();
}
