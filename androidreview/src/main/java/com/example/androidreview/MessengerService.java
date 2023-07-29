package com.example.androidreview;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.security.Provider;

public class MessengerService extends Service {

    static final int MSG_SAY_HELLO = 1;

    static class IncomingHandler extends Handler{
        private Context applicationContext;

        IncomingHandler(Context context){
            applicationContext = context.getApplicationContext();
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case MSG_SAY_HELLO:
                    Toast.makeText(applicationContext, "hello", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }

    Messenger mMessenger;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(getApplicationContext(), "binding", Toast.LENGTH_SHORT).show();
        mMessenger = new Messenger(new IncomingHandler(this));
        return mMessenger.getBinder();
    }
}
