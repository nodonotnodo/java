package com.github.nodonotnodo.modelPC;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

public class Producer implements Runnable {

    private Integer speed = 1000;
    private volatile ArrayBlockingQueue<Goods> goodsShelf;

    public Producer(ArrayBlockingQueue<Goods> goodsShelf) {
        this.goodsShelf = goodsShelf;
    }

    public Producer(Integer speed, ArrayBlockingQueue<Goods> goodsShelf) {
        this.speed = speed;
        this.goodsShelf = goodsShelf;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(this.speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (goodsShelf){
                if (goodsShelf.size() == configTestPC.MAX_GOODSSHELF_CAPACITY) {
                    try {
                        System.out.println("哎呀，货架满了，休息一下吧");
                        goodsShelf.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    Goods goods = new Goods();
                    this.goodsShelf.add(goods);
                    System.out.println(Thread.currentThread().getName() + "创建了一个商品：。。。" + goods.getGoodId());
                }
            }
        }
    }

}
