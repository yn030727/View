package com.example.mode2;

public class Client2 {
    public static void main(String[] args) {
        Goods goods1 = GoodsFactroy.getGoods("iphone7");
        goods1.showGoodsPrice("32G");

        Goods goods2 = GoodsFactroy.getGoods("iphone7");
        goods2.showGoodsPrice("32G");

        Goods goods3 = GoodsFactroy.getGoods("iphone7");
        goods3.showGoodsPrice("128G");
    }
}
