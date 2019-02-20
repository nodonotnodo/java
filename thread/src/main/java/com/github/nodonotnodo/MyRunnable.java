package com.github.nodonotnodo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyRunnable implements Runnable {

    private volatile boolean flag = true;

    public volatile int ticket = 10;

    private Lock ticketLock = new ReentrantLock();

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println("这是" + name + "线程的run方法");

        /**
         * sleep()方法会使线程休眠特定的时间，同时释放CPU资源，但是不会释放锁
         */
//        for (int i = 1; i < 5; i++) {
//            System.out.println(name + ":" + i);
//            try {
//                //sleep()方法以毫秒为单位
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

        /**
         * yield()方法会使线程交出CPU权限，不会释放锁
         * 它与sleep()方法的区别在于：1.它不会造成阻塞，而是直接进入就绪状态；2.所以释放CPU权限时间不确定；3.只能让相同优先级的线程获取CPU资源
         */
//        for (int i = 1; i < 5; i++) {
//            System.out.println(name + ": " + i);
//            Thread.yield();
//        }

//        System.out.println(name + "线程开始运行");
//        for (int i = 0; i < 5; i++) {
//            System.out.println(name + ": " + i);
//        }

        /**
         * 如果run方法是一个死循环，那么我们就要有办法让这个线程终止
         * 途径：1.设置标志位：2.stop()方法（这个方法终止线程太过粗暴，已弃用）；3.interrupted()方法
         */

        /**
         *我们可以通过在主线程改变标志位从而实现终止线程run()方法来终止线程
         */
//        int i = 0;
//        while(true){
//            if(!flag){
//                break;
//            }
//            System.out.println(Thread.currentThread().getName() + ": " + i++);
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

        /**
         * interrupted()方法只是会改变阻塞标志，所以我们可以将其与isInterrupted()方法组合使用达到我们想要的终止
         */
//        int i = 0;
//        while(true){
//            try {
//                if (Thread.currentThread().isInterrupted()) {
//                    System.out.println(Thread.currentThread().getName() + "线程终止");
//                    break;
//                }
//                System.out.println(Thread.currentThread().getName() + ": " + i++ + Thread.currentThread().isInterrupted());
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//                System.out.println(Thread.currentThread().isInterrupted());
//                return;
//            }
//        }

        /**
         * 守护进程示例
         */
//        int i = 0;
//        while(true){
//            System.out.println(Thread.currentThread().getName() + "的打印。。。" + i++);
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

        /**
         * 同步问题示例
         */

        //可以看到，这样导致了最终出现了一票多买以及票的浪费。
        //这是因为有多个进程同时进行变量修改导致的冲突
//        while(ticket > 0){
//            ticket--;
//            System.out.println("顾客" + Thread.currentThread().getName() + "买到了票，当前剩余票数。。。" + ticket);
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }


        //同步代码块
//        while(ticket > 0){
//            synchronized (this){
//                if(ticket > 0){
//                    ticket--;
//                    System.out.println("顾客" + Thread.currentThread().getName() + "买到了票，当前剩余票数。。。" + ticket);
//                }
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }

        //同步方法
//        while (this.ticket > 0){
//            this.sale();
//            try {
//                Thread.sleep(200);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

        /**
         * 优先级示例
         */
//        MyRunnable myRunnable = new MyRunnable();
//        Thread son = new Thread(myRunnable,"Thread-son");
//        System.out.println(son.getName() + "的线程优先级为" + son.getPriority());

        /**
         * Lock示例
         */
        //将unlock()方法一定要放到finally块中，否则有可能锁死
        //这种同步方式更加灵活，但是也需要更高的使用要求。
        //在JDK1.6之后，synchronized关键字已经十分优化，建议使用synchronized
        while (this.ticket > 0){
            try{
                this.ticketLock.lock();
                if(this.ticket > 0){
                    this.ticket--;
                    System.out.println(Thread.currentThread().getName() + "买到了票，剩余票数。。。" + this.ticket);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }finally {
                this.ticketLock.unlock();
            }
        }
    }

    //同步方法
    public synchronized void sale(){
        if (this.ticket > 0) {
            this.ticket--;
            System.out.println("顾客" + Thread.currentThread().getName() + "买到了票，当前剩余票数。。。" + this.ticket);
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
