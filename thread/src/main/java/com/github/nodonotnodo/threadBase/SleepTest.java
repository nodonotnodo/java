package com.github.nodonotnodo.threadBase;

public class SleepTest {

    public static void main(String[] args) {

        MyRunnable myRunnable = new MyRunnable();

        Thread thread1 = new Thread(myRunnable,"Thread-1");
        Thread thread2 = new Thread(myRunnable,"Thread-2");
        Thread thread3 = new Thread(myRunnable,"Thread-3");

        //可以看到运行结果每个进程打印间隔1秒
        thread1.start();
        thread2.start();
        thread3.start();

    }

}
