package com.broada.one.data.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class BBSInfo {
    @Id
    //唯一标识
    private String id;
    private String title;
    private String content;
    private String userId;
    private List<Review> reviewList;

}
