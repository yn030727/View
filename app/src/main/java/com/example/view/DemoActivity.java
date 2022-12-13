package com.example.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Button;

public class DemoActivity extends AppCompatActivity {
    Button button;
    private static final int MESSAGE_SCROLL_TO = 1;
    private static final int FRAME_COUNT = 30;
    private static final int DELAYED_TIME = 33;

    private int mCount = 0;

    private Handler mHandler = new Handler(getMainLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case MESSAGE_SCROLL_TO:{
                    mCount++;
                    if( mCount <= FRAME_COUNT){
                        float fraction = mCount / (float)FRAME_COUNT;
                        int scrollX = (int)(fraction*100);
                        button.scrollTo(scrollX,0);
                        mHandler.sendEmptyMessageDelayed(MESSAGE_SCROLL_TO,DELAYED_TIME);
                        break;
                    }
                }
                default:{
                    break;
                }
            }
        }
    };







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

    }
}