package com.example.springbootthread11.demo;

import com.sun.xml.internal.ws.developer.Serialization;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
@Service
public class RepoService {
    public Integer getRepoByGoodsId(Integer goodsId){
        return new Random().nextInt(1000);
    }
}
