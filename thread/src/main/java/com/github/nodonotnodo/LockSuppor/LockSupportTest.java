package com.github.nodonotnodo.LockSuppor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.LockSupport;

/**
 *
 * 前面学习了wait()和notify()方法，但是这个两个方法使用时非常麻烦，必须要有同步代码块或方法才能使用，而且还要注意调用方法的对象一致。
 * 现在我们有一个新的方法来达到它们的效果：LockSupport类
 * 这个类中的静态方法park()和unpark()就能做到wait()和notify()方法做到的事情，而且十分方便。
 *
 */

public class LockSupportTest {

    public static void main(String[] args) {

        MyRunnable runnable = new MyRunnable();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.submit(runnable);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        boolean bool = true;
        while (bool){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(runnable.getThread1() != null){
                System.out.println(runnable.getThread1().getName() + "线程被通知");
                LockSupport.unpark(runnable.getThread1());
                bool = false;
            }
        }

    }

}

class MyRunnable implements Runnable{

    private volatile Thread thread = null;

    final void setThread(){
        this.thread = Thread.currentThread();
    }

    public Thread getThread1() {
        return this.thread;
    }

    @Override
    public void run() {
        int i = 0;
        while(true){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while(this.thread == null){
                setThread();
            }
            i++;
            if(10 == i){
                System.out.println(Thread.currentThread().getName() + "线程等待");
                LockSupport.park();
            }
            System.out.println(Thread.currentThread().getName() + "打印的： i = " + i);
        }
    }
}
