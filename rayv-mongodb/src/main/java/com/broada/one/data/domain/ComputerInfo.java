package com.broada.one.data.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;
@Data
@Document(collection = "ComputerInfo")
public class ComputerInfo {


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
