package com.github.nodonotnodo;

public class JoinTest {

    public static void main(String[] args) throws InterruptedException {

        MyRunnable myRunnable = new MyRunnable();

        System.out.println(Thread.currentThread().getName()+"线程开始运行");

        Thread thread1 = new Thread(myRunnable,"Thread-1");
        thread1.start();

        /**
         * join()方法会让调用它的当前程阻塞，直到调用该方法的线程run()方法执行结束后当前进程在进入就绪状态
         */
        thread1.join();

        System.out.println(Thread.currentThread().getName()+"线程运行结束");

    }

}
