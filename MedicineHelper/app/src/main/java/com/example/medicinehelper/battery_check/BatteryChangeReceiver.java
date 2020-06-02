package com.example.medicinehelper.battery_check;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.util.Log;

public class BatteryChangeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        checkBatteryLevel(intent);
    }
    public void checkBatteryLevel(Intent batteryChangeIntent) {
        final int currLevel = batteryChangeIntent.getIntExtra(
                BatteryManager.EXTRA_LEVEL, -1);
        final int maxLevel = batteryChangeIntent.getIntExtra(
                BatteryManager.EXTRA_SCALE, -1);
        final int percentage = (int) Math.round((currLevel * 100.0) / maxLevel);
        Log.i("BATTERY", "checkBatteryLevel: chacking");
        if (percentage == 99) {
            Log.i("BATTERY", "battery at 99 percent");
            //nottifiaction for pluging the power source
        }
    }
}
