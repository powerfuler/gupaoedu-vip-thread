package com.example.springbootthread11;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class ApplyExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture cf=CompletableFuture.supplyAsync(()->"Apply").thenApply(r->{
            return "r"+"Mic";
        });
        System.out.println(cf.get());

        CompletableFuture cf2=CompletableFuture.supplyAsync(()->"Combine")
                .thenCombineAsync(CompletableFuture.supplyAsync(()->"Message"),(r1,r2)->r1+r2);
        System.out.println(cf2.get());
    }
}
