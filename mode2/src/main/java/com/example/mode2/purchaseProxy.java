package com.example.mode2;

public class purchaseProxy implements IShop{
    private IShop mShop;
    public purchaseProxy(IShop iShop){
        mShop = iShop;
    }

    @Override
    public void buy() {
        mShop.buy();
    }
}
