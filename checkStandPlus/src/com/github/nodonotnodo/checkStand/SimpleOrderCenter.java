package com.github.nodonotnodo.checkStand;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class SimpleOrderCenter implements OrderCenter {

    private Map<String,Double> orderMap = new HashMap<>();//订单集合

    public int getMaxOrderId(){
        JdbcMould jd = new JdbcMould() {
            @Override
            public ResultSet handle() {
                return null;
            }
        };
        jd.loadDrive();
        jd.connect("root","1234");
        jd.createSql();
        //select max(order_id) from Orders
        String sql = "select max(order_id) from Orders";
        int result = 0;
        jd.executeOrder(sql);
        try (        ResultSet resultSet = jd.getResultSet();
        ){
            if(resultSet.next()){
                result = Integer.parseInt(resultSet.getNString("max(order_id)"));
            }
            jd.myCommit();
            jd.colseAll();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void addOrder(Order order) {//添加订单
        if(order == null){
            return;
        }
        SimpleGoodsCenter goodsCenter = new SimpleGoodsCenter();
        goodsCenter.load();
        Map goodsMap = goodsCenter.getGoodsMap();
        Double totalPrice = 0D;
        for(Map.Entry<String,Integer> entry: order.getOrderInfo().entrySet()){
            if(goodsCenter.isExistGoods(entry.getKey())){
                totalPrice += goodsCenter.getGoods(entry.getKey()).getPrice() * entry.getValue();
            }
        }
        orderMap.put(order.getOrderid(),totalPrice);
    }

    @Override
    public void removeOrder(Order order) {//移除订单
        if(order == null){
            return;
        }
        orderMap.remove(order.getOrderid());
    }

    @Override
    //所有订单信息
    public String orderTable() {
        StringBuffer sb = new StringBuffer();
        sb.append("********************所有订单*********************\n");
        sb.append("order_id\t\torder_totalPrice\n");
        for(Map.Entry<String,Double> entry: orderMap.entrySet()){
            sb.append("\t");
            sb.append(entry.getKey());
            sb.append("\t\t\t\t");
            sb.append(entry.getValue());
            sb.append("\n");
        }
        sb.append("********************结束*************************\n");
        return sb.toString();
    }

    @Override
    //指定订单信息
    public String orderTable(String orderId) {
        StringBuffer sb = new StringBuffer();
        sb.append("********************所有订单*********************\n");
        sb.append("order_id\t\torder_totalPrice\n");
        for(Map.Entry<String,Double> entry: orderMap.entrySet()){
            if(orderId.equalsIgnoreCase(entry.getKey())){
                sb.append("\t");
                sb.append(entry.getKey());
                sb.append("\t\t\t\t");
                sb.append(entry.getValue());
                sb.append("\n");
                break;
            }
        }
        sb.append("********************结束*************************\n");
        return sb.toString();
    }

    @Override
    public void store() {
        JdbcMould jd = new JdbcMould() {
            @Override
            public ResultSet handle() {
                return null;
            }
        };
        jd.loadDrive();
        jd.connect("root","1234");
        jd.createSql();
        //sqlDelete命令会将数据库表中的所有数据清空。
        String sqlDelete = "delete from orders";
        jd.executeOrder(sqlDelete);
        //开始将数据保存到数据库中
        for(Map.Entry<String,Double> entry: orderMap.entrySet()){
            //insert into orders values (order_id,order_totalPrice)
            String sql = String.format("insert into orders values (%s,%.2f)",
                    entry.getKey(),entry.getValue());
            jd.executeOrder(sql);
            jd.myCommit();
        }
        jd.colseAll();
    }

    @Override
    public void load() {
        JdbcMould jd = new JdbcMould() {
            @Override
            public ResultSet handle() {
                return getResultSet();
            }
        };
        jd.loadDrive();
        jd.connect("root","1234");
        jd.createSql();
        String sql = "select * from orders";
        jd.executeOrder(sql);
        try(
                ResultSet resultSet = jd.handle()
        ){
            while (resultSet.next()){
                orderMap.put(resultSet.getNString("order_id")
                        ,resultSet.getDouble("order_totalPrice"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        jd.myCommit();
        jd.colseAll();
    }

    public static void test(){
        SimpleOrderCenter orderCenter = new SimpleOrderCenter();
        orderCenter.load();
//        Order order1 = new Order("1");
//        order1.add("2",3);
//        orderCenter.addOrder(order1);
//        System.out.println(orderCenter.orderTable());
//        System.out.println(orderCenter.orderTable());
//        orderCenter.store();
        Order order2 = new Order("2");
        order2.add("2",5);
//        orderCenter.addOrder(order2);
//        Order order3 = new Order("3");
//        order3.add("2",3);
//        orderCenter.addOrder(order3);
        System.out.println(orderCenter.orderTable());
        System.out.println(orderCenter.orderTable("1"));
//        orderCenter.store();
        orderCenter.removeOrder(order2);
        System.out.println(orderCenter.orderTable());
        System.out.println(orderCenter.orderTable("1"));
        orderCenter.store();
    }

    public static void main(String[] args) {
//        test();
        SimpleOrderCenter orderCenter = new SimpleOrderCenter();
        orderCenter.load();
        System.out.println(orderCenter.getMaxOrderId());
    }
}
