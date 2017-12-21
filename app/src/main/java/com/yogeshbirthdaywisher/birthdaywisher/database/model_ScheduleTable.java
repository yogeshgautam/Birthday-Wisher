package com.yogeshbirthdaywisher.birthdaywisher.database;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by yogesh on 11/23/2017.
 */
public class model_ScheduleTable {
    int schedule_id;
    int hour;
    int minute;
    String message;
    int test;

    public int getSchedule_id() {
        return schedule_id;
    }

    public void setSchedule_id(int schedule_id) {
        this.schedule_id = schedule_id;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getTest() {
        return test;
    }

    public void setTest(int test) {
        this.test = test;
    }

    public void getValue(Activity mactivity,int id){
        SQLiteDatabase db;
        DataBase dbhandler = new DataBase(mactivity, null, null, 1);
        String query = "select * FROM schedule_table where schedule_id="+id;
        db=dbhandler.getDb();
        Cursor c=db.rawQuery(query,null);

        c.moveToFirst();
        hour = c.getInt(c.getColumnIndex("hour"));
        minute = c.getInt(c.getColumnIndex("minute"));

        c.close();

    }


}
