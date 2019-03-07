package com.broada.one.data.vo.request;

import lombok.Data;

@Data
public class A001_Request {

    private Long id;
    private String name;
    private Integer ageBeg;
    private Integer ageEnd;
    private Integer age;
    private String addr;
    private int page;
    private int size;
}
