package com.github.nodonotnodo.Singleton;

public class Singleton4 {//静态内部类单例--推荐用

    private Singleton4(){

    }

    public static class Inter{
        static final Singleton4 singleton4 = new Singleton4();
    }

    public static Singleton4 getSingleton4(){
        return Inter.singleton4;
    }
}
