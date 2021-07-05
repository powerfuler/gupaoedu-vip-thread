package org.example;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Lock lock=new ReentrantLock();
        lock.lock();
        try {
            //N个线程被await
            lock.newCondition().await(); //释放了锁
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        lock.unlock();

        //OtherThead-> ThreadB
        lock.newCondition().signalAll();
        System.out.println( "Hello World!" );
    }
}
