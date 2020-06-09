package com.example.medicinehelper.restarter;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.medicinehelper.Global;
import com.example.medicinehelper.MainServiceHelperClass;

import static android.content.Context.JOB_SCHEDULER_SERVICE;

public class RestartServiceBrodcastReceiver extends BroadcastReceiver {
    public static final String TAG = "SIMONA";
    public static JobScheduler jobScheduler;
    private RestartServiceBrodcastReceiver restartServiceBrodcastReceiver;

    @Override
    public void onReceive(final Context context, Intent intent) {
        Log.d(TAG, "onReceive: about to start timer" + context.toString());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            scheduleJob(context);
        } else {
            registerRestarterReceiver(context);
            MainServiceHelperClass backgroundService = new MainServiceHelperClass();
            backgroundService.launchService(context);
        }
    }



    private void registerRestarterReceiver(final Context context) {
        if (restartServiceBrodcastReceiver == null)
            restartServiceBrodcastReceiver = new RestartServiceBrodcastReceiver();
        else try{
            context.unregisterReceiver(restartServiceBrodcastReceiver);
        } catch (Exception e){

        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                IntentFilter filter = new IntentFilter();
                filter.addAction(Global.RESTART_INTENT);
                try {
                    context.registerReceiver(restartServiceBrodcastReceiver, filter);
                } catch (Exception e) {
                    try {
                        context.getApplicationContext().registerReceiver(restartServiceBrodcastReceiver, filter);
                    } catch (Exception ex) {

                    }
                }
            }
        }, 1000);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void scheduleJob(Context context) {
        if (jobScheduler == null) {
            jobScheduler = (JobScheduler) context
                    .getSystemService(JOB_SCHEDULER_SERVICE);
        }
        ComponentName componentName = new ComponentName(context,HelperJobService.class);
        JobInfo jobInfo = new JobInfo.Builder(1, componentName)
                .setOverrideDeadline(0)
                .setPersisted(true).build();
        jobScheduler.schedule(jobInfo);
    }
}
