package com.github.nodonotnodo.Singleton;

public class Singleton1 {//饿汉式单例

    private final static Singleton1 singleton1 = new Singleton1();

    private Singleton1(){

    }

    public static Singleton1 getSingleton1(){
        return Singleton1.singleton1;
    }
}
