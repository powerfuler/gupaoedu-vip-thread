package com.boot.jdk;

public class SyncUsingWay {
    // 普通方法- 锁对象：我们的对象（new 出来的，谁调用这个方法，锁作用于谁身上）
    public synchronized void SyncMethod() {
        System.out.println("SyncMethod");
    }
    // 静态方法- 锁对象：我们的对象所属的class，全局只有一个。（类型，放到方法区的
    // 包括我们的真正的.class文件的二进制文件都最终加载到了运行时数据区的方法区）
    public synchronized static void StaticSyncMethod(){
        System.out.println("StaticSyncMethod");
    }

    public void method(){
        // 静态代码块
        synchronized (this) {
            System.out.println("method");
        }
    }
}
