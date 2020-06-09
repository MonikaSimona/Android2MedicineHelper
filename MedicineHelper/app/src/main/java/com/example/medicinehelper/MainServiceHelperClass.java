package com.example.medicinehelper;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

public class MainServiceHelperClass {

    private static Intent serviceIntent = null;

    public MainServiceHelperClass(){

    }

    private void setServiceIntent(Context context){
        if(serviceIntent == null){
            serviceIntent = new Intent(context,MainService.class);
        }
    }
    public void launchService(Context context){
        if(context == null){
            return;
        }
        setServiceIntent(context);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            context.startForegroundService(serviceIntent);
        }else{
            context.startService(serviceIntent);
        }
        Log.d("SIMONA", "launchService: service go!!");
    }
}
