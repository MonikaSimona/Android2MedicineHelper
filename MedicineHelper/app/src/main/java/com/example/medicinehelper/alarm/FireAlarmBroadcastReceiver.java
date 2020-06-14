package com.example.medicinehelper.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.medicinehelper.R;
import com.example.medicinehelper.battery_check.BatteryChangeNotification;

public class FireAlarmBroadcastReceiver extends BroadcastReceiver {
    public String name ;
    public int NOTIFICATION_ID;
    public BatteryChangeNotification alarmNotification;
    public String medicineToTake;



    @Override
    public void onReceive(Context context, Intent intent) {

        FireMedicineAlarm fireMedicineAlarm = new FireMedicineAlarm();
        name = fireMedicineAlarm.getMedicineName();
        alarmNotification = new BatteryChangeNotification();
        medicineToTake = "ТРЕБА ДА ГО ИСПИЕТЕ ЛЕКОТ " + name.toUpperCase();
        alarmNotification.setNotification(context,"personal_notification",NOTIFICATION_ID,"ВРЕМЕ ЗА ЛЕК","medicineToTake", R.drawable.ic_medicine_helper);



    }
}
