package com.github.nodonotnodo;

public class YieldTest {

    public static void main(String[] args) {

        MyRunnable myRunnable = new MyRunnable();

        Thread thread1 = new Thread(myRunnable,"Thread-1");
        Thread thread2 = new Thread(myRunnable,"Thread-2");
        Thread thread3 = new Thread(myRunnable,"Thread-3");

        //结果每个进程打印间隔不确定
        thread1.start();
        thread2.start();
        thread3.start();
    }

}
