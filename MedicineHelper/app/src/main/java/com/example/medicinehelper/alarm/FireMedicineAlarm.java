package com.example.medicinehelper.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

import com.example.medicinehelper.R;
import com.example.medicinehelper.battery_check.BatteryChangeNotification;

import java.util.Calendar;

public class FireMedicineAlarm {
    public int notificationID = 0;
    private static final String ACTION_NOTIFY = "com.example.medicinehelper.alarm.ACTION_NOTIFY";
    public String medicineName;
    public void startAlarm(Context context,String medicineName, int numOfPills, int intakeInterval){
       notificationID++;

        Intent notifyIntent = new Intent(ACTION_NOTIFY);
        final PendingIntent notifyPendingIntent = PendingIntent.getBroadcast(context,notificationID,notifyIntent,PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarm  = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);

        long testTriggerTime = SystemClock.currentThreadTimeMillis();
//        long triggerTime = SystemClock.currentThreadTimeMillis() + AlarmManager.INTERVAL_HOUR * intakeInterval;
        long repeatInterval = AlarmManager.INTERVAL_HOUR * intakeInterval;
        alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP,testTriggerTime,repeatInterval,notifyPendingIntent);


    }
    public void setMedicineName(String medicineName){
        this.medicineName = medicineName;


    }
    public String getMedicineName (){
        return medicineName;

    }
}
