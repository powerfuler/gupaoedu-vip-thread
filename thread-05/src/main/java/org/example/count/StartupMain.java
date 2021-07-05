package org.example.count;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class StartupMain {
    public static void main(String[] args) {
        try {
            ApplicationStartup.checkExternalServices();
        } catch (InterruptedException e) {
            //有问题了.
        }
        System.out.println("服务启动成功");
    }
}
