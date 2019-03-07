package com.broada.one.data.vo.response;

import com.broada.one.data.domain.Review;
import lombok.Data;

import java.util.List;

@Data
public class A004_Response {

    private String id;
    private String title;
    private String content;
    private String userId;
    private List<Review> reviewList;

}
