package com.example.medicinehelper.sendPrescription;

import android.content.Context;
import android.os.AsyncTask;

import com.example.medicinehelper.SharedPrefs;

import java.io.IOException;

public class sendPrescriptionAsyncTask extends AsyncTask<Void,Void,String> {
    public Context mContext;
    public sendPrescriptionAsyncTask(Context context){
        this.mContext = context;

    }

    @Override
    protected String doInBackground(Void... voids) {
        String finishedMedicine;
        SharedPrefs sharedPrefs = new SharedPrefs(mContext);
        finishedMedicine = sharedPrefs.getStr("finishedMedicine");
        try {
            postPrescription.post(finishedMedicine);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
