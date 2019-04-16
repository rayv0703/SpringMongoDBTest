package com.broada.one.data.domain;

import com.broada.config.AutoIncKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "emp")
@AllArgsConstructor
@NoArgsConstructor
public class EmpInf {
    @Id
    //@AutoIncKey
    private Long id = 0l;
    @Field
    private String name;
    @Field
    private Integer age;
    @Field
    private String addr;
}
