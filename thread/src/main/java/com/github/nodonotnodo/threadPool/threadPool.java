package com.github.nodonotnodo.threadPool;

import java.util.concurrent.*;

/**
 *
 * 线程池创建线程
 *
 *      为什么要用线程池创建线程？
 *      1.我们自己每一次创建一个线程，终止一个线程都会消耗资源，所以线程池中的线程可以多次使用来降低资源消耗
 *      2.当任务创建后，不用等待线程的创建就能立即执行，更加高效
 *      3.使用线程池可以进行线程分配，调度和监控
 *
 *      如何创建线程？
 *      1.通过实例化ThreadPoolExecutor类来创建线程池
 *      2.创建无大小限制的线程池：ExecutorService newCachedThreadPool
 *      3.创建固定大小的线程池：ExecutorService newFixedThreadPool
 *      4.创建单线程池：ExecutorService newSingleThreadExecutor()
 *
 */

public class threadPool {

    public static void coded1(){

        /*
        public ThreadPoolExecutor(int corePoolSize,——核心线程数量
                              int maximumPoolSize,——最大线程数量
                              long keepAliveTime,——存在周期的时间：数值
                              TimeUnit unit,——存在周期的时间：单位
                              BlockingQueue<Runnable> workQueue——任务队列（决定了任务队列的类型）)
         */

        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(3,
                3,
                0,
                TimeUnit.DAYS,
                new ArrayBlockingQueue<Runnable>(6));

        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                for(int i=0; i<3; i++){
                    System.out.println(Thread.currentThread().getName() + "线程打印： i = " + i);
                }

            }
        };

        /*
        execute()和submit()两个方法会向线程池中添加任务
        execute()用于提交不需要返回值的任务。
        submit()既可以提交不需要返回值的任务，也可以提交需要返回值的任务。
         */
        threadPool.submit(runnable);
        threadPool.execute(runnable);
        Future future1 = threadPool.submit(new Callable<String>(

        ) {
            @Override
            public String call() throws Exception {
                return Thread.currentThread().getName() + "线程执行完毕";
            }
        });
        Future future2 = threadPool.submit(runnable,"线程执行完毕");
        try {
            System.out.println(future1.get());
            System.out.println(future2.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            if(!threadPool.isShutdown())
                threadPool.shutdown();
        }
    }

    public static void code2(){
        /*
        public static ExecutorService newCachedThreadPool() {
        return new ThreadPoolExecutor(0, Integer.MAX_VALUE,——核心线程池为0，最大线程池为int最大值（实际上无法创建这么多线程）
                                      60L, TimeUnit.SECONDS,——存在周期为60s（存在周期很短，因为如果存在周期过长，不断创建线程有可能耗尽资源）
                                      new SynchronousQueue<Runnable>())——没有容量的队列，任务提交就立即创建线程
         */
        ExecutorService executorService = Executors.newCachedThreadPool();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "线程打印结果");
            }
        };
        try{
            while(true){
                executorService.submit(runnable);
            }
        }finally {
            if(!executorService.isShutdown())
                executorService.shutdown();
        }
    }

    public static void code3(){
        /*
        public static ExecutorService newFixedThreadPool(int nThreads) {
        return new ThreadPoolExecutor(nThreads, nThreads,——核心线程数和最大线程数相等，即只有核心线程数
                                      0L, TimeUnit.MILLISECONDS,——存在周期0毫秒，实际上此处的存在周期无意义
                                      new LinkedBlockingQueue<Runnable>())——无界队列
         */
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "线程打印的");
            }
        };

        try{
            while(true){
                executorService.submit(runnable);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if(!executorService.isShutdown()){
                executorService.shutdown();
            }
        }
    }

    public static void code4(){
        /*
        public static ExecutorService newSingleThreadExecutor() {
        return new FinalizableDelegatedExecutorService
            (new ThreadPoolExecutor(1, 1,——核心线程数和最大线程数都为1
                                    0L, TimeUnit.MILLISECONDS,——存在周期0毫秒，实际上此处存在周期无意义
                                    new LinkedBlockingQueue<Runnable>()))——无界队列
         */
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "线程打印的");
            }
        };

        try{
            while(true){
                executorService.submit(runnable);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if(!executorService.isShutdown()){
                executorService.shutdown();
            }
        }
    }

    public static void main(String[] args) {

        //new ThreadPoolExcutor类创建线程池
//        coded1();

        //newCachedThreadPool()创健线程池
//        code2();

        //newFixedThreadPool()创建线程池
//        code3();

        //newSingleThreadExecutor()创建线程池
        code4();

    }

}
