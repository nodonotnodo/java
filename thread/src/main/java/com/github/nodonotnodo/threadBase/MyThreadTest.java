package com.github.nodonotnodo.threadBase;

public class MyThreadTest {

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        //实际上使用start()方法将会运行一个进程，此时这个进程的运行是与main()方法无关而独立的
        //所以线程对象的run方法与main()方法中的打印的执行先后顺序是没有联系的
        myThread.start();
        //这是一个很简单的对象调用方法，此时run()方法的执行是在main()线程中的，要按照正常的执行顺序执行
        myThread.run();
        System.out.println("这是main()方法的打印");
        
    }

}
