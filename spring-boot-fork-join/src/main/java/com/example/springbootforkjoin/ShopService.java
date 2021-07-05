package com.example.springbootforkjoin;

import com.example.springbootforkjoin.pojo.Shop;
import org.springframework.stereotype.Service;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
@Service
public class ShopService extends AbstractLoadDataProcessor{
    @Override
    public void load(Context context) {
        Shop shop=new Shop();
        shop.setName("咕泡小店");
        context.setShop(shop);
    }
}
