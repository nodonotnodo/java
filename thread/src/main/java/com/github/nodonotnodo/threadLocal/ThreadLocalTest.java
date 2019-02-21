package com.github.nodonotnodo.threadLocal;

public class ThreadLocalTest {

    /**
     * ThreadLocal类型的变量，在一个线程中设置值，不影响其它线程中该变量的值。
     * 也就是说ThreadLocal类型的变量在每个线程中是独立的。
     */

    private static ThreadLocal<String> local = new ThreadLocal<>();

    public static void main(String[] args) {

        local.set("原始的值");
        System.out.println(Thread.currentThread().getName() + "打印的值：..." + local.get());

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                local.set("子线程修改后的值");
                System.out.println(Thread.currentThread().getName() + "打印的值：..." + local.get());
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("子进程修改后" + Thread.currentThread().getName() + "打印的值:..." + local.get());

    }

}
