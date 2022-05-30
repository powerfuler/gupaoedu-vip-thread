package com.example.threaddemo;

// 2：注解使用
public class Apple {
    @FruitProvider(id = 1, name = "陕西红富士集团", address = "陕西省西安市延安路")
    private String appleProvider;

    public void setAppleProvider(String appleProvider) {
        this.appleProvider = appleProvider;
    }

    public String getAppleProvider() {
        return appleProvider;
    }
}