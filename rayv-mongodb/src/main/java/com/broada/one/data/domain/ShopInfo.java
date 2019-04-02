package com.broada.one.data.domain;

import com.broada.application.AutoIncKey;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("shop")
public class ShopInfo {
    @Id
    @AutoIncKey
    private Long id;
    private Double[] locs = new Double[2] ;
    private String telephone;
    private String shopName;
    private String address;
    private Double distance;
}
