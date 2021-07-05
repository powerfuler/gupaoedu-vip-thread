package com.example.springbootthread11.demo;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
@Service
public class CommentService {

    public List<String> getCommentsByGoodsId(Integer goodsId){
        return Arrays.asList("好","一般","非常好");
    }
}
