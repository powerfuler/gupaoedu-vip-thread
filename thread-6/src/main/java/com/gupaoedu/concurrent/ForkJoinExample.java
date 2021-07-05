package com.gupaoedu.concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class ForkJoinExample {

    //java8 parallStream

    //针对一个数字，做计算。
    private static final Integer MAX=200;

    static class CalcForJoinTask extends RecursiveTask<Integer> {
        private Integer startValue; //子任务的开始计算的值
        private Integer endValue; //子任务结束计算的值

        public CalcForJoinTask(Integer startValue, Integer endValue) {
            this.startValue = startValue;
            this.endValue = endValue;
        }
        @Override
        protected Integer compute() {
            //如果当前的数据区间已经小于MAX了，那么接下来的计算不需要做拆分
            if(endValue-startValue<MAX){
                System.out.println("开始计算：startValue:"+startValue+" ; endValue:"+endValue);
                Integer totalValue=0;
                for(int i=this.startValue;i<=this.endValue;i++){
                    totalValue+=i;
                }
                return totalValue;
            }
            CalcForJoinTask subTask=new CalcForJoinTask(startValue,(startValue+endValue)/2);
            subTask.fork();
            CalcForJoinTask calcForJoinTask=new CalcForJoinTask((startValue+endValue)/2+1,endValue);
            calcForJoinTask.fork();
            return subTask.join()+calcForJoinTask.join();
        }
    }

    public static void main(String[] args) {
        CalcForJoinTask calcForJoinTask=new CalcForJoinTask(1,10000);
        ForkJoinPool pool=new ForkJoinPool();
        ForkJoinTask<Integer> taskFuture=pool.submit(calcForJoinTask);
        try {
            Integer result=taskFuture.get();
            System.out.println("result:"+result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}
