package com.example.mode2;

public abstract class AbstractSwordsman {
    //该方法为final，防止算法框架被覆写
    public final void fighting(){
        //运行内功，抽象方法
        neigong();
        //调整经脉，具体方法
        meridian();

        if(hasWeapons()){
            weapons();
        }

        //使用招式
        movers();
        //钩子方法
        hook();
    }

    //空实现方法
    protected void hook(){}
    protected abstract void neigong();
    protected abstract void weapons();
    protected abstract void movers();
    protected void meridian(){
        System.out.println("开启正经与奇功");
    }
    protected boolean hasWeapons(){
        //是否有武器，默认有
        return true;
    }
}
