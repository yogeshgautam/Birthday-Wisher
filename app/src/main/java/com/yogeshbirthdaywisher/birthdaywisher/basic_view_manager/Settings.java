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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.yogeshbirthdaywisher.birthdaywisher.R;
import com.yogeshbirthdaywisher.birthdaywisher.database.DataBase;
import com.yogeshbirthdaywisher.birthdaywisher.others.CheckBack;
import com.yogeshbirthdaywisher.birthdaywisher.others.Sound;


/**
 * A simple {@link Fragment} subclass.
 */
public class Settings extends Fragment {
    EditText message;
    TimePicker timePicker;
    Button save;
    Activity mactivity;
    DataBase dbhandler;
    SQLiteDatabase db;
    String message_new;
    int hour_new,minute_new;
    private FragmentActivity myContext;


    public Settings() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myview = inflater.inflate(R.layout.fragment_settings, container, false);

        try {
            message = (EditText) myview.findViewById(R.id.message);
            mactivity=this.getActivity();
            timePicker = (TimePicker) myview.findViewById(R.id.timePicker);
            save = (Button) myview.findViewById(R.id.button6);
            first();



            save.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v) {
                    Sound s=new Sound(mactivity);
                    s.checkSound();
                    CheckBack.a=1;
                    try {
                        hour_new = timePicker.getCurrentHour();
                        minute_new = timePicker.getCurrentMinute();
                        message_new = message.getText().toString();
                        //Toast.makeText(mactivity,message_new+hour_new+" "+minute_new,Toast.LENGTH_LONG).show();
                        String query = "UPDATE schedule_table SET hour=" + hour_new + ",minute=" + minute_new + ",message='" + message_new + "',test=0 WHERE schedule_id=-1";
                        dbhandler = new DataBase(mactivity, null, null, 1);
                        db = dbhandler.getDb();
                        db.execSQL(query);
                        //Toast.makeText(mactivity,message_new+hour_new+" "+minute_new,Toast.LENGTH_LONG).show();

                        Toast.makeText(mactivity,"Value successfully Updated",Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(mactivity, e.toString(), Toast.LENGTH_LONG).show();
                    }
                    //Toast.makeText(mactivity, "save", Toast.LENGTH_LONG).show();
                    goBack();

                }
            });
        }catch(Exception e){
            Toast.makeText(mactivity,"first exception\n" +e.toString(),Toast.LENGTH_LONG).show();
        }



        return myview;

    }
    public void first(){
        dbhandler=new DataBase(mactivity,null,null,1);

        db=dbhandler.getDb();
        String query="SELECT * FROM schedule_table WHERE schedule_id=-1";
        Cursor c=db.rawQuery(query,null);
        if(c!=null) {
            c.moveToFirst();
            //String msg="Happy Birthday....!!!";
            String msg = c.getString(c.getColumnIndex("message"));
            int hour = c.getInt(c.getColumnIndex("hour"));
            int minute = c.getInt(c.getColumnIndex("minute"));
            message.setText(msg);
            timePicker.setCurrentHour(hour);
            timePicker.setCurrentMinute(minute);
        }

    }
    public void goBack(){
        //Toast.makeText(mactivity,"Default values updated successfully",Toast.LENGTH_LONG).show();
        Schedule fragment = new Schedule();
        FragmentTransaction fragmentTransaction = myContext.getSupportFragmentManager().beginTransaction();
        fragmentTransaction = myContext.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment).addToBackStack("b").commit();


    }

    @Override
    public void onAttach(Activity activity) {
        myContext=(FragmentActivity)activity;
        super.onAttach(activity);
    }



}
