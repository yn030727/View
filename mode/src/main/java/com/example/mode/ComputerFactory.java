package com.example.mode;

public class ComputerFactory {
    public static Computer createComputer(String type){
        Computer mComputer = null;
        switch (type){
            case "lenovo":
                mComputer = new LenovoComputer();
                break;
            case "hp":
                mComputer = new HpComputer();
                break;
            case "asus":
                mComputer = new AsusComputer();
                break;
        }
        return mComputer;
    }
}
