package org.example.count;

import java.util.concurrent.CountDownLatch;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class CacheHealthChecker extends BaseHealthChecker{

    private CountDownLatch countDownLatch;

    public CacheHealthChecker(CountDownLatch countDownLatch) {
        super("CacheHealthChecker");
        this.countDownLatch=countDownLatch;
    }

    @Override
    public void verifyService() throws Exception {
        System.out.println("Checking:"+this.getServiceName());
        try {
            Thread.sleep(1000);
            // 如果检查失败，throw RuntimeException()
        } catch (Exception e) {
            throw e;
        }
        countDownLatch.countDown();
        System.out.println(this.getServiceName()+" 健康状态正常");
    }
}
