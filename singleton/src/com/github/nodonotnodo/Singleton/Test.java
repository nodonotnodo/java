package com.github.nodonotnodo.Singleton;

public class Test {

    public static void test1(){//Singleton1饿汉式单例
        Singleton1 singleton1 = Singleton1.getSingleton1();
        Singleton1 singleton2 = Singleton1.getSingleton1();
        System.out.println(singleton1==singleton2);
    }

    public static void test2(){//Singleton2懒汉式单例
        Singleton2 singleton1 = Singleton2.getSingleton2();
        Singleton2 singleton2 = Singleton2.getSingleton2();
        System.out.println(singleton1==singleton2);
    }

    public static void test3(){//Singleton3静态代码块饿汉式单例
        Singleton3 singleton1 = Singleton3.getSingleton3();
        Singleton3 singleton2 = Singleton3.getSingleton3();
        System.out.println(singleton1==singleton2);
    }

    public static void test4(){//Singleton4静态内部类单例--推荐用
        Singleton4 singleton1 = Singleton4.getSingleton4();
        Singleton4 singleton2 = Singleton4.getSingleton4();
        System.out.println(singleton1==singleton2);
    }

    public static void test5(){//Singleton5枚举单例--使用者很少
        Singleton5 singleton1 = Singleton5.SINGLETON_5;
        Singleton5 singleton2 = Singleton5.SINGLETON_5;
        System.out.println(singleton1==singleton2);
    }

    public static void test6(){//Singleton6线程安全单例--效率低不推荐使用
        Singleton6 singleton1 = Singleton6.getSingleton6();
        Singleton6 singleton2 = Singleton6.getSingleton6();
        System.out.println(singleton1==singleton2);
    }

    public static void test7(){//Singleton7双检查线程安全懒汉式单例--线程安全推荐使用
        Singleton7 singleton1 = Singleton7.getSingleton7();
        Singleton7 singleton2 = Singleton7.getSingleton7();
        System.out.println(singleton1==singleton2);
    }

    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();
//        test4();
//        test5();
//        test6();
        test7();
    }
}
