package com.example.medicinehelper.battery_check;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.IBinder;
import android.util.Log;

public class BatteryCheckService extends Service {
    BatteryChangeReceiver batteryChangeReceiver = new BatteryChangeReceiver();

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        final IntentFilter battChangeFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);

        this.registerReceiver(this.batteryChangeReceiver,battChangeFilter);

        return super.onStartCommand(intent, flags, startId);
    }



    public BatteryCheckService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }




}
