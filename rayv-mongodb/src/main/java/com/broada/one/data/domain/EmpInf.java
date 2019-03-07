package com.broada.one.data.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "emp")
public class EmpInf {
    @Id
    private Long id;
    private String name;
    private Integer age;
    private String addr;
}
