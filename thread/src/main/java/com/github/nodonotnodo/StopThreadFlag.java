package com.github.nodonotnodo;

public class StopThreadFlag {

    public static void main(String[] args) throws InterruptedException {

        MyRunnable myRunnable = new MyRunnable();
        Thread thread = new Thread(myRunnable,"Thread-flag");
        thread.start();
        Thread.sleep(5000);
        myRunnable.setFlag(false);

    }

}
