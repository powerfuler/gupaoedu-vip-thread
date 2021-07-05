package org.example.count;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class ApplicationStartup {

    private static List<BaseHealthChecker> services;
    private static CountDownLatch countDownLatch=new CountDownLatch(2);

    static{
        services=new ArrayList<>();
        services.add(new CacheHealthChecker(countDownLatch));
        services.add(new DatabaseHealthChecker(countDownLatch));
    }

    private final static ApplicationStartup INSTANCE=new ApplicationStartup();

    private ApplicationStartup(){}

    public static ApplicationStartup getInstance(){
        return INSTANCE;
    }

    public static boolean checkExternalServices() throws InterruptedException {
        for(BaseHealthChecker bh:services){
            new Thread(bh).start(); //针对每个服务采用线程来执行
        }
        countDownLatch.await();
        return true;
    }
}
