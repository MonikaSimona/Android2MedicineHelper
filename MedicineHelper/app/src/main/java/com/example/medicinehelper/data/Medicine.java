package com.example.medicinehelper.data;

public class Medicine {
    private int mId;
    private String mMedicineName;
    private int mNumberOfPills;
    private int mIntakeIntervalHour;

    public Medicine() {

    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmMedicineName() {
        return mMedicineName;
    }

    public void setmMedicineName(String mMedicineName) {
        this.mMedicineName = mMedicineName;
    }

    public int getmNumberOfPills() {
        return mNumberOfPills;
    }

    public void setmNumberOfPills(int mNumberOfPills) {
        this.mNumberOfPills = mNumberOfPills;
    }

    public int getmIntakeIntervalHour() {
        return mIntakeIntervalHour;
    }

    public void setmIntakeIntervalHour(int mIntakeIntervalHour) {
        this.mIntakeIntervalHour = mIntakeIntervalHour;
    }
}