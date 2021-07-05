package com.example.springbootthread11;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class CompletableFutureExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
       /* CompletableFuture cf=CompletableFuture.runAsync(()->{
            System.out.println(Thread.currentThread().getName()+":异步执行一个任务");
        });
        System.out.println(cf.get());  //通过阻塞获取执行结果.*/

        /*CompletableFuture cf1= CompletableFuture.supplyAsync(()->"Hello World");
        cf1.thenAccept(rs->{
            System.out.println(rs);
        });*/

        //() 表示形参，
        CompletableFuture cf1= CompletableFuture.supplyAsync(()->"Hello World").thenAccept(xx->{
            System.out.println(xx);
        });
        //继续做其他事情。
    }
}
