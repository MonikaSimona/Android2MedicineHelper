package com.example.medicinehelper;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

public class MainService extends Service {
    protected static final int NOTIFICATION_ID = 1337;
    private static MainService mCurrentService;
    private int counter = 0;
    private static Timer timer;
    private static TimerTask timerTask;
    private String TAG = "SIMONA";

    public MainService() {
        super();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            restartForeground();
        }
        mCurrentService = this;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
         super.onStartCommand(intent, flags, startId);

        Log.d(TAG, "onStartCommand: restarting service");
        counter = 0;

        if(intent == null){
            MainServiceHelperClass backgroundService = new MainServiceHelperClass();
            backgroundService.launchService(this);
        }

        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.O){
            restartForeground();
        }
        startTimer();
        return START_STICKY;
    }

    private void startTimer() {
        Log.i(TAG, "startTimer: starting timer");
        stopTimerTask();
        timer = new Timer();
        initializeTimerTask();
        Log.d(TAG, "startTimer: Scheduling.. ");
        timer.schedule(timerTask,1000,1000); // 1 second ticking timer
    }

    private void initializeTimerTask() {
        Log.d(TAG, "initializeTimerTask: initializing TimerTask");
        timerTask = new TimerTask() {
            @Override
            public void run() {
                Log.d(TAG, "run: in timer +++++++ "+ (counter++));
            }
        };
    }

    private void stopTimerTask() {
        if(timer != null){
            timer.cancel();
            timer = null;
        }
    }

    private void restartForeground() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            Log.d(TAG, "restartForeground: restarting foreground!");
            try{
                ServicePresentNotification notification =  new ServicePresentNotification();
                startForeground(NOTIFICATION_ID,notification.setNotification(this,"Медицински Помошник","Вашиот Медицински помошник е во функција.",R.drawable.ic_medicine_helper));
                Log.d(TAG, "restartForeground: restarting foreground successful");
                startTimer();
            }catch (Exception e){
                Log.d(TAG, "restartForeground: error in notification" + e.getMessage());
            }

        }
    }

    @Override
    public IBinder onBind(Intent intent) {
       return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy called");
        Intent broadcastIntent = new Intent(Global.RESTART_INTENT);
        sendBroadcast(broadcastIntent);
        stopTimerTask();
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        Log.i(TAG, "onTaskRemoved caled");
        Intent broadcastIntent =  new Intent(Global.RESTART_INTENT);
        sendBroadcast(broadcastIntent);
    }
}
