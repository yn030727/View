package com.example.mode;

public class Singleton {

//    private static Singleton instance = new Singleton();
//
//    private Singleton(){}
//
//    public static Singleton getInstance(){
//        return instance;
//    }

//    private static Singleton instance;
//    private Singleton(){}
//    public static Singleton getInstance(){
//        if(instance == null){
//            instance = new Singleton();
//        }
//        return instance;
//    }

//
//    private static Singleton instance;
//    private Singleton(){}
//    public static synchronized Singleton getInstance(){
//        if(instance == null){
//            instance = new Singleton();
//        }
//        return instance;
//    }

//    private volatile static Singleton instance;
//    private Singleton(){}
//    public static Singleton getInstance(){
//        if(instance == null){
//            synchronized (Singleton.class){
//                if(instance == null){
//                    instance = new Singleton();
//                }
//            }
//        }
//        return instance;
//    }

    private Singleton(){}

    public static Singleton getInstance(){
        return SingletonHolder.sInstance;
    }
    private static class SingletonHolder{
        private static final Singleton sInstance = new Singleton();
    }

}
