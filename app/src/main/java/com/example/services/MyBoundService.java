package com.example.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.Random;

public class MyBoundService extends Service {

    private IBinder ibinder = new Binder();

    public MyBoundService() {
    }

    public MyBoundService (IBinder b) {
        ibinder = b;
    }

    public void onCreate() {
        super.onCreate();
        Log.d("MyBoundDService", "Bound Service Created!");
    }

    @Override
    public IBinder onBind(Intent intent) {
            Log.d("MyBoundService","onBind called");
            return ibinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return false;
    }

    public int getInfoFromService() {
        Random randomGenerator = new Random();
        return randomGenerator.nextInt(100);
    }
}
