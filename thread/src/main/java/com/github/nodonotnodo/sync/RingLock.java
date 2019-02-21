package com.github.nodonotnodo.sync;

/**
 *
 * 死锁：当两个或多个线程彼此都在等待对方执行完同步块，以便自己可以执行同步块时，
 *       那么它们会一直等待下去，这就是死锁（环）。
 *
 */

public class RingLock {

    public static void main(String[] args) {

        Water water = new Water();
        Glass glass = new Glass();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (water){
                    System.out.println(Thread.currentThread().getName() + ": 我有水，我要杯子");
                    synchronized (glass){
                        System.out.println(Thread.currentThread().getName() + ": 我拿到杯子了！！！");
                    }
                }
            }
        },"李一");

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (glass){
                    System.out.println(Thread.currentThread().getName() + ": 我有杯子，我要水");
                    synchronized (water){
                        System.out.println(Thread.currentThread().getName() + ": 我拿到水了！！！");
                    }
                }
            }
        },"王二");

        thread1.start();
        thread2.start();

    }

}

class Water{

    private String water = "水";

    public String getWater() {
        return water;
    }
}

class Glass{

    private String glass = "杯子";

    public String getGlass() {
        return glass;
    }
}
