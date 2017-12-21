package com.yogeshbirthdaywisher.birthdaywisher.others;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.widget.Toast;

import com.yogeshbirthdaywisher.birthdaywisher.R;

import com.yogeshbirthdaywisher.birthdaywisher.database.DataBase;

/**
 * Created by yogesh on 6/6/2017.
 */
public class Sound {
    Activity mactivity;
    Context c;
    DataBase dbHandler;
    SQLiteDatabase db;
    int sound=1;

    public Sound(Context context){
        c=context;
        dbHandler=new DataBase(c,null,null,1);
    }

    public void checkSound(){
        try {
            checkDatabase();
            if (sound == 1) {
                MediaPlayer mp = MediaPlayer.create(c, R.raw.pop);
                mp.start();
            }
        }catch (Exception e){
            Toast.makeText(c,e.toString(),Toast.LENGTH_LONG).show();
        }
    }

    public void checkDatabase(){
        db=dbHandler.getDb();
        String query = "select * FROM selector where id=1";
        Cursor c=db.rawQuery(query,null);
        c.moveToFirst();
        int sound_database=c.getInt(c.getColumnIndex("sound"));
        sound= sound_database;

    }
}
