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
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.yogeshbirthdaywisher.birthdaywisher.R;
import com.yogeshbirthdaywisher.birthdaywisher.database.DataBase;
import com.yogeshbirthdaywisher.birthdaywisher.others.CheckBack;
import com.yogeshbirthdaywisher.birthdaywisher.others.Sound;


/**
 * A simple {@link Fragment} subclass.
 */
public class ScheduleDetail extends Fragment {

    Activity mactivity;
    DataBase dbhandler;
    SQLiteDatabase db;
    EditText message;
    String name,phone,dob;
    TimePicker time;
    int idd;
    TextView date,to,name_main;
    RadioButton sche,notsche;
    Button save,cancel;

    String time_new;
    int time_new_hour,time_new_minute,scheduling;
    private FragmentActivity myContext;


    public ScheduleDetail() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

           View myview = inflater.inflate(R.layout.fragment_schedule_detail, container, false);
        try {
           mactivity = this.getActivity();
           dbhandler = new DataBase(mactivity, null, null, 1);
           to = (TextView) myview.findViewById(R.id.textView10);
           time = (TimePicker) myview.findViewById(R.id.timePicker2);
           message = (EditText) myview.findViewById(R.id.editText4);
           name_main = (TextView) myview.findViewById(R.id.textView15);
           //date=(TextView)myview.findViewById(R.id.textView11);
           sche = (RadioButton) myview.findViewById(R.id.radioButton);
           notsche = (RadioButton) myview.findViewById(R.id.radioButton2);
            save=(Button) myview.findViewById(R.id.button4);
            cancel=(Button) myview.findViewById(R.id.button5);

           first();
       }catch(Exception e){
           Toast.makeText(mactivity,e.toString(),Toast.LENGTH_LONG).show();
       }


        return myview;
    }

    public void first(){
        String query = "select * FROM selector where id=1";
        db=dbhandler.getDb();
        Cursor c=db.rawQuery(query,null);
        c.moveToFirst();

        name = c.getString(c.getColumnIndex("name"));
        phone = c.getString(c.getColumnIndex("phone"));
        dob = c.getString(c.getColumnIndex("dob"));
        idd=c.getInt(c.getColumnIndex("idd"));
        c.close();

        query = "select * FROM birth_table where id="+idd;
        c=db.rawQuery(query,null);
        c.moveToFirst();
        scheduling=c.getInt(c.getColumnIndex("schedule_id"));



       // Toast.makeText(mactivity,name+"\n"+phone+"\n"+dob+"\n"+idd+"\n"+scheduling,Toast.LENGTH_LONG).show();

        if(scheduling==0){
            notsche.setChecked(true);
            notsche.setSelected(true);
            time.setCurrentHour(0);
            time.setCurrentMinute(0);
            //message.setText("Happy Birthday...!!!");

            //the below code are used to show default sms and time.
            db=dbhandler.getDb();
            query="select * FROM schedule_table where schedule_id=-1";
            c=db.rawQuery(query,null);
            c.moveToFirst();
            int saved_hour=c.getInt(c.getColumnIndex("hour"));
            int saved_minute=c.getInt(c.getColumnIndex("minute"));
            String saved_message=c.getString(c.getColumnIndex("message"));
            //Toast.makeText(mactivity,saved_hour+"\n"+saved_minute+"\n"+saved_message+"\n yogesh",Toast.LENGTH_LONG).show();
            time.setCurrentHour(saved_hour);
            time.setCurrentMinute(saved_minute);
            message.setText(saved_message);
        }

        else{
            sche.setSelected(true);
            sche.setChecked(true);

            db=dbhandler.getDb();
            try {
                query = "select * FROM schedule_table where schedule_id=" + scheduling;
                c = db.rawQuery(query, null);
                c.moveToFirst();
            }catch(Exception e){
                query = "select * FROM schedule_table where schedule_id=-1";
                c = db.rawQuery(query, null);
                c.moveToFirst();
            }

            int saved_hour=c.getInt(c.getColumnIndex("hour"));
            int saved_minute=c.getInt(c.getColumnIndex("minute"));
            String saved_message=c.getString(c.getColumnIndex("message"));
            //Toast.makeText(mactivity,saved_hour+"\n"+saved_minute+"\n"+saved_message+"\n yogesh",Toast.LENGTH_LONG).show();
            time.setCurrentHour(saved_hour);
            time.setCurrentMinute(saved_minute);
            message.setText(saved_message);

        }

        name_main.setText(name);
        to.setText(phone);

        //Toast.makeText(mactivity,""+time_new_hour+"\n"+time_new_minute+"\nyogesh",Toast.LENGTH_LONG).show();

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sound s=new Sound(mactivity);
                s.checkSound();
                if(CheckBack.a==13){
                    goBack();

                }else{
                    goback_profile();
                }
                CheckBack.a=1;

            }
        });

        save.setOnClickListener(new View.OnClickListener()

        {
            public void onClick(View v){
                try {
                    Sound s=new Sound(mactivity);
                    s.checkSound();
                    CheckBack.a=1;
                    int hour_clicksave=time.getCurrentHour();
                    int minute_clicksave=time.getCurrentMinute();
                    String message_clicksave=message.getText().toString();
                    db=dbhandler.getDb();
                    //Toast.makeText(mactivity,"id="+idd+"test="+test)
                    if(sche.isChecked()){
                        if(scheduling==-1){
                            String query="UPDATE birth_table SET schedule_id="+idd+" WHERE id="+idd;
                            db.execSQL(query);
                            try {
                                query = "insert into schedule_table(schedule_id,hour,minute,message,test) values(" + idd + "," + hour_clicksave + "," + minute_clicksave + ",'" + message_clicksave + "'," + 0 + ");";
                                db.execSQL(query);
                            }catch(Exception e){
                                query="UPDATE schedule_table SET hour="+hour_clicksave+" ,minute="+minute_clicksave+" ,message='"+message_clicksave+"',test=0 WHERE schedule_id="+idd+";";
                                db.execSQL(query);

                            }
                        }
                        else if(scheduling==0){
                            String query="UPDATE birth_table SET schedule_id="+idd+" WHERE id="+idd;
                            db.execSQL(query);
                            try {
                                query = "insert into schedule_table(schedule_id,hour,minute,message,test) values(" + idd + "," + hour_clicksave + "," + minute_clicksave + ",'" + message_clicksave + "'," + 0 + ");";
                                db.execSQL(query);
                            }catch(Exception e){
                                query="UPDATE schedule_table SET hour="+hour_clicksave+" ,minute="+minute_clicksave+" ,message='"+message_clicksave+"',test=0 WHERE schedule_id="+idd+";";
                                db.execSQL(query);

                            }

                        }
                        else{
                            String query="UPDATE schedule_table SET hour="+hour_clicksave+" ,minute="+minute_clicksave+" ,message='"+message_clicksave+"',test=0 WHERE schedule_id="+idd+";";
                            db.execSQL(query);
                        }
                        Toast.makeText(mactivity,"Scheduled Successfully",Toast.LENGTH_LONG).show();

                    }
                    else{
                        String query="UPDATE birth_table SET schedule_id=0 where id="+idd;
                        db.execSQL(query);
                        query="DELETE FROM schedule_table WHERE schedule_id="+idd+";";
                        db.execSQL(query);
                        Toast.makeText(mactivity,"This value is not scheduled",Toast.LENGTH_LONG).show();

                    }

                    //Toast.makeText(mactivity, "" + time_new_hour + "\n" + time_new_minute, Toast.LENGTH_LONG).show();
                }catch(Exception e){
                    Toast.makeText(mactivity,e.toString(),Toast.LENGTH_LONG).show();
                }
                goBack();

            }
        });


    }

    public void goBack(){
        //Toast.makeText(mactivity,"Scheduled Successfully",Toast.LENGTH_LONG).show();

        Schedule fragment = new Schedule();
        FragmentTransaction fragmentTransaction = myContext.getSupportFragmentManager().beginTransaction();
        fragmentTransaction = myContext.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment)
                .addToBackStack("b").commit();
    }

    public void goback_profile(){
        Details fragment = new Details();
        FragmentTransaction fragmentTransaction = myContext.getSupportFragmentManager().beginTransaction();
        fragmentTransaction = myContext.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment)
                .addToBackStack("b").commit();
    }

    @Override
    public void onAttach(Activity activity) {
        myContext=(FragmentActivity)activity;
        super.onAttach(activity);
    }



}
