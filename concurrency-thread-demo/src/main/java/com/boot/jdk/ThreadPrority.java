package com.boot.jdk;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.SneakyThrows;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

public class ThreadPrority {
    public static void main(String[] args) throws JsonProcessingException {
        System.out.println(Thread.currentThread().getName()
                +"("+Thread.currentThread().getPriority()+ ")");

        Thread t1=new MyThread("t1");    // 新建t1
        Thread t2=new MyThread("t2");    // 新建t2
        t1.setPriority(1);                // 设置t1的优先级为1
        t2.setPriority(10);                // 设置t2的优先级为10
        t1.start();                        // 启动t1
        t2.start();                        // 启动t2
    }
}
class MyThread extends Thread{
    public MyThread(String name) {
        super(name);
    }
    @SneakyThrows
    public void run(){
        for (int i=0; i<5; i++) {
            System.out.println(Thread.currentThread().getName()
                    +"("+Thread.currentThread().getPriority()+ ")"
                    +", loop "+i);
        }
//        Thread.sleep(100000);
    }
};


