package com.broada.one.data.vo.response;

import lombok.Data;

import java.util.Map;

@Data
public class A003_Response {

    private double cpuCombined;
    private double cpuUser;
    private double cpuSys;
    private double cpuWait;
    private double cpuIdle;
    private String cpuAvgCombined;
    private String cpuAvgIdle;
    private long memTotal;
    private long memUsed;
    private long memFree;
    private Map<String,Object> fileSysMsgMap;

}
