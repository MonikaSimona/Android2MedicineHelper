package com.example.medicinehelper.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.IOException;

public class MedicineListOpenHelper extends SQLiteOpenHelper {
    private SQLiteDatabase mWritableDB;
    private SQLiteDatabase mReadableDB;

    private static final int DATABASE_VERSION = 1;
    private static final String MEDICINE_LIST_TABLE = "medicine_entries";
    private static final String DATABASE_NAME = "medicinelist";

    public static final String KEY_ID = "_id";
    public static final String KEY_MEDICINE = "medicine";
    public static final String KEY_NUM_OF_PILLS = "numberPills";
    public static final String KEY_INTAKE_INTERVAL = "intakeInterval";

    private static final String[] COLUMNS = {KEY_ID,KEY_MEDICINE,KEY_NUM_OF_PILLS,KEY_INTAKE_INTERVAL};

    private static final String MEDICINE_LIST_TABLE_CREATE =
            "CREATE TABLE " + MEDICINE_LIST_TABLE +" (" +
                    KEY_ID + " INTEGER PRIMARY KEY, " +
                    KEY_MEDICINE + " TEXT, " +
                    KEY_NUM_OF_PILLS + " INTEGER, " +
                    KEY_INTAKE_INTERVAL + " INTEGER ); ";



    public MedicineListOpenHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(MEDICINE_LIST_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MedicineListOpenHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + MEDICINE_LIST_TABLE );
        onCreate(db);

    }

    public Medicine query(int position){
        String query  = "SELECT * FROM " + MEDICINE_LIST_TABLE +
                " ORDER BY " + KEY_MEDICINE + " ASC " + "LIMIT " + position + ",1";

        Cursor cursor = null;
        Medicine entry = new Medicine();

        try{
            if(mReadableDB == null){
                mReadableDB = getReadableDatabase();
            }
            cursor = mReadableDB.rawQuery(query,null);
            cursor.moveToFirst();
            entry.setmId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
            entry.setmMedicineName(cursor.getString(cursor.getColumnIndex(KEY_MEDICINE)));
            entry.setmNumberOfPills(cursor.getInt(cursor.getColumnIndex(KEY_NUM_OF_PILLS)));
            entry.setmIntakeIntervalHour(cursor.getInt(cursor.getColumnIndex(KEY_INTAKE_INTERVAL)));

        }catch (Exception e ){
            Log.d("SIMONA", "EXCEPTION : " + e);
        }finally {

            return entry;
        }
    }

    public long insert(String medicineName, int numPills, int intakeInterval){
        long newId = 0;

        ContentValues values = new ContentValues();
        values.put(KEY_MEDICINE,medicineName);
        values.put(KEY_NUM_OF_PILLS,numPills);
        values.put(KEY_INTAKE_INTERVAL,intakeInterval);

        try{

            if(mWritableDB == null){
                mWritableDB = getWritableDatabase();
            }
            newId = mWritableDB.insert(MEDICINE_LIST_TABLE,null,values);

        }catch (Exception e){
            Log.d("SIMONA", "Insert Exception! " + e.getMessage());

        }
        return newId;
    }


    public long count(){
        if(mReadableDB == null){
            mReadableDB = getReadableDatabase();
        }
        return DatabaseUtils.queryNumEntries(mReadableDB, MEDICINE_LIST_TABLE);
    }

    public int delete(String medicine_name){
        int deleted = 0;
        try {

            if (mWritableDB == null){
                mWritableDB = getWritableDatabase();
            }
            String selection  = KEY_MEDICINE+" LIKE ?";
            String[] selection_args = {medicine_name};
            deleted = mWritableDB.delete(MEDICINE_LIST_TABLE,selection,selection_args);

        }catch (Exception e){
            Log.d("SIMONA", "delete error" + e.getMessage());

        }

        return deleted;
    }

    public Cursor search(String searchString){
        String[] columns = new String[]{KEY_MEDICINE};
        searchString = "%" + searchString + "%";
        String where = KEY_MEDICINE + " LIKE ?";
        String[] whereArgs = new String []{searchString};
        Cursor cursor = null;
        try{
            if(mReadableDB == null){
                mReadableDB = getReadableDatabase();
            }
            cursor = mReadableDB.query(MEDICINE_LIST_TABLE, columns, where,whereArgs,null,null,null);


        }catch (Exception e){
            Log.d("SIMONA", "search error" + e.getMessage());
        }
        return  cursor;
    }


}
