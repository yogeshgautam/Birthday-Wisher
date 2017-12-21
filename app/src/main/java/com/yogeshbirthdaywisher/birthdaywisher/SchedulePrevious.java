package com.yogeshbirthdaywisher.birthdaywisher;


import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.yogeshbirthdaywisher.birthdaywisher.basic_view_manager.ScheduleDetail;
import com.yogeshbirthdaywisher.birthdaywisher.database.DataBase;
import com.yogeshbirthdaywisher.birthdaywisher.others.CheckBack;
import com.yogeshbirthdaywisher.birthdaywisher.others.Sound;


/**
 * A simple {@link Fragment} subclass.
 */
public class SchedulePrevious extends Fragment {

    Button button;
    DataBase dbhandler;
    SQLiteDatabase db;
    Activity mactivity;
    TextView textView;
    LinearLayout.LayoutParams button_params;
    LinearLayout relayout;
    Display display;
    private FragmentActivity myContext;

    public String []array_BS;
    public String []array_AD;
    int color_count=0;

    ListView mylist;
    int count=0,counter=0;
    int sorting_table_id[];



    public SchedulePrevious() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myview = inflater.inflate(R.layout.fragment_schedule, container, false);
        mactivity = this.getActivity();
        dbhandler = new DataBase(mactivity, null, null, 1);
        // button = (Button) myview.findViewById(R.id.button_member);
        relayout = (LinearLayout) myview.findViewById(R.id.relayout);
        mylist=(ListView)myview.findViewById(R.id.listView);

        this.array_BS = new String[] {
                "Baisakh", "Jestha", "Ashar", "Shrawan", "Bhadra","Aswin","Karthik","Mangshir","Poush","Magh","Falgun","Chaitra"
        };
        this.array_AD=new String[]{
                "jan","Feb","Mar","Apr","May","June","July","Aug","Sep","Oct","Nov","Dec"
        };


        // button.setId(0);

        // textView=(TextView)myview.findViewById(R.id.viewall);
        readdatabase();


        return myview;
    }

    public void readdatabase() {

        String name, phone, dob;
        int idd;
        String query = "select * FROM birth_table where 1";
        int a = 0;
        try {

            db = dbhandler.getDb();

            Cursor c = db.rawQuery(query, null);
            c.moveToFirst();

            while(!c.isAfterLast()){
                counter++;
                c.moveToNext();
            }
            sorting_table_id=new int[counter];


            c.moveToFirst();
            while (!c.isAfterLast()) {
                int b = 1;
                a++;
                name = c.getString(c.getColumnIndex("name"));
                phone = c.getString(c.getColumnIndex("phone"));
                dob = c.getString(c.getColumnIndex("dob"));
                idd=c.getInt(c.getColumnIndex("id"));
                a=c.getInt(c.getColumnIndex("format"));
                int month=c.getInt(c.getColumnIndex("month"));
                int day=c.getInt(c.getColumnIndex("day"));
                c.moveToNext();
                //Toast.makeText(mactivity, name+"\n"+phone+"\n"+dob+"\n"+idd, Toast.LENGTH_LONG).show();
                //showdata(name, phone, dob,idd, b,a,month,day);
                printresult();
                // textView.setText(name+"  "+phone+"  "+dob+"\n");
                b++;
                sorting_table_id[count]=idd;
                //sorting_table_id[count]=idd;
                count++;


            }
            c.close();
            db.close();
        } catch (Exception e) {
            Toast.makeText(mactivity, e.toString(), Toast.LENGTH_LONG).show();
        }
        //Toast.makeText(mactivity, "" + a + " results", Toast.LENGTH_LONG).show();


    }

    /*(public void showdata(String name, String phone, String dob,int idd, int b,int a,int month,int day) {
        // display = mactivity.getWindowManager().getDefaultDisplay();
        color_count++;
        String [] for_list=new String[count];
        Button mbutton = new Button(mactivity);
        mbutton.setId(b);
        if(color_count%2==0) {
            mbutton.setBackgroundColor(Color.parseColor("#ecd182"));
        }else{
            mbutton.setBackgroundColor(Color.parseColor("#82eccc"));

        }
        if(a==1) {
            mbutton.setText(name + " \n " + phone + " \n " + array_AD[month - 1]+" "+day);
        }else{
            mbutton.setText(name + " \n " + phone + " \n " + array_BS[month - 1]+" "+day);

        }
        relayout.addView(mbutton);

        buttonclicked(mbutton, name, phone, dob,idd);


    }*/
    public void printresult(){
        dbhandler=new DataBase(mactivity,null,null,1);
        db=dbhandler.getDb();
        //Toast.makeText(mactivity,"inside print result",Toast.LENGTH_LONG).show();

        String [] for_list=new String[count];


        for(int i=0;i<count;i++) {
            //Toast.makeText(mactivity,"inside print result"+sorting_table_id[i],Toast.LENGTH_LONG).show();

            String query = "select * FROM birth_table where id=" +sorting_table_id[i];
            //Toast.makeText(mactivity,"before cursor"+sorting_table_id[0],Toast.LENGTH_LONG).show();


            Cursor c=db.rawQuery(query,null);

            c.moveToFirst();
            //Toast.makeText(mactivity,"after cursor"+sorting_table_id[i],Toast.LENGTH_LONG).show();


            try {
                String name = c.getString(c.getColumnIndex("name"));
                String phone = c.getString(c.getColumnIndex("phone"));
                String dob = c.getString(c.getColumnIndex("dob"));
                int month = c.getInt(c.getColumnIndex("month"));
                int day = c.getInt(c.getColumnIndex("day"));
                int idd = c.getInt(c.getColumnIndex("id"));

                //...Button mbutton = new Button(mactivity);
                //...mbutton.setId(i);
                //Conversion conversion=new Conversion();
                //conversion.setDate(2017,month,day);
                //int get=conversion.getdifference();
                //...mbutton.setText(name + " \n " + month+"/"+day );
                //...relayout.addView(mbutton);
                //c.close();

                for_list[i]=name + " \n "+"\n" + month+"/"+day;

                Toast.makeText(mactivity,"inside print result",Toast.LENGTH_LONG).show();

                Toast.makeText(mactivity,""+for_list[i],Toast.LENGTH_LONG).show();

                // Toast.makeText(mactivity,"after cursor closed"+sorting_table_id[0],Toast.LENGTH_LONG).show();


                //...buttonclicked(mbutton, name, phone, dob, idd);


            }catch (Exception e){
                Toast.makeText(mactivity,e.toString(),Toast.LENGTH_LONG).show();
            }

        }
        ListAdapter yogeshAdapter=new ArrayAdapter<String>(mactivity,android.R.layout.simple_list_item_1,for_list);
        mylist.setAdapter(yogeshAdapter);
    }



    public void buttonclicked(Button mbutton, final String name, final String phone, final String dob,final int idd) {
        mbutton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        //Toast.makeText(mactivity, name + phone + "", Toast.LENGTH_LONG).show();
                        Sound s=new Sound(mactivity);
                        s.checkSound();
                        CheckBack.a=13;
                        try {
                            db=dbhandler.getDb();
                            String query="UPDATE selector SET name='"+name+"',phone='"+phone+"',dob='"+dob+"',idd="+idd+" WHERE id=1";
                            db.execSQL(query);

                            // Float a=Float.valueOf(phone);
                            ScheduleDetail fragment = new ScheduleDetail();
                            FragmentTransaction fragmentTransaction = myContext.getSupportFragmentManager().beginTransaction();
                            fragmentTransaction = myContext.getSupportFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.fragment_container, fragment).addToBackStack("b").commit();
                            //fragmentTransaction.commit();/*/

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
