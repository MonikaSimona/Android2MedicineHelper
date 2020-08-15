package com.example.medicinehelper;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefs {
    private static Context mcontext;
    public final static String PREFS_NAME = "medicineHelper_prefs";
    public SharedPrefs(Context context){
        this.mcontext = context;

    }
    public static void setInt( String key, int value) {
        SharedPreferences prefs = mcontext.getSharedPreferences(PREFS_NAME,0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(key, value);
        editor.apply();
    }
    public static int getInt(String key) {
        SharedPreferences prefs = mcontext.getSharedPreferences(PREFS_NAME, 0);
        return prefs.getInt(key, 0);
    }

    public static void setStr(String key, String value) {
        SharedPreferences prefs = mcontext.getSharedPreferences(PREFS_NAME,0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getStr(String key) {
        SharedPreferences prefs = mcontext.getSharedPreferences(PREFS_NAME, 0);
        return prefs.getString(key,"DNF");
    }

}
