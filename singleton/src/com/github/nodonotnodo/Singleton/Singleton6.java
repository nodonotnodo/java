package com.github.nodonotnodo.Singleton;

public class Singleton6 {//线程安全懒汉式单例
    private static Singleton6 singleton6;

    private Singleton6(){

    }

    public static synchronized Singleton6 getSingleton6(){
        return Singleton6.singleton6;
    }
}
