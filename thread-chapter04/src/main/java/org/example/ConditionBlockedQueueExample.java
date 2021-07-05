package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class ConditionBlockedQueueExample {

    //表示阻塞队列中的容器
    private List<String> items;
    //元素个数（表示已经添加的元素个数）
    private volatile int size;
    //数组的容量
    private volatile int count;
    private Lock lock=new ReentrantLock();
    //让take方法阻塞 ->wait/notify
    private final Condition notEmpty=lock.newCondition();
    //放add方法阻塞
    private final Condition notFull=lock.newCondition();

    public ConditionBlockedQueueExample(int count){
        this.count=count;
        items=new ArrayList<>(count); //写死了
    }

    //添加一个元素，并且阻塞添加
    public void put(String item) throws InterruptedException {
        lock.lock();
        try{
            if(size>=count){
                System.out.println("队列满了，需要先等一会");
                notFull.await();
            }
            ++size; //增加元素个数
            items.add(item);
            notEmpty.signal();
        }finally {
            lock.unlock();
        }
    }
    public String take() throws InterruptedException {
        lock.lock();
        try{
            if(size==0){
                System.out.println("阻塞队列空了，先等一会");
                notEmpty.await();
            }
            --size;
            String item=items.remove(0);
            notFull.signal();
            return item;
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ConditionBlockedQueueExample cbqe=new ConditionBlockedQueueExample(10);
        //生产者线程
        Thread t1=new Thread(()->{
            Random random=new Random();
            for (int i = 0; i < 1000; i++) {
                String item="item-"+i;
                try {
                    cbqe.put(item); //如果队列满了，put会阻塞
                    System.out.println("生产一个元素："+item);
                    Thread.sleep(random.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        Thread.sleep(100);
        Thread t2=new Thread(()->{
            Random random=new Random();
            for (;;) {
                try {
                    String item=cbqe.take();
                    System.out.println("消费者线程消费一个元素："+item);
                    Thread.sleep(random.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t2.start();
    }

}
