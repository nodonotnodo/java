package com.github.nodonotnodo.modelPC;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

public class Goods {

    private final String goodId;

    public String getGoodId() {
        return goodId;
    }

    public Goods() {
        this.goodId = LocalDateTime.now().toString();
    }
}
