package com.example.springbootthread11.demo;

import java.math.BigDecimal;
import java.util.List;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic: 2082233439
 * http://www.gupaoedu.com
 **/
public class Goods {
    private Integer id;
    private String name;
    private BigDecimal price;
    private Integer repo;
    private Integer buyerNum;
    private List<String> comment;

    public Goods(Integer id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getRepo() {
        return repo;
    }

    public void setRepo(Integer repo) {
        this.repo = repo;
    }

    public Integer getBuyerNum() {
        return buyerNum;
    }

    public void setBuyerNum(Integer buyerNum) {
        this.buyerNum = buyerNum;
    }

    public List<String> getComment() {
        return comment;
    }

    public void setComment(List<String> comment) {
        this.comment = comment;
    }
}
