package com.example.springbootforkjoin;

import com.example.springbootforkjoin.pojo.Comment;
import com.example.springbootforkjoin.pojo.Item;
import com.example.springbootforkjoin.pojo.Seller;
import com.example.springbootforkjoin.pojo.Shop;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class Context {

    private Item item;  //商品
    private Comment comment; //评论
    private Seller seller; //销售信息
    private Shop shop;  //店铺信息

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    @Override
    public String toString() {
        return "Context{" +
                "item=" + item +
                ", comment=" + comment +
                ", seller=" + seller +
                ", shop=" + shop +
                '}';
    }
}
