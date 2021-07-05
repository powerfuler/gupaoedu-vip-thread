package com.gupaoedu.concurrent;

import java.lang.ref.WeakReference;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class ReferenceExample {
    static Object object=new Object();
    public static void main(String[] args) {
//        Object strongRef=object; //强引用
        WeakReference<Object> objectWeakReference=new WeakReference<>(object);
        object=null;
        System.gc();
        System.out.println(objectWeakReference.get());
    }
}
