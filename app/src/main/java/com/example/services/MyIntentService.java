package com.example.services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 */
public class MyIntentService extends IntentService {

    public MyIntentService() {
        super("MyIntentService");
        Log.d("MyIntentService", "IntentService instantiated");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("MyIntentService", "IntentService created!");
    }

    public MyIntentService(String name) {
        super(name);
        setIntentRedelivery(true);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        try {
            Thread.sleep(5000);
            Log.d("MyIntentService","task performed");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}