package com.example.a2thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Alipay {
    private double[] accounts;
    private Lock alipayLock;
    //条件对象
    private Condition condition;

    public Alipay(int n , double money){
        accounts = new double[n];
        alipayLock = new ReentrantLock();
        //初始化条件对象
        condition = alipayLock.newCondition();

        for(int i = 0 ; i < accounts.length ; i++){
            accounts[i] = money;
        }
    }

    public void transfer(int from , int to , int amount) throws InterruptedException{
        alipayLock.lock();
        try{
            while(accounts[from] < amount){
                //钱不够进行转账
                condition.await();
            }
            //转账操作
            accounts[from] = accounts[from] - amount;
            accounts[to] = accounts[to] + amount;
            condition.signalAll();
        }finally {
            alipayLock.unlock();
        }
    }

    public void transfer2(int from , int to , int amount) throws  InterruptedException{
        while(accounts[from] < amount){
            wait();
        }
        //转账的操作
        accounts[from] = accounts[from] - amount;
        accounts[to] = accounts[to] + amount;
        notifyAll();
    }
}
