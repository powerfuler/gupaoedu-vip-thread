package com.example.springbootthread11;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class RunExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture cxf=CompletableFuture.supplyAsync(()->"Both")
                .runAfterBoth(CompletableFuture.supplyAsync(()->"Message"),()->{
                    System.out.println("Done");
                });
        System.out.println(cxf.get());
    }
}
