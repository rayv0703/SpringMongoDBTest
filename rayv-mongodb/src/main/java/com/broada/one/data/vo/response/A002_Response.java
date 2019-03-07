package com.broada.one.data.vo.response;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class A002_Response {

    private double cpuCombined;
    private double cpuUser;
    private double cpuSys;
    private double cpuWait;
    private double cpuIdle;
    private long memTotal;
    private long memUsed;
    private long memFree;
    private Map<String,Object> fileSysMsgMap;

}
