package com.gupaoedu.concurrent;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class ThreadLocalDemo {
    //非线程安全的
    private static final SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static ThreadLocal<DateFormat> dateFormatThreadLocal=new ThreadLocal<>();

    private static DateFormat getDateFormat(){
        DateFormat dateFormat=dateFormatThreadLocal.get(); //从当前线程的范围内获得一个DateFormat
        if(dateFormat==null){
            dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //Thread.currentThread();
            dateFormatThreadLocal.set(dateFormat); //要在当前线程的范围内设置一个simpleDateFormat对象.
        }
        return dateFormat;
    }
    public static Date parse(String strDate) throws ParseException {
        return getDateFormat().parse(strDate);
    }

    public static void main(String[] args) {
        ExecutorService executorService= Executors.newFixedThreadPool(10);
        for (int i = 0; i < 20; i++) {
            executorService.execute(()->{
                try {
                    System.out.println(parse("2021-05-30 20:12:20"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            });

        }
    }
}
