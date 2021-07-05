package com.example.springbootforkjoin;

import com.example.springbootforkjoin.pojo.Item;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 *
 * 表示一个聚合任务
 **/
@Service
public class ItemTaskForkJoinDataProcessor extends AbstractLoadDataProcessor implements ApplicationContextAware {

    ApplicationContext applicationContext;

    private List<AbstractLoadDataProcessor> taskDataProcessors=new ArrayList<>();

    @Override
    public void load(Context context) {
        taskDataProcessors.forEach(abstractLoadDataProcessor->{
            abstractLoadDataProcessor.setContext(this.context);
            abstractLoadDataProcessor.fork();//创建一个fork task
        });
    }
    @Override
    public Context getContext() {
        //ForkJoinTask::join  java8方法引用
        // * 构造引用
        // * 静态方法引用
        // * 实例方法引用
        this.taskDataProcessors.forEach(ForkJoinTask::join);
        return super.getContext();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
        taskDataProcessors.add(applicationContext.getBean(CommentService.class));
        taskDataProcessors.add(applicationContext.getBean(ItemService.class));
        taskDataProcessors.add(applicationContext.getBean(ComplexTradeTaskService.class));
    }
}
