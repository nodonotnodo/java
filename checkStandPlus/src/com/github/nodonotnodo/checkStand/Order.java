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

    public void add(String name, Integer num){//添加要买的商品及数量
        if(name == null || num == 0){
            return;
        }
        SimpleGoodsCenter goodsCenter = new SimpleGoodsCenter();
        goodsCenter.load();
        Map<String,Goods> goodsMap = goodsCenter.getGoodsMap();
        if(goodsMap.containsKey(name)){
            if(orderInfo.containsKey(name)){//如果订单中已经有过这个商品了，则在原基础上进行变化
                num = orderInfo.get(name)+num;
                num = num>0 ? num:0;
                orderInfo.replace(name,num);
            }else{//如果订单中还没有这个商品，则将这个商品看情况添加进订单
                orderInfo.put(name,num);
            }
            if(orderInfo.get(name) == 0){
                orderInfo.remove(name,0);
            }
        }
    }

    public void cancel(String name, Integer num){//退掉指定数量商品
        if(name == null || num == 0){
            return;
        }
        SimpleGoodsCenter goodsCenter = new SimpleGoodsCenter();
        goodsCenter.load();
        Map<String,Goods> goodsMap = goodsCenter.getGoodsMap();
        if(goodsMap.containsKey(name)){
            if(orderInfo.containsKey(name)){//如果订单中已经有过这个商品了，则在原基础上进行变化
                num = orderInfo.get(name)-num;
                num = num>0 ? num:0;
                orderInfo.replace(name,num);
                //如果订单上商品数目为0，则清除掉这个商品
                if(orderInfo.get(name) == 0){
                    orderInfo.remove(name,0);
                }
            }
        }
    }

    public void clear(){//清空订单
        orderInfo.clear();
    }

    public static void myPrint(Order order){
        System.out.println("添加后的结果：");
        for(Map.Entry entry:order.orderInfo.entrySet()){
            System.out.println("商品编号："+entry.getKey()+"\t\t商品数量"+entry.getValue());
        }
        System.out.println("******************");
    }

    public static void test(){
        Order order = new Order("");
        order.add("2",2);
        myPrint(order);
        order.add("2",3);
        myPrint(order);
        order.add("2",-5);
        myPrint(order);
        order.add("1",5);
        myPrint(order);
        order.add("2",5);
        order.cancel("2",3);
        myPrint(order);
        order.clear();
        myPrint(order);
    }

    public static void main(String[] args) {
        test();
    }
}
