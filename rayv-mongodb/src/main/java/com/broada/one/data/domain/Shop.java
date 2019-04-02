package com.broada.one.data.domain;

import lombok.Data;

@Data
public class Shop {
    private Double[] locs = new Double[2] ;
    private String title;
    private String address;
}
