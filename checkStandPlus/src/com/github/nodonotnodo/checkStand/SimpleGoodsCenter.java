package com.github.nodonotnodo.checkStand;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

//商品中心
public class SimpleGoodsCenter implements GoodsCenter {

    private Map<String,Goods> goodsMap = new HashMap<>();//商品集合

    public Map<String, Goods> getGoodsMap() {
        return goodsMap;
    }

    @Override
    public void addGoods(Goods good) {//添加商品
        if(good == null){
            return;
        }
        if(goodsMap.containsKey(good.getId())){
            return;
        }else{
            goodsMap.put(good.getId(),good);
        }
    }

    @Override
    public void removeGoods(Goods good) {//移除商品
        if(good == null){
            return;
        }
        if(goodsMap.containsKey(good.getId())){
            goodsMap.remove(good.getId());
        }else{
            return;
        }
    }

    @Override
    public void updateGoods(Goods good) {//更新商品
        if(good == null){
            return;
        }
        if(goodsMap.containsKey(good.getId())){
            goodsMap.replace(good.getId(),good);
        }else{
            return;
        }
    }

    @Override
    public boolean isExistGoods(String goodId) {//判断是否为商品
        if(goodId == null){
            return false;
        }
        if(goodsMap.containsKey(goodId)){
            return true;
        }
        return false;
    }

    @Override
    public Goods getGoods(String goodId) {//根据商品编号获得商品对象
        if(goodId == null){
            return null;
        }
        if(goodsMap.containsKey(goodId)){
            return goodsMap.get(goodId);
        }
        return null;
    }

    @Override
    public String listGoods() {//打印商品信息
        StringBuffer sb = new StringBuffer();
        sb.append("*****************商品信息*****************\n");
        sb.append("good_id\t\tgood_name\tgood_price\n");
        for(Map.Entry<String, Goods> entry : goodsMap.entrySet()){
            sb.append("\t");
            sb.append(entry.getValue().getId());
            sb.append("\t\t\t");
            sb.append(entry.getValue().getName());
            sb.append("\t\t\t");
            sb.append(entry.getValue().getPrice());
            sb.append("\n");
        }
        sb.append("******************************************\n");
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
        String sqlDelete = "delete from goods";
        jd.executeOrder(sqlDelete);
        //开始将数据保存到数据库中
        for(Goods good: goodsMap.values()){
            //insert into checkstand values (goodId,goodName,goodPrice)
            String sql = String.format("insert into goods values (%s,'%s',%.2f)"
                    ,good.getId(),good.getName(),good.getPrice());
//            StringBuffer sql = null;
//            sql.append("insert into checkstand values ");
//            sql.append("("+good.getId());
//            sql.append(","+good.getName());
//            sql.append(","+good.getPrice()+")");
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
        String sql = "select * from goods";
        jd.executeOrder(sql);
        try(
            ResultSet resultSet = jd.handle()
                ){
            while (resultSet.next()){
                Goods good = new Goods(resultSet.getString("good_id"),
                        resultSet.getString("good_name"),
                        resultSet.getDouble("good_price"));
                goodsMap.put(resultSet.getNString("good_id"), good);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        jd.myCommit();
        jd.colseAll();
    }

    public static void main(String[] args) {
        SimpleGoodsCenter goodsCentercenter = new SimpleGoodsCenter();
//        Goods good1 = new Goods("1","铅笔",0.5);
//        goodsCentercenter.addGoods(good1);
//        goodsCentercenter.load();
//        System.out.println(goodsCentercenter.listGoods());
//        Goods good2 = new Goods("2","钢笔",12.5);
//        goodsCentercenter.addGoods(good2);
//        System.out.println(goodsCentercenter.listGoods());
//        goodsCentercenter.store();
        goodsCentercenter.load();
        System.out.println(goodsCentercenter.listGoods());
        goodsCentercenter.removeGoods(goodsCentercenter.getGoods("1"));
        goodsCentercenter.listGoods();
        goodsCentercenter.store();
    }
}
