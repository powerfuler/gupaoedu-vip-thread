package com.example.springbootthread11;

import sun.rmi.runtime.Log;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class ExceptionExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture cf=CompletableFuture.supplyAsync(()->{
            throw new RuntimeException("Occur Exception");
        }).exceptionally(e->{
            System.out.println(e);
            return "Exceptionally";
        });/*.handleAsync((r,th)->{
           return th!=null?"出现异常":r;
        });*/
        System.out.println(cf.get());
    }
}
