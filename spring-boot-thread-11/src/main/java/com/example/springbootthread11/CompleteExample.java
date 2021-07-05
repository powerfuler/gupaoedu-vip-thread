package com.example.springbootthread11;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class CompleteExample {
    static class ClientThread implements Runnable{
        private CompletableFuture completableFuture;
        public ClientThread(CompletableFuture completableFuture) {
            this.completableFuture = completableFuture;
        }
        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName()+":"+
                        completableFuture.get()); //阻塞
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        CompletableFuture cf=new CompletableFuture();
        new Thread(new ClientThread(cf),"t1").start();
        new Thread(new ClientThread(cf),"t2").start();
        //执行某段逻辑.
        //normal
        cf.complete("Finish");
        //exception
//        cf.completeExceptionally(e);
    }
}
