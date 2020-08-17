package com.example.medicinehelper.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.util.Log;

import com.example.medicinehelper.R;
import com.example.medicinehelper.SharedPrefs;
import com.example.medicinehelper.battery_check.BatteryChangeNotification;
import com.example.medicinehelper.sendPrescription.CheckNetwork;
import com.example.medicinehelper.sendPrescription.sendPrescriptionAsyncTask;

import java.util.Calendar;

public class FireMedicineAlarm {

    public int count;
    private static final String ACTION_NOTIFY = "com.example.medicinehelper.alarm.ACTION_NOTIFY";
    public void startAlarm(Context context,String medicineName, int numOfPills, int intakeInterval, int notificationID){
        // razlicno id za sekoj nov alarm


        Intent notifyIntent = new Intent(ACTION_NOTIFY);
        notifyIntent.putExtra("medicineName",medicineName);
        final PendingIntent notifyPendingIntent = PendingIntent.getBroadcast(context,notificationID,notifyIntent,PendingIntent.FLAG_IMMUTABLE);

        AlarmManager alarm  = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);

//        long testTriggerTime = SystemClock.currentThreadTimeMillis(); //za test
//        long repeatInterval = AlarmManager.INTERVAL_FIFTEEN_MINUTES;

        long triggerTime = SystemClock.currentThreadTimeMillis() + AlarmManager.INTERVAL_HOUR * intakeInterval;  // NE go brisi
        long repeatInterval = AlarmManager.INTERVAL_HOUR * intakeInterval;


        alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP,triggerTime,repeatInterval,notifyPendingIntent);

    }


    public void cancelAlarm(Context context,String medicineName, int notificationID){
        AlarmManager alarm = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent cancelIntent = new Intent(Intent.ACTION_DEFAULT);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(context, notificationID, cancelIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarm.cancel(alarmIntent);
    }

}
