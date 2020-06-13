package com.example.medicinehelper.alarm;

import android.app.AlarmManager;
import android.content.Context;

import com.example.medicinehelper.R;
import com.example.medicinehelper.battery_check.BatteryChangeNotification;

import java.util.Calendar;

public class FireMedicineAlarm {
    String medicineToTake;
    public void startAlarm(Context context,String medicineName, int numOfPills, int intakeInterval){
        int notificationID = 0;
        BatteryChangeNotification alarmNotification = new BatteryChangeNotification();

        AlarmManager alarm  = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);
        Calendar timeOff = Calendar.getInstance();

        medicineToTake = "ТРЕБА ДА ГО ИСПИЕТЕ ЛЕКОТ " + medicineName.toUpperCase();
                alarmNotification.setNotification(context,"personal_notification",notificationID++,"ВРЕМЕ ЗА ЛЕК",medicineToTake, R.drawable.ic_medicine_helper);

    }
}
