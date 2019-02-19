package com.github.nodonotnodo;

public class MyRunnable implements Runnable {

    private volatile boolean flag = true;

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
        int i = 0;
        while(true){
            try {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread().getName() + "线程终止");
                    break;
                }
                System.out.println(Thread.currentThread().getName() + ": " + i++ + Thread.currentThread().isInterrupted());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println(Thread.currentThread().isInterrupted());
                return;
            }


        }
    }

}
