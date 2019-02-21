package com.github.nodonotnodo.threadBase;

public class PriorityTest {

    public static void main(String[] args) {

        //线程优先级默认为5
        //线程的优先级具有继承性，子线程的优先级和父线程优先级相等
        System.out.println(Thread.currentThread().getPriority());

        MyRunnable myRunnable = new MyRunnable();

        Thread parent = new Thread(myRunnable,"Thread-Parent");
        System.out.println(parent.getName() + "的优先级是" + parent.getPriority());
        parent.setPriority(10);
        System.out.println(parent.getName() + "此时的优先级是" + parent.getPriority());
        parent.start();
    }

}
