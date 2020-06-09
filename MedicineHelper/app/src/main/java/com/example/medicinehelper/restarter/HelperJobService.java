package com.example.medicinehelper.restarter;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.medicinehelper.Global;
import com.example.medicinehelper.MainServiceHelperClass;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class HelperJobService extends JobService {

    private static String TAG = "SIMONA";
    private static RestartServiceBrodcastReceiver restartServiceBrodcastReceiver;
    public static JobService instance;
    public static JobParameters jobParameters;

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        MainServiceHelperClass backgroungService = new MainServiceHelperClass();
        backgroungService.launchService(this);
        registerRestarterReceiver();
        instance = this;
        HelperJobService.jobParameters = jobParameters;

        return false;
    }

    private void registerRestarterReceiver() {

        if(restartServiceBrodcastReceiver == null) {
            restartServiceBrodcastReceiver = new RestartServiceBrodcastReceiver();
        }else try{
            unregisterReceiver(restartServiceBrodcastReceiver);
        }catch (Exception e){

        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                IntentFilter filter = new IntentFilter();
                filter.addAction(Global.RESTART_INTENT);
                try{
                    registerReceiver(restartServiceBrodcastReceiver,filter);
                }catch (Exception e){
                    try {
                        getApplicationContext().registerReceiver(restartServiceBrodcastReceiver,filter);
                    }catch (Exception ex){

                    }
                }
            }
        },1000);
    }


    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        Log.d(TAG, "onStopJob: Stopping Job");
        Intent broadcastIntent = new Intent(Global.RESTART_INTENT);
        sendBroadcast(broadcastIntent);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                unregisterReceiver(restartServiceBrodcastReceiver);
            }
        },1000);
        return false;
    }
}
