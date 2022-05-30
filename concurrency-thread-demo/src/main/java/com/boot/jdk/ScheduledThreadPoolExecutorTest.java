package com.boot.jdk;

import lombok.SneakyThrows;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolExecutorTest {
    public static void main(String[] args) {
        // 创建 ScheduledThreadPool的方式及源码
        // ScheduledThreadPool 其实是我们 ThreadPoolExecutor的一个子类，所以构造函数使用的是 ThreadPool的
        // 但是，它使用的是 delayQueue   new DelayedWorkQueue()
        // 这两种创建方式有什么不同呢？Executors.newScheduledThreadPool(5);调用的方法也是 new ScheduledThreadPoolExecutor(5);
        // ScheduledThreadPoolExecutor 是我们的 ScheduledExecutorService 的一个子类
        // 建议选择第一种创建方式 和 第三种创建方式。更倾向于第三种创建方式。
//        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(5);
//        ScheduledExecutorService service = Executors.newScheduledThreadPool(5);
        ScheduledThreadPoolExecutor pool
                = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(5);
        // schedule 方法只适用于 一次性的执行。一个任务只执行一次就完事儿了。
//        pool.schedule(new STask(1), 5, TimeUnit.SECONDS);

        // 如果我想要重复执行一个任务。怎么办？
        // 这个方法是以固定的频率进行循环执行。5s以后开始第一次执行，之后每2秒再次执行一次。
        // 这个方法不会考虑前一次执行的时间。
        // 如果第一次执行的时间超过了时间间隔，那么不可以在预期的时间执行第二次。
        //因为： 我们task 是周期执行的，每次执行完一次之后，我们的ScheduledThreadPoolExecutor会从新计算下次的执行时间
        // 然后将下次的执行时间修改完后再次 add 到我们的 delayQUEUE 里。现在如果是一个 while循环，那么永远不能执行结束
        //我们的ScheduledThreadPoolExecutor就没有办法再次的从新计算时间，并且从新添加到我们的delayqueue中。
        pool.scheduleAtFixedRate(new STask(1), 5, 5, TimeUnit.SECONDS);

        //5s以后开始第一次执行，执行完毕之后 2s 后开始执行第二次； 第二次执行完成后，2s后执行第三次。
        // 考虑前一次执行的时间，只有前一次执行完成之后，才会计算2s的时间间隔。
//        pool.scheduleWithFixedDelay(new STask(1), 5, 5, TimeUnit.SECONDS);
    }

    private static class STask implements Runnable {
        private int taksNum;
        public STask(int taksNum) {
            this.taksNum = taksNum;
        }
        @SneakyThrows
        @Override
        public void run() {
            System.out.println("Scheduled task is running! taskNum = " + taksNum);
            Thread.sleep(6000);
        }
    }
}
