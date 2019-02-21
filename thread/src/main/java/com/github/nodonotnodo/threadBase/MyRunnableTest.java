package com.github.nodonotnodo.threadBase;

public class MyRunnableTest {

    public static void main(String[] args) {
        MyRunnable runnable = new MyRunnable();
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        Thread thread3 = new Thread(runnable);
        thread1.start();
        thread2.start();
        thread3.start();

        Thread thread4 = new Thread(new Runnable() {
            public void run() {
                Thread.currentThread().setName("Thread-Runnable匿名对象线程");
                System.out.println("这是"+Thread.currentThread().getName()+"线程的run方法");
            }
        });
        thread4.start();

        Runnable runnable1 = () -> {
            Thread.currentThread().setName("Thread-Runnable使用Lamdba表达式实例化线程");
            System.out.println("这是"+Thread.currentThread().getName()+"线程的run方法");
        };
        Thread thread5 = new Thread(()->{
            Thread.currentThread().setName("Thread-Runnable使用Lamdba表达式实例化线程");
            System.out.println("这是"+Thread.currentThread().getName()+"线程的run方法");
        });
//        Thread thread5 = new Thread(runnable1);
        thread5.start();
    }

}
