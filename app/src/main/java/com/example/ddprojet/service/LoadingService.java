package com.example.ddprojet.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.Timer;
import java.util.TimerTask;

public class LoadingService extends Service {

    private Timer timer;
    private TimerTask task;
    private int number;

    @Override
    public void onCreate() {
        this.timer = new Timer();
        this.number = 0;
    }

    @Override
    public void onDestroy() {
        timer.cancel();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        final Handler handler = new Handler();
        this.task = new TimerTask() {
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        Toast.makeText(LoadingService.this, "Loading." + ((number > 0) ? "." : "") + ((number > 1) ? "." : ""),
                                Toast.LENGTH_SHORT).show();
                        LoadingService.this.number = (number+1)%3;
                    }
                });
            }
        };

        this.timer.schedule(task, 0, 1000);

        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
