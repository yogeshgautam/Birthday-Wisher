package com.yogeshbirthdaywisher.birthdaywisher.basic_view_manager;


import android.app.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.yogeshbirthdaywisher.birthdaywisher.R;
import com.yogeshbirthdaywisher.birthdaywisher.database.DataBase;
import com.yogeshbirthdaywisher.birthdaywisher.others.CheckBack;
import com.yogeshbirthdaywisher.birthdaywisher.others.Sound;

public class Details extends Fragment {

    Activity mactivity;
    TextView name_layout,phone_layout,dob_layout,sms_layout,default_layout;
    Switch switch_sms,switch_default;
    Button edit,delete,custom;
    DataBase dbHandler;
    SQLiteDatabase db;

    String name,phone,dob;
    int idd,format,month,day,schedule_id;
    int globalId;

    private FragmentActivity myContext;

    public String []array_BS;
    public String []array_AD;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myview = inflater.inflate(R.layout.fragment_details, container, false);
        mactivity = this.getActivity();
        try {

            name_layout = (TextView) myview.findViewById(R.id.name_text);
            phone_layout = (TextView) myview.findViewById(R.id.phone_text);
            dob_layout = (TextView) myview.findViewById(R.id.dob_text);
            sms_layout = (TextView) myview.findViewById(R.id.textView24);
            default_layout = (TextView) myview.findViewById(R.id.text_default_ans);

            switch_sms = (Switch) myview.findViewById(R.id.switch1);
            switch_default = (Switch) myview.findViewById(R.id.switch2);

            edit = (Button) myview.findViewById(R.id.button2);
            delete = (Button) myview.findViewById(R.id.button3);
            custom = (Button) myview.findViewById(R.id.button7);
            dbHandler = new DataBase(mactivity, null, null, 1);

            this.array_BS = new String[] {
                    "Baisakh", "Jestha", "Ashar", "Shrawan", "Bhadra","Aswin","Karthik","Mangshir","Poush","Magh","Falgun","Chaitra"
            };
            this.array_AD=new String[]{
                    "jan","Feb","Mar","Apr","May","June","July","Aug","Sep","Oct","Nov","Dec"
            };


            first();
        }catch(Exception e){
            Toast.makeText(mactivity,e.toString(),Toast.LENGTH_LONG).show();
        }



        edit.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                CheckBack.a=11;
                DetailEdit fragment = new DetailEdit();
                FragmentTransaction fragmentTransaction = myContext.getSupportFragmentManager().beginTransaction();
                fragmentTransaction = myContext.getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment).addToBackStack("b").commit();
                Sound s=new Sound(mactivity);
                s.checkSound();

            }
        });

        delete.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Sound s=new Sound(mactivity);
                s.checkSound();

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mactivity);
                alertDialogBuilder.setMessage("Are you Sure you want to Delete\n Warning:this is permanent.");
                alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            db=dbHandler.getDb();
                            String query = "DELETE FROM birth_table WHERE id=" + idd + ";";
                            db.execSQL(query);
                            Toast.makeText(mactivity, "Data deleted successfully", Toast.LENGTH_LONG).show();
                        } catch (Exception e) {
                            Toast.makeText(mactivity, "yogesh\n" + e.toString(), Toast.LENGTH_LONG).show();
                        }
                        goBack();

                    }
                });

                alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }

            });


        custom.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Sound s=new Sound(mactivity);
                s.checkSound();
                CheckBack.a=12;
                ScheduleDetail fragment = new ScheduleDetail();
                FragmentTransaction fragmentTransaction = myContext.getSupportFragmentManager().beginTransaction();
                fragmentTransaction = myContext.getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment).addToBackStack("b").commit();

            }
        });

        switch_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db=dbHandler.getDb();
                if(switch_sms.isChecked()){
                    if(schedule_id==0){
                        String query="UPDATE birth_table SET schedule_id=-1 WHERE id="+globalId;
                        db.execSQL(query);

                    }
                    sms_layout.setText("Enabled");

                }else{
                    String query="UPDATE birth_table SET schedule_id=0 WHERE id="+globalId;
                    db.execSQL(query);
                    sms_layout.setText("Disabled");
                }

            }
        });
        switch_default.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db=dbHandler.getDb();
                if(switch_default.isChecked()) {
                    String query = "UPDATE birth_table SET schedule_id=-1 WHERE id=" + globalId;
                    db.execSQL(query);
                    default_layout.setText("ON");
                }else{
                    default_layout.setText("OFF");


                }

            }
        });





        return myview;

    }


    public void first(){


        db=dbHandler.getDb();

        String query = "select * FROM selector where id=1";
        Cursor c=db.rawQuery(query,null);
        c.moveToFirst();
        int id=c.getInt(c.getColumnIndex("idd"));
        globalId=id;
        second(id);








    }
    public void second(int id){

        db=dbHandler.getDb();

        String query = "select * FROM birth_table where id="+id;
        try {



            Cursor c = db.rawQuery(query, null);
            c.moveToFirst();

            while (!c.isAfterLast()) {
                int b = 1;
                name = c.getString(c.getColumnIndex("name"));
                phone = c.getString(c.getColumnIndex("phone"));
                dob = c.getString(c.getColumnIndex("dob"));
                idd=c.getInt(c.getColumnIndex("id"));
                format=c.getInt(c.getColumnIndex("format"));
                month=c.getInt(c.getColumnIndex("month"));
                day=c.getInt(c.getColumnIndex("day"));
                schedule_id=c.getInt(c.getColumnIndex("schedule_id"));
                c.moveToNext();

                b++;

            }
            c.close();
            db.close();
            showdata();
        } catch (Exception e) {
            Toast.makeText(mactivity, e.toString(), Toast.LENGTH_LONG).show();
        }

    }

    public void showdata(){
        name_layout.setText(name);
        phone_layout.setText(phone);
        //dob_layout.setText("yo paxadi garxu...:D ");
        if(schedule_id==0){
            sms_layout.setText("Disabled");
            default_layout.setText("OFF");
            switch_sms.setChecked(false);
            switch_default.setChecked(false);
        }
        else{
            sms_layout.setText("Enabled");
            switch_sms.setChecked(true);
            if(schedule_id==-1){
                default_layout.setText("On");

                switch_default.setChecked(true);
            }
            else{
                default_layout.setText("Off");
                switch_default.setChecked(false);
            }

        }
        String month_tokeep;
        if(format==0){
            month_tokeep=array_BS[month-1];
        }
        else{
            month_tokeep=array_AD[month-1];
        }
        dob_layout.setText(month_tokeep+" "+day);


    }

    @Override
    public void onAttach(Activity activity) {
        myContext=(FragmentActivity)activity;
        super.onAttach(activity);
    }

    public void goBack(){
        viewlist fragment = new viewlist();
        FragmentTransaction fragmentTransaction = myContext.getSupportFragmentManager().beginTransaction();
        fragmentTransaction = myContext.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment).addToBackStack("b").commit();

    }

}