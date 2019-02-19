package com.github.nodonotnodo;

public class StopThreadInterrupted {

    public static void main(String[] args) throws InterruptedException {

        MyRunnable myRunnable = new MyRunnable();
        Thread thread = new Thread(myRunnable,"Thread-interrupted");
        thread.start();
        Thread.sleep(5000);
        thread.interrupt();

    }

}
