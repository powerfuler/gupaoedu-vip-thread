package org.example;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class ChainExample {

    public static void main(String[] args) {
        FinalRequestProcessor finalRequestProcessor=new FinalRequestProcessor();
        SaveRequestProcessor saveRequestProcessor=new SaveRequestProcessor(finalRequestProcessor);
        saveRequestProcessor.start();
        PrintProcessor printProcessor=new PrintProcessor(saveRequestProcessor);
        printProcessor.start();
        ValidProcessor validProcessor=new ValidProcessor(printProcessor);
        validProcessor.start();
        Request request=new Request();
        request.setName("Mic");
        validProcessor.processRequest(request);
    }
}
