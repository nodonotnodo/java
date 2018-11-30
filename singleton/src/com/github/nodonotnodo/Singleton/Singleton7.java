package com.github.nodonotnodo.Singleton;

public class Singleton7 {//双检查线程安全懒汉式单例
    private static volatile Singleton7 singleton7;

    private Singleton7(){

    }

    public static Singleton7 getSingleton7(){
        if(null == Singleton7.singleton7){
            synchronized(Singleton7.class){
                if(null == Singleton7.singleton7){
                    Singleton7.singleton7 = new Singleton7();
                }
            }
        }
        return Singleton7.singleton7;
    }
}
