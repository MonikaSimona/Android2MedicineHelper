package com.example.medicinehelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.medicinehelper.alarm.FireMedicineAlarm;
import com.example.medicinehelper.battery_check.BatteryChangeReceiver;
import com.example.medicinehelper.battery_check.BatteryCheckService;
import com.example.medicinehelper.data.MedicineListOpenHelper;
import com.example.medicinehelper.restarter.RestartServiceBrodcastReceiver;

public class MainActivity extends AppCompatActivity {
    private BatteryCheckService batteryCheckService;
    BatteryChangeReceiver batteryChangeReceiver;
    private MedicineListOpenHelper mDB;
    public EditText nameEdit;
    public EditText num_pillsEdit;
    public EditText intake_intervalEdit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDB = new MedicineListOpenHelper(this);

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

    @Override
    protected void onResume() {
        super.onResume();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            RestartServiceBrodcastReceiver.scheduleJob(getApplicationContext());
        }else{
            MainServiceHelperClass backgroundService = new MainServiceHelperClass();
            backgroundService.launchService(getApplicationContext());
        }
    }

    public void insertMedicine(View view) {

        nameEdit = (EditText) findViewById(R.id.nameEdit);
        num_pillsEdit = (EditText) findViewById(R.id.num_pillsEdit);
        intake_intervalEdit = (EditText) findViewById(R.id.intake_intervalEdit);


        if(nameEdit.getText().toString().matches("") || num_pillsEdit.getText().toString().matches("") || intake_intervalEdit.getText().toString().matches("")){
            Toast.makeText(this,"Пополни ги сите полиња",Toast.LENGTH_SHORT).show();
        }else{

            String nameString = nameEdit.getText().toString();
            int num_pills = Integer.parseInt(num_pillsEdit.getText().toString());
            int intake_interval = Integer.parseInt(intake_intervalEdit.getText().toString());

            mDB.insert(nameString,num_pills,intake_interval);

            nameEdit.setText("");
            num_pillsEdit.setText("");
            intake_intervalEdit.setText("");

            FireMedicineAlarm alarm = new FireMedicineAlarm();
            alarm.startAlarm(this,nameString,num_pills,intake_interval);
        }

    }

    public void medicineList(View view) {
        Intent intent = new Intent(this,MedicineList.class);
        startActivity(intent);
    }
}
