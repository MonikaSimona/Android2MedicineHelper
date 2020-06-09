package com.example.medicinehelper.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

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

    public MedicineListOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
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
                mReadableDB =getReadableDatabase();
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
}
