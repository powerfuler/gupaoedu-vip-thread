package com.example.springbootforkjoin;

import java.util.concurrent.RecursiveAction;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public abstract class AbstractLoadDataProcessor extends RecursiveAction implements ILoadDataProcessor{

    protected  Context context;

    @Override
    protected void compute() {
        load(context); //调用子类的具体实现
    }

    public Context getContext() {
        this.join(); //得到一个聚合的结果
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
