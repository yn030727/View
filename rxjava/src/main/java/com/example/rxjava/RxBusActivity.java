package com.example.rxjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.locks.Condition;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

public class RxBusActivity extends AppCompatActivity {
    private Button bt_post;
    private ConstraintLayout constraintLayout;
    private TextView bt_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_bus);
        bt_post = (Button) this.findViewById(R.id.bt_post);
        bt_text = this.findViewById(R.id.bt_text);

        bt_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxBus.getInstance().post(new MessageEvent("用RxJava实现RxBus"));
            }
        });



        RxBus.getInstance()
                .toObservable(MessageEvent.class)
                .subscribe(new Observer<MessageEvent>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull MessageEvent messageEvent) {
                        if (messageEvent != null) {
                           bt_text.setText(messageEvent.getMessage());
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        bt_text.setText("error!");
                    }

                    @Override
                    public void onComplete() {

                    }
                });



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}