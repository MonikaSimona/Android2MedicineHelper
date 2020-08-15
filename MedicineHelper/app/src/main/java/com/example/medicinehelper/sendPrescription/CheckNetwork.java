package com.example.medicinehelper.sendPrescription;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class CheckNetwork {
    public boolean connection;
    public Context mContext;

    public  CheckNetwork(Context context){
        mContext=context;

    }

    public boolean networkCheck(){

        ConnectivityManager connMgr = (ConnectivityManager) mContext.getSystemService(mContext.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null) {
            connection = true;
            if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                connection = true;
            } else if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                connection = true;
            }
        } else {
            connection = false;
        }

        return connection;
    }
}
