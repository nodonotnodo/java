package com.github.nodonotnodo.modelPC;

import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class TestPc {

    public static void main(String[] args) {

        ArrayBlockingQueue<Goods> goodsShelf = new ArrayBlockingQueue<>(configTestPC.MAX_GOODSSHELF_CAPACITY);

        Producer producer = new Producer(goodsShelf);
        Customer customer = new Customer(goodsShelf);

        Thread thread1 = new Thread(producer,"Thread-老板");
        Thread thread2 = new Thread(new Producer(1500,goodsShelf),"Thread-老板娘");
        Thread thread3 = new Thread(new Producer(2000,goodsShelf),"Thread-雇员");
        thread1.start();
        thread2.start();
        thread3.start();

        Thread thread4 = new Thread(customer,"顾客1");
        Thread thread5 = new Thread(customer,"顾客2");
        thread4.start();
        thread5.start();

    }

}
