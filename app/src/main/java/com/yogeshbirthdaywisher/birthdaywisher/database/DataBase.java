package com.yogeshbirthdaywisher.birthdaywisher.database;

import android.database.sqlite.*;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.content.Context;
import android.widget.Toast;

public class DataBase extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "birthday_wisher.db";
    Context con;
    public DataBase(Context c){
        super(c,DATABASE_NAME,null,DATABASE_VERSION);
    }

    public DataBase(Context c, String n, CursorFactory f, int v)
    {
        super(c, DATABASE_NAME, f, DATABASE_VERSION);
        con =c;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    	 try{

    		 String query2="CREATE TABLE birth_table(id INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR(30),phone VARCHAR(20),dob VARCHAR(20),month INTEGER(5),day INTEGER(5),format INTEGER(5),schedule_id INTEGER(5));";
             db.execSQL(query2);
    		String query="CREATE TABLE selector(id INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR(30),phone VARCHAR(20),dob VARCHAR(20),idd INTEGER,sound INTEGER,notification INTEGER,sms INTEGER);";
             db.execSQL(query);
             query="INSERT INTO selector(name,phone,dob,idd,sound,notification,sms) VALUES('yogesh ','yogesh ','yogesh ',0,1,1,1);";
             db.execSQL(query);
             query="CREATE TABLE schedule_table(schedule_id INTEGER PRIMARY KEY,hour INTEGER(5),minute INTEGER(5),message VARCHAR(40),test INTEGER(5));";
            db.execSQL(query);
             query="INSERT INTO schedule_table(schedule_id,hour,minute,message,test) VALUES (-1,0,0,'Happy Birthday...!!!',0);";
             db.execSQL(query);



             // query="CREATE TABLE  password(date VARCHAR(20),old_password VARCHAR(30),password VARCHAR(30),recovery_key VARCHAR(30));";
             //db.execSQL(query);
            // create_facultyList(db);
             Toast.makeText(con,"DataBase: Table created",Toast.LENGTH_LONG ).show();
         }catch(Exception e) {
             Toast.makeText(con,e.toString(),Toast.LENGTH_LONG ).show();
         }
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int old_ver, int new_ver) {
        onCreate(db);
    }

    
    public SQLiteDatabase getDb()
    {
        return this.getWritableDatabase();
    }

}
