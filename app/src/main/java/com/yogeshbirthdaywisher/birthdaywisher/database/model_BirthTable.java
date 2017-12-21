package com.yogeshbirthdaywisher.birthdaywisher.database;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by yogesh on 9/27/2017.
 */
public class model_BirthTable {

    int id;
    String name;
    String phone;
    String dob;
    int month;
    int day;
    int format;
    int schedule_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getFormat() {
        return format;
    }

    public void setFormat(int format) {
        this.format = format;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getSchedule_id() {
        return schedule_id;
    }

    public void setSchedule_id(int schedule_id) {
        this.schedule_id = schedule_id;
    }

    public void insert()
    {
        String query = "insert into birth_table(name,phone,dob,month,day,format,schedule_id) values('" + name + "','" + phone + "','" + dob + "'," + month + "," + day+","+format+ ","+schedule_id+");";
    }

    public void getValue(Activity mactivity,int id)
    {


        SQLiteDatabase db;
        DataBase dbhandler = new DataBase(mactivity, null, null, 1);
        String query = "select * FROM birth_table where id="+id;
        db=dbhandler.getDb();
        Cursor c=db.rawQuery(query,null);
        c.moveToFirst();

        name = c.getString(c.getColumnIndex("name"));
        phone = c.getString(c.getColumnIndex("phone"));
        dob = c.getString(c.getColumnIndex("dob"));
        month=c.getInt(c.getColumnIndex("month"));
        day=c.getInt(c.getColumnIndex("day"));
        format=c.getInt(c.getColumnIndex("format"));
        schedule_id=c.getInt(c.getColumnIndex("schedule_id"));
        c.close();
    }


}
