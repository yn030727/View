package com.example.mode2;

public class Client {
    public static void main(String[] args) {
        IShop ning = new Ning();
        IShop purchasing = new purchaseProxy(ning);
        purchasing.buy();
    }
}
