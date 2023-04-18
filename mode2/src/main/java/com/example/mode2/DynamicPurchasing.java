package com.example.mode2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicPurchasing implements InvocationHandler {

    private Object object;
    public DynamicPurchasing(Object object){
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = method.invoke(object , args);
        if(method.getName().equals("buy")){
            System.out.println("Ning在买东西");
        }
        return result;
    }
}
