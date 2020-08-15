package com.example.medicinehelper;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.medicinehelper.data.MedicineListOpenHelper;

public class DeleteMedicine extends AppCompatActivity {

    public  EditText setNameEdit;
    public MedicineListOpenHelper db;
    public int index;
    public int position;
    private String medicineName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_medicine);


    }

    public void deleteMedicine(View view) {
        db = new MedicineListOpenHelper(this);
        setNameEdit = (EditText) findViewById(R.id.setNameEdit);
        medicineName = setNameEdit.getText().toString();

        if(medicineName.equals("")){
            Toast.makeText(this,"Внесете име на лек ",Toast.LENGTH_SHORT).show();
        }else{
            db.delete(medicineName);
            setNameEdit.setText("");

        }
    }
}
