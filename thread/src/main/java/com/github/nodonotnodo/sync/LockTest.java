package com.github.nodonotnodo.sync;

import com.github.nodonotnodo.threadBase.MyRunnable;

public class LockTest {

    public static void main(String[] args) {
        MyRunnable buyTicket = new MyRunnable();
        Thread thread1 = new Thread(buyTicket,"李一");
        Thread thread2 = new Thread(buyTicket,"王二");
        Thread thread3 = new Thread(buyTicket,"梁三");

        thread1.start();
        thread2.start();
        thread3.start();
    }

}
