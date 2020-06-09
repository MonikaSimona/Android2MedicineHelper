package com.example.medicinehelper.battery_check;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.util.Log;

import com.example.medicinehelper.R;
import com.example.medicinehelper.ServicePresentNotification;

public class BatteryChangeReceiver extends BroadcastReceiver {
    ServicePresentNotification notification;
    public static final String CHANNEL_ID = "personal_notification";
    public static final int NOTIFICATION_ID = 001;

    @Override
    public void onReceive(Context context, Intent intent) {
        checkBatteryLevel(context,intent);
    }
    public void checkBatteryLevel(Context context,Intent batteryChangeIntent) {
        final int currLevel = batteryChangeIntent.getIntExtra(
                BatteryManager.EXTRA_LEVEL, -1);
        final int maxLevel = batteryChangeIntent.getIntExtra(
                BatteryManager.EXTRA_SCALE, -1);
        final int percentage = (int) Math.round((currLevel * 100.0) / maxLevel);
        Log.i("BATTERY", "checkBatteryLevel: chacking");
        if (percentage == 20) {
            Log.i("BATTERY", "battery at 20 percent");
            //nottifiaction for pluging the power source
            BatteryChangeNotification notification = new BatteryChangeNotification();
            notification.setNotification(context,CHANNEL_ID,NOTIFICATION_ID,"БАТЕРИЈАТА Е НА 20%","ПОВРЗЕТЕ ГО ПОЛНАЧОТ",R.drawable.ic_battery_20);
        }
    }
}
