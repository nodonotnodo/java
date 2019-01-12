package com.github.nodonotnodo.checkStand;

import java.util.HashMap;
import java.util.Map;

public class Order {


    private final String orderid;//订单编号
    private final Map<String,Integer> orderInfo = new HashMap<>();//订单信息

    public Order(String orderid) {
        this.orderid = orderid;
    }

    public String getOrderid() {
        return orderid;
    }

    public Map<String, Integer> getOrderInfo() {
        return orderInfo;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderid='" + orderid + '\'' +
                ", orderInfo=" + orderInfo +
                '}';
    }

    public void add(String name, Integer num){
        //TODO
    }

    public void cancel(String name, Integer num){
        //TODO
    }

    public void clear(){
        //TODO
    }
}
