package com.github.nodonotnodo.Singleton;

public class Singleton2 {//懒汉式单例--在多线程中不安全，不可用

    private static Singleton2 singleton2;

    private Singleton2(){

    }

    public static Singleton2 getSingleton2(){
        if(null == Singleton2.singleton2){
            Singleton2.singleton2 = new Singleton2();
        }
        return Singleton2.singleton2;
    }
}
