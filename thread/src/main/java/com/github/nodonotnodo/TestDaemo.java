package com.github.nodonotnodo;

public class TestDaemo {

    public static void main(String[] args) {

        MyRunnable myRunnable = new MyRunnable();
        Thread daemo = new Thread(myRunnable,"Thread-Daemo");
        daemo.setDaemon(true);
        daemo.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
