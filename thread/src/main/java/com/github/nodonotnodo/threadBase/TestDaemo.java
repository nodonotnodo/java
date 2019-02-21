package com.github.nodonotnodo.threadBase;

public class TestDaemo {

    public static void main(String[] args) {

        MyRunnable myRunnable = new MyRunnable();
        Thread daemo = new Thread(myRunnable,"Thread-Daemo");
        //将Thread-Daemo线程设置为守护线程，可以看到它的run方法应该是一个死循环
        //但是在main()主线程结束后，Daemo线程却也结束了。
        /**
         * 实际上守护线程是一种伴生线程，只要JVM中还有非守护线程（用户线程）没有结束，守护线程就不会结束。
         */
        daemo.setDaemon(true);
        daemo.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
