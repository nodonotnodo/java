package com.github.nodonotnodo.checkStand;

import java.util.Map;
import java.util.Scanner;

public class CheckStand {

    public static void help(){
        StringBuffer help = new StringBuffer();
        help.append("***************欢迎使用收银台（数据库）*****************\n");
        help.append("      [U] 使用  [S] 设置  [P] 保存  [A] 关于  [Q]退出   \n");
        help.append("       输入： U   S   P   A   Q   进入操作              \n");
        help.append("********************************************************\n");
        System.out.println(help);
    }

    public static void anenst(){
        StringBuffer help = new StringBuffer();
        help.append("***************欢迎使用收银台（数据库）*****************\n");
        help.append("           名称：简易收银台                             \n");
        help.append("           作者：nodonotnodo                            \n");
        help.append("           创建时间：2019-01-13                         \n");
        help.append("           存储：数据库存储                             \n");
        help.append("********************************************************\n");
        System.out.println(help);
    }

    public static void preserve(SimpleGoodsCenter simpleGoodsCenter,SimpleOrderCenter simpleOrderCenters){
        simpleGoodsCenter.store();
        simpleOrderCenters.store();
    }

    public static void sHelp(){
        StringBuffer help = new StringBuffer();
        help.append("**********************欢迎设置商品**********************\n");
        help.append("      [U]商品上架      [D]商品下架     [A]商品修改      \n");
        help.append("      [L]查看商品      [O]浏览订单     [R]返回上级      \n");
        help.append("       输入：U    D   A   L   O   R进行操作             \n");
        help.append("********************************************************\n");
        System.out.println(help);
    }

    //商品上架
    public static void upGood(final SimpleGoodsCenter simpleGoodsCenter,Scanner sc){
        System.out.println("请输入要上架的商品信息：(如：1,铅笔,0.2)\n");
        String good = sc.next().replaceAll(" ","");
        String[] goods = good.split(",");
        if(goods.length == 3){
            simpleGoodsCenter.addGoods(new Goods(goods[0],goods[1],Double.parseDouble(goods[2])));
        }
    }

    //商品下架
    public static void downGood(final SimpleGoodsCenter simpleGoodsCenter,Scanner sc){
        System.out.println("请输入要下架的商品信息：(如：1,铅笔,0.2)\n");
        String good = sc.next().replaceAll(" ","");
        String[] goods = good.split(",");
        if(goods.length == 3){
            simpleGoodsCenter.removeGoods(new Goods(goods[0],goods[1],Double.parseDouble(goods[2])));
        }
    }

    //商品更新
    public static void alterGood(final SimpleGoodsCenter simpleGoodsCenter,Scanner sc){
        System.out.println("请输入要更新的商品信息：(如：1,铅笔,0.2)\n");
        String good = sc.next().replaceAll(" ","");
        String[] goods = good.split(",");
        if(goods.length == 3){
            simpleGoodsCenter.updateGoods(new Goods(goods[0],goods[1],Double.parseDouble(goods[2])));
        }
    }

    //商品中心设置
    public static void setGoods(final SimpleGoodsCenter simpleGoodsCenter, final SimpleOrderCenter simpleOrderCenters, Scanner sc){
        while(true){
            sHelp();
            String choose = sc.next();
            switch(choose.toUpperCase()){
                case "U":{
                    upGood(simpleGoodsCenter,sc);
                    break;
                }
                case "D":{
                    downGood(simpleGoodsCenter,sc);
                    break;
                }
                case "A":{
                    alterGood(simpleGoodsCenter,sc);
                    break;
                }
                case "L":{
                    System.out.println(simpleGoodsCenter.listGoods());
                    break;
                }
                case "O":{
                    System.out.println(simpleOrderCenters.orderTable());
                    break;
                }
                case "R":{
                    return;
                }
                default:{
                    break;
                }
            }
        }
    }

    //买单帮助菜单
    public static void oHelp(){
        StringBuffer help = new StringBuffer();
        help.append("**********************欢迎进行买单**********************\n");
        help.append("      [A]添加商品      [D]取消商品     [L]浏览商品      \n");
        help.append("      [S]查看订单      [R]返回上级                      \n");
        help.append("       输入：A    D   L   S   R进行操作                 \n");
        help.append("********************************************************\n");
        System.out.println(help);
    }

    //向订单添加商品
    public static void addGood(Scanner sc, Order order){
        System.out.println("请输入要买的商品编号及数量：(如：1,3)\n");
        String good = sc.next().replaceAll(" ","");
        String[] goods = good.split(",");
        if(goods.length == 2){
            order.add(goods[0],Integer.parseInt(goods[1]));
        }
    }

    //向订单取消商品
    public static void deleteGood(Scanner sc, Order order){
        System.out.println("请输入要取消的商品编号及数量：(如：1,3)\n");
        String good = sc.next().replaceAll(" ","");
        String[] goods = good.split(",");
        if(goods.length == 2){
            order.cancel(goods[0],Integer.parseInt(goods[1]));
        }
    }

    //买单
    public static void useOrder(final SimpleGoodsCenter simpleGoodsCenter, final SimpleOrderCenter simpleOrderCenters, Scanner sc){
        int orderId = simpleOrderCenters.getMaxOrderId();
        Order order = new Order(String.valueOf(++orderId));
        while(true){
            oHelp();
            String choose = sc.next();
            switch(choose.toUpperCase()){
                case "A":{
                    addGood(sc,order);
                    break;
                }
                case "D":{
                    deleteGood(sc,order);
                    break;
                }
                case "L":{
                    System.out.println(simpleGoodsCenter.listGoods());
                    break;
                }
                case "S":{
                    System.out.println(simpleOrderCenters.orderTable());
                    break;
                }
                case "R":{
                    if(order.getOrderInfo().size() != 0){
                        simpleOrderCenters.addOrder(order);
                    }
                    return;
                }
                default:{
                    break;
                }
            }
            System.out.println("****购物订单****");
            Double totalPrice = 0D;
            for(Map.Entry<String,Integer> entry:order.getOrderInfo().entrySet()){
                System.out.println("商品编号："+entry.getKey()+"\t\t商品数量"+entry.getValue());
                totalPrice += simpleGoodsCenter.getGoods(entry.getKey()).getPrice() * entry.getValue();
            }
            System.out.println("\t订单总价:"+totalPrice);
            System.out.println("******************");
        }
    }

    public static void main(String[] args) {
        SimpleGoodsCenter simpleGoodsCenter = new SimpleGoodsCenter();
        SimpleOrderCenter simpleOrderCenter = new SimpleOrderCenter();
        simpleGoodsCenter.load();
        simpleOrderCenter.load();
        Scanner sc = new Scanner(System.in);
        while (true){
            help();
            String choose = sc.next();
            switch (choose.toUpperCase()){
                case "U":{
                    useOrder(simpleGoodsCenter,simpleOrderCenter,sc);
                    break;
                }
                case "S":{
                    setGoods(simpleGoodsCenter,simpleOrderCenter,sc);
                    break;
                }
                case "P":{
                    preserve(simpleGoodsCenter,simpleOrderCenter);
                    break;
                }
                case "A":{
                    anenst();
                    break;
                }
                case "Q":{
                    System.out.println("************再见！欢迎下次使用**************");
                    return;
                }
                default:{
                    break;
                }
            }
        }
    }}

