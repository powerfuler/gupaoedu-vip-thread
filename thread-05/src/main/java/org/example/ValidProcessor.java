package org.example;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class ValidProcessor extends Thread implements IRequestProcessor{

    protected IRequestProcessor nextProcessor;

    protected BlockingQueue<Request> requests=new LinkedBlockingQueue<>();

    public ValidProcessor(IRequestProcessor nextProcessor) {
        this.nextProcessor = nextProcessor;
    }

    @Override
    public void processRequest(Request request) {
        requests.add(request);
    }

    @Override
    public void run() {
        while(true){
            try {
                //异步进行请求处理
                Request request=requests.take();
                System.out.println("ValidProcessor："+request);
                if(null!=nextProcessor){
                    nextProcessor.processRequest(request);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
