package com.github.nodonotnodo.modelPC;

/**
 *
 * 可以看出wait()方法会使线程等待（阻塞），同时会释放锁。
 *！！！注意：wait()方法只能在同步块或同步方法中使用。
 *！！！否则会抛出IllegalMonitorStateException异常（非法监控状态异常）
 */

public class WaitTest {

    public static void main(String[] args) {

        String string = "对象";

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (string){
                    for(int i=0;;i++){
                        if (i == 10) {
                            try {
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

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (string){
                    System.out.println(Thread.currentThread().getName() + "线程获得对象锁" + string);
                }
            }
        },"Thread-test");

        thread1.start();
        thread2.start();

    }

}
