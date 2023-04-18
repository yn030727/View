package com.example.mode;

public class Client2 {
    public static void main(String[] args) {
        ComputerFactory2 computerFactory2 = new GDComputerFactor();

        LenovoComputer mLenovoComputer = computerFactory2.createComputer(LenovoComputer.class);
        mLenovoComputer.start();

        HpComputer mHpComputer = computerFactory2.createComputer(HpComputer.class);
        mHpComputer.start();

        AsusComputer mAsusComputer = computerFactory2.createComputer(AsusComputer.class);
        mAsusComputer.start();
    }
}
