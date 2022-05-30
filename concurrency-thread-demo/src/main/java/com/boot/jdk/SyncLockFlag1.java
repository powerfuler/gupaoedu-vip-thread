package com.boot.jdk;

import lombok.SneakyThrows;
import org.openjdk.jol.info.ClassLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

public class SyncLockFlag1 {
    static Thread A;
    static Thread B;
    static int loopFlag = 20;
    public static void main(String[] args) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        final List<Object> list = new ArrayList<>();
        A = new Thread() {
            @SneakyThrows
            @Override
            public void run() {
                for (int i = 0; i < 20; i++) {
                    Object a = new Object();
                    list.add(a);
                    System.out.println("AAAA加锁前第 " +  (i+1) + "次" + ClassLayout.parseInstance(a).toPrintable());
                    synchronized (a) {
                        System.out.println("AAAA加锁中第 " +  (i+1) + "次" + ClassLayout.parseInstance(a).toPrintable());
                    }
                    System.out.println("AAAA加锁后第 " +  (i+1) + "次" + ClassLayout.parseInstance(a).toPrintable());
                }
                System.out.println("============线程A 都是偏向锁=============");
                //防止竞争 执行完后唤醒线程B
                LockSupport.unpark(B);
            }
        };
        B = new Thread() {
            @Override
            public void run() {
                //防止竞争 先睡眠线程B
                LockSupport.park();
                for (int i = 0; i < 20; i++) {
                    Object a = list.get(i);
                    //因为从list当中拿出都是偏向线程A
                    System.out.println("线程BBBB加锁前第 " + (i+1) + " 次" + ClassLayout.parseInstance(a).toPrintable());
                    synchronized (a) {
                        //20次撤销偏向锁偏向线程A；然后升级轻量级锁指向线程B线程栈当中的锁记录
                        //后面的发送批量偏向线程B
                        System.out.println("线程BBBB加锁中第 " + (i+1) + " 次" + ClassLayout.parseInstance(a).toPrintable());
                    }
                    //因为前19次是轻量级锁，释放之后为无锁不可偏向
                    //但是第20次是偏向锁 偏向线程B 释放之后依然是偏向线程B
                    System.out.println("线程BBBB加锁后第 " + (i+1) + " 次" + ClassLayout.parseInstance(a).toPrintable());
                }
                System.out.println("新产生的对象" + ClassLayout.parseInstance(new Object()).toPrintable());
            }
        };
        A.start();
        B.start();
    }
}