package com.example.springbootthread11.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
@RestController
public class GoodsController {

    @Autowired
    GoodsService goodsService;

    @Autowired
    CommentService commentService;

    @Autowired
    RepoService repoService;

    @GetMapping("/goods")
    public List<Goods> goods() throws ExecutionException, InterruptedException {
        //requstThread ->
        //另外的线程在执行
        CompletableFuture<List<Goods>> goodsFuture=CompletableFuture
                .supplyAsync(()->goodsService.queryGoods());
        CompletableFuture cf=goodsFuture.thenApplyAsync(goods->{
            goods.stream().map(goods1 -> CompletableFuture.supplyAsync(()->{
                goods1.setRepo(repoService.getRepoByGoodsId(goods1.getId()));
                return goods1;
            }).thenCompose(goods2 -> CompletableFuture.supplyAsync(()->{
                goods2.setComment(commentService.getCommentsByGoodsId(goods2.getId()));
                return goods2;
            }))).toArray(size->new CompletableFuture[size]);
            return goods;
        });
        // 。TODO 。//

        return (List<Goods>)cf.handleAsync((goods,th)->th!=null?"系统繁忙":goods).get();
    }
}
