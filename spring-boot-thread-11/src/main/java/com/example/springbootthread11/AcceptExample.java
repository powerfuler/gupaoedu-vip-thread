package com.example.springbootthread11;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class AcceptExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /*CompletableFuture<Void> cf=CompletableFuture.supplyAsync(()->"Then Accept Message")
                .thenAcceptAsync((result)->System.out.println(result));*/

        CompletableFuture<String> task1=CompletableFuture.supplyAsync(()->"AcceptBot");
        CompletableFuture<String> task2=CompletableFuture.supplyAsync(()->"Message");

        // r1=task1.done();
        // r2=task2.done();
        // future.callable(new Callable(r1,r2){

        // })

        //future.callable((r1,r2)->{

        // })


        task1.thenAcceptBoth(task2,(r1,r2)->{
            System.out.println("result: "+r1+r2);
        }).get();
    }
}
