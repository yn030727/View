package com.example.a2thread;

public class MoonRunner implements Runnable{
    private long i ;
    private volatile boolean on = true;

    @Override
    public void run() {
        while(on){
            i++;
            System.out.println("i=" +i);
        }
        System.out.println("stop");
    }

    public void cancel(){
        on = false;
    }
}
