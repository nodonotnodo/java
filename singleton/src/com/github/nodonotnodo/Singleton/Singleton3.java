package com.github.nodonotnodo.Singleton;

public class Singleton3 {//静态代码块饿汉式

    private static Singleton3 singleton3;

    {
        singleton3 = new Singleton3();
    }

    private Singleton3(){

    }

    public static Singleton3 getSingleton3(){
        return Singleton3.singleton3;
    }
}
