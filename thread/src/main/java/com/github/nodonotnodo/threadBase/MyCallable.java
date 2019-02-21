package com.github.nodonotnodo.threadBase;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<String> {

    @Override
    public String call() throws Exception {
        System.out.println("线程"+Thread.currentThread().getName()+"的打印");
        return "线程"+Thread.currentThread().getName()+"返回的数据";
    }

}
