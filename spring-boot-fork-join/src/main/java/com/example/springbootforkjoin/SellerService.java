package com.example.springbootforkjoin;

import com.example.springbootforkjoin.pojo.Seller;
import org.springframework.stereotype.Service;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
@Service
public class SellerService extends AbstractLoadDataProcessor{

    @Override
   public void load(Context context) {
        Seller seller=new Seller();
        seller.setSellerNum(100);
        seller.setTotalNum(1000);
        context.setSeller(seller);
    }
}
