package com.broada.one.data.domain;

import lombok.Data;

@Data
public class Review {
    //评论楼层
    private String level;
    //评论用户id
    private String userId;
    //评论内容
    private String content;

}
