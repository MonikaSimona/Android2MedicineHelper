package com.example.medicinehelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.medicinehelper.battery_check.BatteryChangeReceiver;
import com.example.medicinehelper.battery_check.BatteryCheckService;

public class MainActivity extends AppCompatActivity {
    private BatteryCheckService batteryCheckService;
    BatteryChangeReceiver batteryChangeReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(batteryCheckService==null){
            Intent i = new Intent(this,BatteryCheckService.class);
            startService(i);
        }
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(batteryChangeReceiver);
        super.onDestroy();
    }
}
