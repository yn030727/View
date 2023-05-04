package com.example.rxjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.rxjava.databinding.ActivityMainBinding;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Function3;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.observables.GroupedObservable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String TAG = "Ning_RxJava";

        //setContentView(R.layout.activity_main);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this , R.layout.activity_main);

        Swordsman swordsman = new Swordsman("张无忌" , "SSR") ;
//        binding.setSwordsman(swordsman);
//
//        binding.setOnclickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d(TAG, "onClick: ");
//            }
//        });

        String name = "Hello World";
        binding.setSwordsman(swordsman);










//        //1.创建Observer(观察者)
//        Observer observer = new Observer<Object>() {
//            @Override
//            public void onSubscribe(@NonNull Disposable d) {
//                Log.d(TAG, "onSubscribe");
//            }
//
//            @Override
//            public void onNext(Object o) {
//                Log.d(TAG, "onNext");
//            }
//
//            @Override
//            public void onError(Throwable t) {
//                Log.d(TAG, "onError");
//            }
//
//            @Override
//            public void onComplete() {
//                Log.d(TAG, "onComplete");
//            }
//        } ;
//
//
//        Observable observable = Observable.create(new ObservableOnSubscribe<Object>() {
//            @Override
//            public void subscribe(@NonNull ObservableEmitter<Object> emitter) throws Throwable {
//                emitter.onNext("杨影枫");
//                emitter.onNext("月眉儿");
//                emitter.onComplete();
//            }
//        });
//
//        Observable observable1 = Observable.just("杨影枫" , "月眉儿");
//
//        String[] words = {"杨影枫" , "月眉儿"};
//        Observable observable2 = Observable.fromArray(words);

//        observable.subscribe(observer);
//        observable1.subscribe(observer);
//        observable2.subscribe(observer);

       // Log.d(TAG, "分割线**************************************************************");

//        Function<String , String> onNextFunction = new Function<String, String>() {
//            @Override
//            public String apply(String s) throws Throwable {
//                Log.d(TAG, "apply " + s);
//                return s + "!";
//            }
//        };
//
//        observable1.map(onNextFunction).subscribe(new Consumer<String>() {
//            @Override
//            public void accept(String s) throws Throwable {
//                Log.d(TAG, "accept: " + s);
//            }
//
//        });
//

//        Disposable d = Observable.create(new ObservableOnSubscribe<Object>() {
//            @Override
//            public void subscribe(@NonNull ObservableEmitter<Object> emitter) throws Throwable {
//                emitter.onNext("1");
//                emitter.onNext("22");
//                emitter.onNext("333");
//                emitter.onError(new Throwable("手动丢出异常"));
//            }
//        }).subscribe(new Consumer<Object>() {
//            @Override
//            public void accept(Object o) throws Throwable {
//                Log.d(TAG, "accept: " + o);
//            }
//        }, new Consumer<Throwable>() {
//            @Override
//            public void accept(Throwable throwable) throws Throwable {
//                Log.d(TAG, "accept: " + throwable);
//            }
//        });

        //创建一个每秒发射一次长整型数字的Observable
//        Observable<Long> observable = Observable.interval(1 , TimeUnit.SECONDS);
//        observable.subscribe(number -> Log.d(TAG, "interval: " + number));


        //创建一个发射指定范围的事件序列，处理每一个事件
//        Observable<Integer> observable1 = Observable.range(0 , 5).repeat(2);
//        observable1.subscribe(number -> Log.d(TAG, "range: " + number));

//        final String Host = "http://blog.csdn/net/";
//        List<String> mList = new ArrayList<>();
//        mList.add("Ning1");
//        mList.add("Ning2");
//        mList.add("Ning3");
//        mList.add("Ning4");
//
//        Observable.fromIterable(mList).flatMap(new Function<String, ObservableSource<?>>() {
//            @Override
//            public ObservableSource<?> apply(String s) throws Throwable {
//                return Observable.just(Host + s);
//            }
//        }).cast(String.class).subscribe(new Consumer<String>() {
//
//            @Override
//            public void accept(String s) throws Throwable {
//                Log.d(TAG, "flatMap: " + s);
//            }
//        });


//        Observable.just(1,2,3).flatMapIterable(new Function<Integer, Iterable<Integer>>() {
//            @Override
//            public Iterable<Integer> apply(Integer integer) throws Throwable {
//                List<Integer> mList = new ArrayList<>();
//                mList.add(integer + 1);
//                return mList;
//            }
//        }).subscribe(new Consumer<Integer>() {
//            @Override
//            public void accept(Integer integer) throws Throwable {
//                Log.d(TAG, "accept: " + integer);
//            }
//        });


//        Observable.just(1,2,3,4,5,6,7,8,9,10)
//                .buffer(3)
//                .subscribe(new Consumer<List<Integer>>() {
//                    @Override
//                    public void accept(List<Integer> integers) throws Throwable {
//                        for(Integer i : integers){
//                            Log.d(TAG, "buffer: " + i);
//                        }
//                        Log.d(TAG, "buffer: -----------------------" );
//                    }
//                });

//        Observable.just(1,2,3,4,5,6)
//                .groupBy(integer -> integer % 2 == 0 ? "偶数" : "奇数")
//                .subscribe(groupObservable -> {
//                    groupObservable.subscribe(integer -> Log.d(TAG, "key: " + groupObservable.getKey() + ", value: " + integer));
//                });


//        Observable<Integer> observable = Observable.just(1,2,3,4,5,6,7);
//        observable.groupBy(new Function<Integer, String>() {
//                    @Override
//                    public String apply(Integer integer) throws Throwable {
//                        if(integer % 2 == 0){
//                            return "偶数";
//                        }else{
//                            return "奇数";
//                        }
//                    }
//                }).subscribe(new Consumer<GroupedObservable<String, Integer>>() {
//                    @Override
//                    public void accept(GroupedObservable<String, Integer> stringIntegerGroupedObservable) throws Throwable {
//                        Log.d(TAG, "accept: key = " + stringIntegerGroupedObservable.getKey());
//                        stringIntegerGroupedObservable.subscribe(new Consumer<Integer>() {
//                            @Override
//                            public void accept(Integer integer) throws Throwable {
//                                Log.d(TAG, "accept: value = " + integer);
//                            }
//                        });
//                    }
//                });


//        Observable.just(1,2,3,4).filter(new Predicate<Integer>() {
//            @Override
//            public boolean test(Integer integer) throws Throwable {
//                return integer % 2 == 0;
//            }
//        }).subscribe(new Consumer<Integer>() {
//            @Override
//            public void accept(Integer integer) throws Throwable {
//                Log.d(TAG, "filter: " + integer);
//            }
//        });

//        Observable.just(1,2,2,2,3,4,5,5,6)
//                .ignoreElements()
//                .doOnComplete(new Action() {
//                    @Override
//                    public void run() throws Throwable {
//                        Log.d(TAG, "Observable Completed");
//                    }
//                })
//                .subscribe();

//            Observable.create(new ObservableOnSubscribe<Integer>() {
//                @Override
//                public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
//                    for(int i = 0 ; i < 10 ; i++){
//                        emitter.onNext(i);
//                        try{
//                            Thread.sleep(100);
//                        }catch (InterruptedException e){
//                            e.printStackTrace();
//                        }
//                    }
//                    emitter.onComplete();
//                }
//            }).throttleFirst(200 , TimeUnit.MILLISECONDS).subscribe(new Consumer<Integer>() {
//                @Override
//                public void accept(Integer integer) throws Throwable {
//                    Log.d(TAG, "throttleFirst: " + integer);
//                }
//            });


//            Observable.create(new ObservableOnSubscribe<Integer>() {
//                @Override
//                public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
//                    for(int i = 0 ; i < 10 ; i++){
//                        emitter.onNext(i);
//                        int sleep = 100;
//                        if(i % 3 == 0){
//                            sleep = 300;
//                        }
//                        try{
//                            Thread.sleep(sleep);
//                        }catch (InterruptedException e){
//                            e.printStackTrace();
//                        }
//                    }
//                    emitter.onComplete();
//                }
//            }).throttleWithTimeout(200 , TimeUnit.MILLISECONDS)
//                    .subscribe(new Consumer<Integer>() {
//                        @Override
//                        public void accept(Integer integer) throws Throwable {
//                            Log.d(TAG, "throttleWithTimeout" + integer);
//                        }
//                    });



//        Observable<Integer> obs1 = Observable.just(1,2,3).subscribeOn(Schedulers.io());
//        Observable<Integer> obs2 = Observable.just(4,5,6);
//        Observable.merge(obs1 , obs2)
//                .subscribe(new Consumer<Integer>() {
//                    @Override
//                    public void accept(Integer integer) throws Throwable {
//                        Log.d(TAG, "merge" + integer);
//                    }
//                });


//        Observable<Integer> obs1 = Observable.just(1,2,3);
//        Observable<String> obs2 = Observable.just("a" , "b" , "c");
//        Observable.combineLatest(obs1, obs2, new BiFunction<Integer, String, String>() {
//            @Override
//            public String apply(Integer integer, String s) throws Throwable {
//                return integer + s;
//            }
//        }).subscribe(new Consumer<String>() {
//            @Override
//            public void accept(String s) throws Throwable {
//                Log.d(TAG, "combineLatest: " + s);
//            }
//        });

//        Observable.create(new ObservableOnSubscribe<Long>() {
//            @Override
//            public void subscribe(@NonNull ObservableEmitter<Long> emitter) throws Throwable {
//                Long currentTime = System.currentTimeMillis()/1000;
//                emitter.onNext(currentTime);
//            }
//        }).delay(2 , TimeUnit.SECONDS)
//                .subscribe(new Consumer<Long>() {
//                    @Override
//                    public void accept(Long aLong) throws Throwable {
//                        Log.d(TAG, "delay: " + (System.currentTimeMillis()/1000 - aLong));
//                    }
//                });
        
        
//        Observable<Integer> obs = Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
//                Log.d(TAG, "subscribe: " + Thread.currentThread().getName());
//                emitter.onNext(1);
//                emitter.onComplete();
//            }
//        });
//        obs.subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<Integer>() {
//                    @Override
//                    public void accept(Integer integer) throws Throwable {
//                        Log.d(TAG, "accept: " + Thread.currentThread().getName());
//                    }
//                });


//        Observable<Integer> obs = Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
//                for(int i = 0 ; i < 4 ; i++){
//                    try{
//                        Thread.sleep(i * 100);
//                    }catch (InterruptedException e){
//                        e.printStackTrace();
//                    }
//                    emitter.onNext(i);
//                }
//                emitter.onComplete();
//            }
//        }).timeout(200 , TimeUnit.MILLISECONDS , Observable.just(10 ,11));
//        obs.subscribe(new Consumer<Integer>() {
//            @Override
//            public void accept(Integer integer) throws Throwable {
//                Log.d(TAG, "timeOut: " + integer);
//            }
//        });

//        Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
//                for(int i = 0 ; i < 5 ; i++){
//                    if(i > 2){
//                        emitter.onError(new Throwable("Throwable"));
//                    }
//                    emitter.onNext(i);
//                }
//                emitter.onComplete();
//            }
//        }).onErrorReturn(new Function<Throwable, Integer>() {
//            @Override
//            public Integer apply(Throwable throwable) throws Throwable {
//                return 6;
//            }
//        }).subscribe(new Observer<Integer>() {
//            @Override
//            public void onSubscribe(@NonNull Disposable d) {
//                Log.d(TAG, "onSubscribe: ");
//            }
//
//            @Override
//            public void onNext(@NonNull Integer integer) {
//                Log.d(TAG, "onNext: " + integer);
//            }
//
//            @Override
//            public void onError(@NonNull Throwable e) {
//                Log.d(TAG, "onError: " + e.getMessage());
//            }
//
//            @Override
//            public void onComplete() {
//                Log.d(TAG, "onComplete");
//            }
//        });

//
//        Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
//                for(int i = 0 ; i < 5 ; i++){
//                    if(i == 1){
//                        emitter.onError(new Throwable("Throwable"));
//                    }else{
//                        emitter.onNext(i);
//                    }
//                }
//                emitter.onComplete();
//            }
//        }).retry(2).subscribe(new Observer<Integer>() {
//            @Override
//            public void onSubscribe(@NonNull Disposable d) {
//                Log.d(TAG, "onSubscribe");
//            }
//
//            @Override
//            public void onNext(@NonNull Integer integer) {
//                Log.d(TAG, "onNext: " + integer);
//            }
//
//            @Override
//            public void onError(@NonNull Throwable e) {
//                Log.d(TAG, "onError: " + e.getMessage());
//            }
//
//            @Override
//            public void onComplete() {
//                Log.d(TAG, "onComplete: ");
//            }
//        });


//        Observable.just(1,2,3).all(new Predicate<Integer>() {
//            @Override
//            public boolean test(Integer integer) throws Throwable {
//                return  integer < 2;
//            }
//        }).subscribe(new Consumer<Boolean>() {
//            @Override
//            public void accept(Boolean aBoolean) throws Throwable {
//                Log.d(TAG, "accept: " + aBoolean);
//            }
//        });

//        Observable.just(1,2,3)
//                .contains(1)
//                .subscribe(new Consumer<Boolean>() {
//                    @Override
//                    public void accept(Boolean aBoolean) throws Throwable {
//                        Log.d(TAG, "contains: " + aBoolean);
//                    }
//                });
//        Observable.just(1,2,3)
//                .isEmpty()
//                .subscribe(new Consumer<Boolean>() {
//                    @Override
//                    public void accept(Boolean aBoolean) throws Throwable {
//                        Log.d(TAG, "isEmpty: " + aBoolean);
//                    }
//                });

//        Observable.ambArray(Observable.just(1,2,3).delay(2 , TimeUnit.SECONDS) , Observable.just(4,5,6))
//                .subscribe(new Consumer<Integer>() {
//                    @Override
//                    public void accept(Integer integer) throws Throwable {
//                        Log.d(TAG, "amb: " + integer
//                        );
//                    }
//                });

//        Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
//                emitter.onComplete();
//            }
//        }).defaultIfEmpty(3)
//                .subscribe(new Consumer<Integer>() {
//                    @Override
//                    public void accept(Integer integer) throws Throwable {
//                        Log.d(TAG, "defaultIfEmpty: " + integer);
//                    }
//                });

//        Observable.just(3,1,2).toSortedList()
//                .subscribe(new Consumer<List<Integer>>() {
//                    @Override
//                    public void accept(List<Integer> integers) throws Throwable {
//                        for(int num : integers){
//                            Log.d(TAG, "accept: " + num);
//                        }
//                    }
//                });

//        postAsynHttp("123456");
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://api.github.com/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
//                .build();
//
//        IpServiceForPost ipServiceForPost = retrofit.create(IpServiceForPost.class);
//
//        ipServiceForPost.getUser("octocat")
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new SingleObserver<User>() {
//                    @Override
//                    public void onSubscribe(@NonNull Disposable d) {
//                        //订阅时回调这里可以显示加载框等操作
//                    }
//
//                    @Override
//                    public void onSuccess(@NonNull User user) {
//                        Log.d(TAG, "onSuccess: " + user.getName() + "   " + user.getFollowers() );
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//                        Log.d(TAG, "onError: ");
//                    }
//                });

//
//        Intent intent = new Intent(this , RxBusActivity.class);
//        startActivity(intent);

    }

//    OkHttpClient mOkHttpClient;
//    private Observable<String> getObservable(final String ip){
//        Observable observable = Observable.create(new ObservableOnSubscribe<String>() {
//            @Override
//            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
//                mOkHttpClient = new OkHttpClient();
//                Request request = new Request.Builder()
//                        .url("https://www.baidu.com")
//                        .build();
//                Call call = mOkHttpClient.newCall(request);
//                call.enqueue(new Callback() {
//                    @Override
//                    public void onFailure(Call call, IOException e) {
//                        emitter.onError(new Exception("error"));
//                    }
//
//                    @Override
//                    public void onResponse(Call call, Response response) throws IOException {
//                        String str = response.body().string();
//                        emitter.onNext(str);
//                        emitter.onComplete();
//                    }
//                });
//            }
//        });
//
//        return observable;
//    }

//
//    private String TAG = "Ning";
//    private void postAsynHttp(String size){
//
//        getObservable(size).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<String>() {
//                    @Override
//                    public void onSubscribe(@NonNull Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(@NonNull String s) {
//                        Log.d(TAG, "onNext: " + s);
//                        Toast.makeText( getApplicationContext() , "请求成功", Toast.LENGTH_SHORT ).show();
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//                        Log.d(TAG, "onError: " + e.getMessage());
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Log.d(TAG, "onComplete");
//                    }
//                });
//    }
}