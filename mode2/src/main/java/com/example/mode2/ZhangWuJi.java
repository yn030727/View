package com.example.mode2;

public class ZhangWuJi extends AbstractSwordsman{
    //他没有武器，所以返回false
    @Override
    protected void neigong() {
        System.out.println("运行九阳神功");
    }

    @Override
    protected void weapons() {

    }

    @Override
    protected void movers() {
        System.out.println("使用招式乾坤大挪移");
    }

    @Override
    protected boolean hasWeapons() {
        return false;
    }
}
