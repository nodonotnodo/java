package com.github.nodonotnodo.modelPC;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

public class Customer implements Runnable{

    private ArrayBlockingQueue<Goods> goodsShelf;

    public Customer(ArrayBlockingQueue<Goods> goodsShelf){
        this.goodsShelf = goodsShelf;
    }

    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (goodsShelf){
                if(goodsShelf.isEmpty()){
                    goodsShelf.notify();
                    System.out.println("商品空了，老板呢？");
                }else{
                    Goods goods = goodsShelf.poll();
                    if(goods != null)
                        System.out.println(Thread.currentThread().getName() + "买了一个商品---" + goods.getGoodId());
                }
            }
        }
    }

}
