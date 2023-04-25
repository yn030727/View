package com.example.rxjava;


import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.Subject;

public class RxBus {
    private final Subject<Object> subject;
    private static volatile RxBus rxBus;

    //Subject是非线程安全的，所有在这里将PublishSubject转化为一个SerializedSubject
    private RxBus(){
        subject = PublishSubject.create().toSerialized();
    }

    //注释2处用到了单例模式的双重检查模式
    public static RxBus getInstance(){
        if(rxBus == null){
            synchronized (RxBus.class){
                if(rxBus == null){
                    rxBus = new RxBus();
                }
            }
        }
        return rxBus;
    }

    public void post(Object ob){
        subject.onNext(ob);
    }

    //ofType只会发送指定的数据
    public <T>Observable<T> toObservable(Class<T> eventType){
        return subject.ofType(eventType);
    }

}
