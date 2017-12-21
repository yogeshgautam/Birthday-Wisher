package com.yogeshbirthdaywisher.birthdaywisher.basic_view_manager;


import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.yogeshbirthdaywisher.birthdaywisher.R;
import com.yogeshbirthdaywisher.birthdaywisher.database.DataBase;
import com.yogeshbirthdaywisher.birthdaywisher.facebook.FacebookLogin;
import com.yogeshbirthdaywisher.birthdaywisher.others.CheckBack;
import com.yogeshbirthdaywisher.birthdaywisher.others.Sound;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsMain extends Fragment {

    private FragmentActivity myContext;

    TextView default_sms,fblogin;
    Activity mactivity;

    DataBase dbHandler;
    SQLiteDatabase db;

    Switch sound,notification,sms;


    public SettingsMain() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myview = inflater.inflate(R.layout.fragment_settings_main, container, false);
        mactivity = this.getActivity();
        dbHandler=new DataBase(mactivity,null,null,1);
        default_sms=(TextView)myview.findViewById(R.id.default_Sms);
        sound=(Switch) myview.findViewById(R.id.switch1);
        notification=(Switch)myview.findViewById(R.id.switch2);
        sms=(Switch)myview.findViewById(R.id.switch3);
        fblogin=(TextView)myview.findViewById(R.id.fblogin);

        getDatabase();

        check_click_defaultSms();
        check_click_sound();
        check_click_notification();
        check_click_sms();
        fbLogin();
        return myview;
    }

    public void check_click_sms(){
        sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db=dbHandler.getDb();
                if(sms.isChecked()){
                    String query="UPDATE selector SET sms=1 WHERE id=1";
                    db.execSQL(query);
                    Toast.makeText(mactivity,"SMS Turned On",Toast.LENGTH_LONG).show();
                }else{
                    String query="UPDATE selector SET sms=0 WHERE id=1";
                    db.execSQL(query);
                    Toast.makeText(mactivity,"SMS Turned Off",Toast.LENGTH_LONG).show();

                }

            }
        });
    }

    public void check_click_notification(){
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db=dbHandler.getDb();
                if(notification.isChecked()){
                    String query="UPDATE selector SET notification=1 WHERE id=1";
                    db.execSQL(query);
                    Toast.makeText(mactivity,"Notification Turned On",Toast.LENGTH_LONG).show();
                }else{
                    String query="UPDATE selector SET notification=0 WHERE id=1";
                    db.execSQL(query);
                    Toast.makeText(mactivity,"Notification Turned Off",Toast.LENGTH_LONG).show();

                }

            }
        });

    }

    public void getDatabase(){
        db=dbHandler.getDb();
        String query = "select * FROM selector where id=1";
        Cursor c=db.rawQuery(query,null);
        c.moveToFirst();
        int sound_database=c.getInt(c.getColumnIndex("sound"));
        int notification_database=c.getInt(c.getColumnIndex("notification"));
        int sms_database=c.getInt(c.getColumnIndex("sms"));

        if(sound_database==1){
            sound.setChecked(true);
        }else{
            sound.setChecked(false);
        }

        if(notification_database==1){
            notification.setChecked(true);
        }else{
            notification.setChecked(false);
        }

        if(sms_database==1){
            sms.setChecked(true);
        }else{
            sms.setChecked(false);
        }

    }

    public void check_click_sound(){
       sound.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               db=dbHandler.getDb();
               if(sound.isChecked()){
                   String query="UPDATE selector SET sound=1 WHERE id=1";
                   db.execSQL(query);
                   Toast.makeText(mactivity,"Sound Turned On",Toast.LENGTH_LONG).show();
               }else{
                   String query="UPDATE selector SET sound=0 WHERE id=1";
                   db.execSQL(query);
                   Toast.makeText(mactivity,"Sound Turned Off",Toast.LENGTH_LONG).show();

               }

           }
       });

    }

    public void check_click_defaultSms(){
        default_sms.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Sound s=new Sound(mactivity);
                s.checkSound();
                CheckBack.a=14;
                Settings fragment = new Settings();
                FragmentTransaction fragmentTransaction = myContext.getSupportFragmentManager().beginTransaction();
                fragmentTransaction = myContext.getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment).addToBackStack("b").commit();

            }
        });
    }

    public void onAttach(Activity activity) {
        myContext=(FragmentActivity)activity;
        super.onAttach(activity);
    }

    public void fbLogin(){
        fblogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FacebookLogin fragment = new FacebookLogin();
                FragmentTransaction fragmentTransaction = myContext.getSupportFragmentManager().beginTransaction();
                fragmentTransaction = myContext.getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment).addToBackStack("b").commit();

            }
        });

    }

}
