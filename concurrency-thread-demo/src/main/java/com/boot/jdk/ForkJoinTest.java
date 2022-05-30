package com.boot.jdk;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.atomic.AtomicInteger;

public class ForkJoinTest extends RecursiveTask<Integer> { //需要返回结果
    private static final int THRESHOLD = 2; // 最多几个数字做子任务啊？
    private int start; //从那块相加。举例：从1 开始加
    private int end;// 加到几？ 加到 4

    public ForkJoinTest(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() { //拆分任务，获取结果
        System.out.println("======================");
        int sum = 0;
        // 如果任务足够小就计算任务
        boolean canCompute = (end - start) <= THRESHOLD;
        if (canCompute) {
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            // 如果任务大于阈值，就分裂成两个子任务计算
            int middle = (start + end) / 2;
            ForkJoinTest leftTask = new ForkJoinTest(start, middle);
            ForkJoinTest rightTask = new ForkJoinTest(middle + 1, end);
            // 执行子任务
            leftTask.fork();
            rightTask.fork();
            // 等待子任务执行完，并得到其结果
            int leftResult = leftTask.join();
            int rightResult = rightTask.join();
            // 合并子任务
            sum = leftResult + rightResult;
        }
        return sum;
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        // 生成一个计算任务，负责计算1+2+3+4
        ForkJoinTest task = new ForkJoinTest(1, 100);
        // 执行一个任务
        Future<Integer> result = forkJoinPool.submit(task);
        try {
            System.out.println(result.get());
        } catch (InterruptedException e) {
        } catch (ExecutionException e) {
        }
    }
}
