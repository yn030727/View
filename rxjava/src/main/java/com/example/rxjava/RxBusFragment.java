package com.example.rxjava;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class RxBusFragment extends Fragment {

    TextView textView ;

    @Nullable
    @Override
    public View onCreateView(@androidx.annotation.NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext() , R.layout.layout_rxbus ,container);
        textView = view.findViewById(R.id.rx_text);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        RxBus.getInstance()
                .toObservable(MessageEvent.class)
                .subscribe(new Observer<MessageEvent>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull MessageEvent messageEvent) {
                        if (messageEvent != null) {
                            textView.setText(messageEvent.getMessage());
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        textView.setText("error!");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
