package com.github.nodonotnodo;

public class Pratice1Test {

    public static void main(String[] args) throws InterruptedException {

        Pratice1 pratice = new Pratice1();
        Thread thread1 = new Thread(pratice,"Thread-1");
        Thread thread2 = new Thread(pratice,"Thread-2");
        Thread thread3 = new Thread(pratice,"Thread-3");
        Thread thread4 = new Thread(pratice,"Thread-4");

        thread1.start();
        thread1.join();
        thread2.start();
        thread2.join();
        thread3.start();
        thread3.join();
        thread4.start();
        thread4.join();

    }

}
