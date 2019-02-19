package com.github.nodonotnodo;

public class Pratice1 implements Runnable {

    @Override
    public void run() {
        synchronized(this){
            System.out.println("@#" + Thread.currentThread().getName());
            System.out.println(Thread.currentThread().getName() + "线程开始");
            System.out.println(Thread.currentThread().getName() + "线程结束");
        }
    }

}
