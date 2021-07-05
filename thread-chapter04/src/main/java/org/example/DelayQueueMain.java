package org.example;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class DelayQueueMain {

    private static DelayQueue<DelayQueueExampleTask> delayQueue=new DelayQueue();
    public static void main(String[] args) {
        delayQueue.offer(new DelayQueueExampleTask("1001",1000));
        delayQueue.offer(new DelayQueueExampleTask("1002",5000));
        delayQueue.offer(new DelayQueueExampleTask("1003",3000));
        delayQueue.offer(new DelayQueueExampleTask("1004",6000));
        delayQueue.offer(new DelayQueueExampleTask("1005",2000));
        delayQueue.offer(new DelayQueueExampleTask("1006",8000));
        delayQueue.offer(new DelayQueueExampleTask("1007",3000));
        while(true){
            try {
                DelayQueueExampleTask task=delayQueue.take();
                System.out.println(task);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
