package com.example.springbootforkjoin;

import com.example.springbootforkjoin.pojo.Item;
import org.springframework.stereotype.Service;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
@Service
public class ItemService extends AbstractLoadDataProcessor{
    @Override
    public void load(Context context) {
        Item item=new Item();
        item.setNum(100);
        item.setProductName("键盘");
        context.setItem(item);
    }
}
