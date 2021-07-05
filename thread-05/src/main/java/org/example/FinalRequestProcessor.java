package org.example;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class FinalRequestProcessor implements IRequestProcessor{

    @Override
    public void processRequest(Request request) {
        System.out.println("FinalRequestProcessor:"+request);
    }
}
