package com.example.mode2;

import java.lang.reflect.Proxy;

public class Client {
    public static void main(String[] args) {
//        IShop ning = new Ning();
//        IShop purchasing = new purchaseProxy(ning);
//        purchasing.buy();

        //创建Ning
        IShop ning = new Ning();
        //创建动态代理
        DynamicPurchasing mDynamicPurchasing = new DynamicPurchasing(ning);
        //创建Ning的ClassLoader
        ClassLoader loader = ning.getClass().getClassLoader();
        //动态创建代理类
        IShop purchasing = (IShop) Proxy.newProxyInstance(loader , new Class[]{IShop.class} , mDynamicPurchasing);
        purchasing.buy();
    }
}
