package com.yogeshbirthdaywisher.birthdaywisher.basic_view_manager;


import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.yogeshbirthdaywisher.birthdaywisher.R;
import com.yogeshbirthdaywisher.birthdaywisher.database.model_ScheduleTable;
import com.yogeshbirthdaywisher.birthdaywisher.date_conversion.Conversion;
import com.yogeshbirthdaywisher.birthdaywisher.database.DataBase;
import com.yogeshbirthdaywisher.birthdaywisher.others.Capitalize;
import com.yogeshbirthdaywisher.birthdaywisher.others.CheckBack;
import com.yogeshbirthdaywisher.birthdaywisher.others.Sound;
import com.yogeshbirthdaywisher.birthdaywisher.scheduleManager.CustomAdapterSchedule;

import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class Schedule extends Fragment {
    Button button;
    DataBase dbhandler;
    SQLiteDatabase db;
    Activity mactivity;
    FragmentActivity mycontext;
    TextView textView;
    LinearLayout.LayoutParams button_params;
    LinearLayout relayout;
    Display display;
    private FragmentActivity myContext;
    String name2[],phone2[],dob2[];
    int idd2[];
    int count=0;

    int sorting_table_id[];
    int sorting_table_date[];

    ListView mylist;

    MediaPlayer mp;
    public String []array_BS;
    public String []array_AD;




    public Schedule() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_viewlist, container, false);

        View myview = inflater.inflate(R.layout.fragment_viewlist, container, false);


        mactivity = this.getActivity();
        mp = MediaPlayer.create(mactivity, R.raw.pop);

        this.array_BS = new String[] {
                "Baisakh", "Jestha", "Ashar", "Shrawan", "Bhadra","Aswin","Karthik","Mangshir","Poush","Magh","Falgun","Chaitra"
        };
        this.array_AD=new String[]{
                "jan","Feb","Mar","Apr","May","June","July","Aug","Sep","Oct","Nov","Dec"
        };

        dbhandler = new DataBase(mactivity, null, null, 1);
        // button = (Button) myview.findViewById(R.id.button_member);
        relayout = (LinearLayout) myview.findViewById(R.id.relayout);
        mylist=(ListView)myview.findViewById(R.id.listView);
        // button.setId(0);
        // textView=(TextView)myview.findViewById(R.id.viewall);
        readdatabase();

        int todayyear,todaymonth,todayday;
        Date dt=new Date();
        todayyear=dt.getYear();
        todaymonth=dt.getMonth();
        todayday=dt.getDay();

        //Toast.makeText(mactivity,""+dt.toString(),Toast.LENGTH_LONG).show();


        listViewClicked();


        return myview;
    }

    public void readdatabase() {

        String name, phone, dob;
        int idd;
        String query = "select * FROM birth_table where 1";
        int a = 0;
        try {

            db = dbhandler.getDb();

            //Cursor d = db.rawQuery(query, null);
            //d.moveToFirst();
            count=0;
            /*while (!d.isAfterLast()) {
                count++;
            }
            //d.moveToFirst();
            d.close();*/
            Cursor c=db.rawQuery(query,null);
            c.moveToFirst();
            while(!c.isAfterLast()){
                count++;
                c.moveToNext();
            }

            sorting_table_id=new int[count];
            sorting_table_date=new int[count];
            // Toast.makeText(mactivity,"count="+count,Toast.LENGTH_LONG).show();
            c.moveToFirst();
            name2=new String[count];
            phone2=new String[count];
            dob2=new String [count];
            idd2=new int[count];
            int b = 0;

            while (!c.isAfterLast()) {


                name = c.getString(c.getColumnIndex("name"));
                phone = c.getString(c.getColumnIndex("phone"));
                dob = c.getString(c.getColumnIndex("dob"));
                idd=c.getInt(c.getColumnIndex("id"));
                int month_database=c.getInt(c.getColumnIndex("month"));
                int day_database=c.getInt(c.getColumnIndex("day"));
                int format_database=c.getInt(c.getColumnIndex("format"));
                c.moveToNext();
                name2[a]=name;
                phone2[a]=phone;
                dob2[a]=dob;
                idd2[a]=idd;
                //Toast.makeText(mactivity, name+"\n"+phone+"\n"+dob+"\n"+idd, Toast.LENGTH_LONG).show();
                showdata(name, phone, dob,idd,month_database,day_database,format_database, b);
                // textView.setText(name+"  "+phone+"  "+dob+"\n");
                a++;
                b++;

            }
            c.close();
            db.close();
            sortdata();
            printresult();
        } catch (Exception e) {
            Toast.makeText(mactivity, "exception 1\n"+e.toString(), Toast.LENGTH_LONG).show();
        }
        //Toast.makeText(mactivity, "" + a + " results", Toast.LENGTH_LONG).show();


    }

    public void showdata(String name, String phone, String dob,int idd,int month_database,int day_database,int format, int b) {

        // display = mactivity.getWindowManager().getDefaultDisplay();

        //ArrayList<String> newlist;
        //Toast.makeText(mactivity,"in show data",Toast.LENGTH_LONG).show();
        int month,day,year;
        month=0;
        day=0;
        year=0;
        dob="2051/11/12";
        try {
            //Toast.makeText(mactivity,dob.substring(0,4)+"\n"+dob.substring(5, 7)+"\n"+dob.substring(8, 10),Toast.LENGTH_LONG).show();
            //Toast.makeText(mactivity,"my name is yogesh",Toast.LENGTH_LONG).show();
            year = Integer.parseInt(dob.substring(0, 4));



            if(dob.substring(6,7).equals(".")||dob.substring(6,7).equals("/")||dob.substring(6,7).equals("-")){
                // Toast.makeText(mactivity,"only one character month",Toast.LENGTH_LONG).show();
                month=Integer.parseInt(dob.substring(5,6));
                try {
                    day = Integer.parseInt(dob.substring(7, 9));
                }catch(Exception e){
                    day=Integer.parseInt(dob.substring(7,8));
                }

            }
            else {
                month = Integer.parseInt(dob.substring(5, 7));
                day = Integer.parseInt(dob.substring(8, 10));
            }
            //Toast.makeText(mactivity,year+"\n"+month+"\n"+day,Toast.LENGTH_LONG).show();
        }catch(Exception e){
            Toast.makeText(mactivity,"exceeption 2\n"+e.toString(),Toast.LENGTH_LONG).show();
        }

        if(format==0){
            month=month_database;
            day=day_database;
        }
        else{
            Conversion aa=new Conversion();
            aa.setDate(2017,month_database,day_database);
            month=aa.getMonth();
            day=aa.getDay();
        }



        Conversion con=new Conversion();
        con.setCurrentDate();
        int today_year=con.getYear();
        int today_month=con.getMonth();
        int today_day=con.getDay();

        int compare_var_real=month*100+day;
        // Toast.makeText(mactivity,"middle of show data"+today_year+"\n"+today_month+"\n"+today_day,Toast.LENGTH_LONG).show();

        int compare_var_today=today_month*100+today_day;
        // int todayyear2=today_year+1;
        //Toast.makeText(mactivity,"date:"+todayyear2)
        try {
            if (compare_var_real >= compare_var_today) {
                sorting_table_date[b] =  50000 + compare_var_real;
                sorting_table_id[b] = idd;
            } else {
                sorting_table_date[b] =  60000 + compare_var_real;
                sorting_table_id[b] = idd;
            }
        }catch(Exception e){
            Toast.makeText(mactivity,e.toString()+"\n"+"yogesh exception",Toast.LENGTH_LONG).show();
        }

        // Toast.makeText(mactivity,"leaving show data idd " +b+"="+idd+"\n"+sorting_table_id[b],Toast.LENGTH_LONG).show();





        /*Button mbutton = new Button(mactivity);
        mbutton.setId(b);
        mbutton.setText(name  + " \n " + dob);
        relayout.addView(mbutton);

        buttonclicked(mbutton, name, phone, dob,idd);
        */


    }

    public void sortdata(){
        // Toast.makeText(mactivity,"inside sortdata",Toast.LENGTH_LONG).show();
        for(int i=0;i<count;i++){
            for(int j=0;j<count;j++){
                if(sorting_table_date[i]<sorting_table_date[j]){
                    int temp1=sorting_table_date[i];
                    int temp2=sorting_table_id[i];
                    sorting_table_date[i]=sorting_table_date[j];
                    sorting_table_date[j]=temp1;
                    sorting_table_id[i]=sorting_table_id[j];
                    sorting_table_id[j]=temp2;

                }
            }
            //Toast.makeText(mactivity,"inside sortdata at end idd="+sorting_table_id[i],Toast.LENGTH_LONG).show();

        }

    }

    public void printresult(){
        db=dbhandler.getDb();
        // Toast.makeText(mactivity,"inside print result",Toast.LENGTH_LONG).show();

        String [] for_list=new String[count];
        for(int i=0;i<count;i++) {
            //Toast.makeText(mactivity,"inside print result"+sorting_table_id[i],Toast.LENGTH_LONG).show();

            String query = "select * FROM birth_table where id=" +sorting_table_id[i];
            //Toast.makeText(mactivity,"before cursor"+sorting_table_id[0],Toast.LENGTH_LONG).show();

            Cursor c=db.rawQuery(query,null);

            c.moveToFirst();
            // Toast.makeText(mactivity,"after cursor"+sorting_table_id[i],Toast.LENGTH_LONG).show();

            try {
                String name = c.getString(c.getColumnIndex("name"));
                String phone = c.getString(c.getColumnIndex("phone"));
                String dob = c.getString(c.getColumnIndex("dob"));
                int month = c.getInt(c.getColumnIndex("month"));
                int day = c.getInt(c.getColumnIndex("day"));
                int idd = c.getInt(c.getColumnIndex("id"));
                int format=c.getInt(c.getColumnIndex("format"));
                int schedule_id=c.getInt(c.getColumnIndex("schedule_id"));

                if(phone.length()==0){
                    phone="empty";
                }
                name=Capitalize.capitalize(name);
                String timer="Not Scheduled";

                //now we are putting data about scheduled time
                if(schedule_id==-1)
                {
                    timer="Scheduled at: "+"0:0";
                }
                else if(schedule_id!=0){
                    try {
                        model_ScheduleTable time = new model_ScheduleTable();
                        time.getValue(mactivity, idd);
                        int hour = time.getHour();
                        int minute = time.getMinute();
                        timer = "Scheduled at: "+hour + ":" + minute;
                    }catch(Exception e){
                        Toast.makeText(mactivity,e.toString(),Toast.LENGTH_LONG).show();
                    }
                }




                if(format==0) {
                    for_list[i] = name + "\n" + phone + " \n " + array_BS[month-1] + "-" + day+"\n"+timer;
                }
                else{
                    for_list[i] = name + "\n" + phone + " \n " + array_AD[month-1] + "-" + day+"\n"+timer;

                }


            }catch (Exception e){
                Toast.makeText(mactivity,e.toString(),Toast.LENGTH_LONG).show();
            }

        }

        ListAdapter yogeshAdapter=new CustomAdapterSchedule(mactivity,for_list);


//        ListAdapter yogeshAdapter=new ArrayAdapter<String>(mactivity,android.R.layout.simple_list_item_1,for_list);
         mylist.setAdapter(yogeshAdapter);

    }
    public void listViewClicked(){
        mylist.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Sound s=new Sound(mactivity);
                        s.checkSound();

                        String query = "select * FROM birth_table where id=" +sorting_table_id[position];
                        db=dbhandler.getDb();
                        Cursor c=db.rawQuery(query,null);
                        c.moveToFirst();

                        String name_list = c.getString(c.getColumnIndex("name"));
                        String phone = c.getString(c.getColumnIndex("phone"));
                        String dob = c.getString(c.getColumnIndex("dob"));
                        int idd = c.getInt(c.getColumnIndex("id"));

                        query="UPDATE selector SET name='"+name_list+"',phone='"+phone+"',dob='"+dob+"',idd="+idd+" WHERE id=1";
                        db.execSQL(query);

                        try {


                            ScheduleDetail fragment = new ScheduleDetail();
                            FragmentTransaction fragmentTransaction = myContext.getSupportFragmentManager().beginTransaction();
                            fragmentTransaction = myContext.getSupportFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.fragment_container, fragment)
                                    .addToBackStack("b").commit();


                            //
                            CheckBack.a = 1;
                        }catch(Exception e){
                            Toast.makeText(mactivity,e.toString(),Toast.LENGTH_LONG).show();
                        }


                        //Toast.makeText(mactivity,name_list,Toast.LENGTH_LONG).show();


                    }
                }
        );
    }


    public void buttonclicked(Button mbutton, final String name, final String phone, final String dob,final int idd) {
        mbutton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        Sound s=new Sound(mactivity);
                        s.checkSound();
                        //Toast.makeText(mactivity, name + phone + "", Toast.LENGTH_LONG).show();
                        try {
                            db=dbhandler.getDb();
                            String query="UPDATE selector SET name='"+name+"',phone='"+phone+"',dob='"+dob+"',idd="+idd+" WHERE id=1";
                            db.execSQL(query);

                            // Float a=Float.valueOf(phone);
                            Details fragment = new Details();
                            FragmentTransaction fragmentTransaction = myContext.getSupportFragmentManager().beginTransaction();
                            fragmentTransaction = myContext.getSupportFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.fragment_container,fragment)
                                    .addToBackStack("b").commit();

                            //Toast.makeText(mactivity, "add friend", Toast.LENGTH_LONG).show();
                        }catch(Exception e){
                            Toast.makeText(mactivity,e.toString(),Toast.LENGTH_LONG).show();
                        }

                    }
                }
        );

    }

    @Override
    public void onAttach(Activity activity) {
        myContext=(FragmentActivity)activity;
        super.onAttach(activity);
    }



}
