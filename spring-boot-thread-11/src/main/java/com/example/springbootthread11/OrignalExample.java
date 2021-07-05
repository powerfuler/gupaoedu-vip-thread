package com.example.springbootthread11;

import java.util.concurrent.CompletableFuture;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class OrignalExample {

    public static void main(String[] args) {
        CompletableFuture<String> baseFuture=CompletableFuture.completedFuture("Base Future");
        baseFuture.thenApply(r->"Then Apply:"+r); //UniCompletion ——>

        baseFuture.thenAccept(r-> System.out.println(r)).thenAccept(Void-> System.out.println("no return"));
        baseFuture.thenApply(r->"Apply Message").thenAccept(r-> System.out.println("result:"+r));
    }
}
