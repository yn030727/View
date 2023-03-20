package com.example.handler1;

import android.app.IntentService;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.Nullable;

public class LocalIntentService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    private static final String TAG = "Ning";

    public LocalIntentService(){
        super(TAG);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String action = intent.getStringExtra("task_action");
        Log.d("Ning","receive task : " + action);
        SystemClock.sleep(3000);
        if("com.ryg.action.TASK1".equals(action)){
            Log.d("Ning" , "handle task : " + action);
        }
    }

    @Override
    public void onDestroy() {
        Log.d("Ning","service destroyed.");
        super.onDestroy();
    }
}
