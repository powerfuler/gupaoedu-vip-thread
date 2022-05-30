package com.boot.jdk;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class Message implements Delayed {
    private String name;
    private long time;

    public Message(String name, long time, TimeUnit timeUnit) {
        this.name = name;
        this.time = System.currentTimeMillis() + timeUnit.toMillis(time);
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return time - System.currentTimeMillis();
    }

    @Override
    public int compareTo(Delayed o) {
        //谁先到期谁先执行，通过 time 进行一个比较优先级
        Message msg = (Message) o;
        long timeDiff = this.time - msg.time;
        if(timeDiff <= 0) {
            return -1;
        }
        return 1;
    }

    @Override
    public String toString() {
        return name + " - " + time;
    }
}
