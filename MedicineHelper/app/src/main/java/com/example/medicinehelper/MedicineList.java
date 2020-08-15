package com.example.medicinehelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.medicinehelper.data.Medicine;
import com.example.medicinehelper.data.MedicineListOpenHelper;

public class MedicineList extends AppCompatActivity {

    private MedicineListOpenHelper mDB;
    public TextView list;
    public Medicine medicine;
    public String name;
    public int numPills;
    public int intakeInterval;
    public String oneMedcineString;
    public String printList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_list);

        String newline = System.getProperty("line.separator");

        mDB = new MedicineListOpenHelper(this);
        medicine = new Medicine();
        list = (TextView) findViewById(R.id.list);

        long numberOfEntries = mDB.count();
        for (int i = 0; i<numberOfEntries; i++){
            medicine = mDB.query(i);

            name  = medicine.getmMedicineName();
            numPills = medicine.getmNumberOfPills();
            intakeInterval = medicine.getmIntakeIntervalHour();

             oneMedcineString = "Име на лек: " + name + newline +
                    "Број на таблети: " + numPills + newline +
                    "Интервал на пиење: " + intakeInterval + " часа " + newline  ;
             printList+=oneMedcineString + newline;

        }
        list.setText(printList);
    }

    public void toDeleteMedicineActivity(View view) {
        Intent intent = new Intent(this,DeleteMedicine.class);
        startActivity(intent);
    }
}
