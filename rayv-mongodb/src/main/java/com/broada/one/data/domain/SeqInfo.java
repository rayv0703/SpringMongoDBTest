package com.broada.one.data.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 序列计数类
 */
@Data
@Document(collection = "sequence")
public class SeqInfo {
    /**
     * 主键
     */
    @Id
    private String id;
    /**
     * 集合名称
     */
    @Field
    private String collName;
    /**
     * 序列值
     */
    @Field
    private Long seqId;
}
