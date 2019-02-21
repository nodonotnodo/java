package com.github.nodonotnodo.modelPC;

/**
 *
 * notify()方法是在“待执行线程队列”中随机挑出一个线程进行通知，被通知的线程进入就绪状态。
 * notify()方法是将所有"待执行线程队列"中的线程进行通知，被通知的线程进入就绪状态
 * ！！！注意，notify()方法必须在同步块或同步方法中使用，它通知的wait()线程只是和它有相同锁的线程
 * ！！！否则会抛出IllegalMonitorStateException异常（非法监控状态异常）
 *
 */

public class NotifyTest {

    public static void main(String[] args) {

        String string = "对象";

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (string){
                    for(int i=0;;i++){
                        if (i == 10) {
                            try {
                                Thread.sleep(500);
                                System.out.println(Thread.currentThread().getName() + "线程等待");
                                string.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        } else {
                            System.out.println(Thread.currentThread().getName() + "" + i);
                        }
                    }
                }
            }
        },"Thread-wait");
        thread1.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (string){string.notify();}
    }

}
