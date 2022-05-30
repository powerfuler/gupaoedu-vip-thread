package com.boot.jdk;

import org.openjdk.jol.info.ClassLayout;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class SyncLockFlag {
    // 当我们开启了偏向锁，并且没有延迟开启的时候，新创建的对象的mark word 默认就是偏向锁状态的markword。
    // 只不过这个时候，因为没有现成争抢，除了我们的锁标志为和是否为偏向锁标志位，其他的位数都是0
    static MyObject myobject = new MyObject();
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=====================未偏向线程的偏向锁============================");
        System.out.println(ClassLayout.parseInstance(myobject).toPrintable());
        HashMap map = new HashMap();
        map.put(myobject,""); //隐式的调用了hashcode方法
        synchronized (myobject) {
            System.out.println("=====================偏向锁============================");
            System.out.println(ClassLayout.parseInstance(myobject).toPrintable());
        }
    }
    static class MyObject{
    }
}

