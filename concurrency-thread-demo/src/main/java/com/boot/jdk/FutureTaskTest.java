package com.boot.jdk;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class FutureTaskTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        TaskTest task = new TaskTest();
        // 放到线程池中执行
//        Future submit = Executors.newSingleThreadExecutor().submit(task);
//        System.out.println(submit.get());
        FutureTask<String> futureTask = new FutureTask<>(task);
        futureTask.run();
        System.out.println(futureTask.get());
    }

    private static class TaskTest implements Callable {
        @Override
        public String call() throws Exception {
            Thread.sleep(5000);
            return "Hello world!";
        }
    }
}
