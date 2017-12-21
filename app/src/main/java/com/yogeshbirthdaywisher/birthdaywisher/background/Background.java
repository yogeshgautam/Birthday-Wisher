package com.yogeshbirthdaywisher.birthdaywisher.background;


import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

import com.yogeshbirthdaywisher.birthdaywisher.date_conversion.Conversion;
import com.yogeshbirthdaywisher.birthdaywisher.database.DataBase;
import com.yogeshbirthdaywisher.birthdaywisher.R;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Background extends Service {

    DataBase dbhandler;
    SQLiteDatabase db;
    int sorting_table_id[];
    int sorting_table_date[];
    public Context Context;



    public Background(){
       // Toast.makeText(this.getApplicationContext(),"started service",Toast.LENGTH_LONG).show();

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


            Checking checking = new Checking(this);
            checking.startbackgroundtask();

        return 1;




        //return super.onStartCommand(intent, flags, startId);
    }

    public void onDestroy()
    {

        Toast.makeText(this, "Service is destroyed", Toast.LENGTH_LONG).show();
    }

    /**
     * Created by yogesh on 5/5/2017.
     */
    public static class Checking {
        int a=1;
        DataBase dbhandler;
        SQLiteDatabase db;
        public static Context context;
        int notification,sms;

        NotificationCompat.Builder notify;

        Handler handler = new Handler()
        {
            @Override
            public void handleMessage(Message m)
            {

                NotificationCompat.BigTextStyle big_text = new NotificationCompat.BigTextStyle();
                big_text.setBigContentTitle("Birthday Wisher");
                //big_text.bigText("The birthday wish has been sent to");

                big_text.bigText(m.getData().getString("yogesh"));
                //big_text.setBigContentTitle("Routine schedule");

               // Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                notify = new NotificationCompat.Builder(context);
                notify.setAutoCancel(true);
                notify.setSmallIcon(R.drawable.birthday_logo);
                notify.setContentTitle("Birthday Wisher");
                notify.setContentText("");
                //notify.setStyle(new NotificationCompat.BigTextStyle().bigText(m.toString()));
                notify.setStyle(big_text);
               // notify.setSound(soundUri);
                //notify.setDefaults(Notification.DEFAULT_ALL);

                NotificationManager nm = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
                nm.notify((int)(Math.random() * 10000),notify.build());
                ((Vibrator)context.getSystemService(VIBRATOR_SERVICE)).vibrate(2000);


            }
        };



        public Checking(Context c){
            context=c;
            dbhandler=new DataBase(context,null,null,1);


        }


        public void startbackgroundtask(){
            Runnable r=new Runnable() {
                @Override
                public void run() {
                    Log.i("yogesh","inside thread");


                    while(true) {


                        int count = 0;

                        synchronized(this)
                        {
                            try{
                                wait(1000);
                            }catch(Exception e){}
                        }
                        getGlobalValues();



                        String query = "select * FROM birth_table where 1";
                        db = dbhandler.getDb();
                        Cursor c = db.rawQuery(query, null);


                        c.moveToFirst();
                        int i = -1;

                        int yog=0;
                        String dob,phone;
                        int idd,schedule_id,month,day,format;

                        while (!c.isAfterLast()) {

                            yog++;
                            dob = c.getString(c.getColumnIndex("dob"));
                            idd = c.getInt(c.getColumnIndex("id"));
                            schedule_id = c.getInt(c.getColumnIndex("schedule_id"));
                            phone=c.getString(c.getColumnIndex("phone"));
                            month=c.getInt(c.getColumnIndex("month"));
                            day=c.getInt(c.getColumnIndex("day"));
                            format=c.getInt(c.getColumnIndex("format"));

                            insidecheck(dob,schedule_id,phone,month,day,format);
                           // notification("yg");
                            i++;
                            c.moveToNext();

                        }

                        c.close();
                        db.close();
                    }
                }
            };
            //password:4336572F78
            Thread thread=new Thread(r);
            thread.start();

        }

        public void sendmessage(String phone,String message){
            ((Vibrator)context.getSystemService(VIBRATOR_SERVICE)).vibrate(2000);

            SmsManager smsManager=SmsManager.getDefault();
            smsManager.sendTextMessage(phone,null,message,null,null);

        }





        public void insidecheck(String dob,int schedule_id,String phone,int dmonth,int dday,int dformat){

            int month, day, year;
            month = 0;
            day = 0;
            year = 0;
            String query;
            Message m;
            Bundle b=new Bundle();
            Conversion today = new Conversion();

            if(dformat==0) {

                /*year = Integer.parseInt(dob.substring(0, 4));
                if (dob.substring(6, 7).equals(".") || dob.substring(6, 7).equals("/") || dob.substring(6, 7).equals("-")) {
                    // Toast.makeText(mactivity,"only one character month",Toast.LENGTH_LONG).show();
                    month = Integer.parseInt(dob.substring(5, 6));
                    try {
                        day = Integer.parseInt(dob.substring(7, 9));
                    } catch (Exception e) {
                        day = Integer.parseInt(dob.substring(7, 8));
                    }

                } else {
                    month = Integer.parseInt(dob.substring(5, 7));
                    day = Integer.parseInt(dob.substring(8, 10));
                }*/
                month=dmonth;
                day=dday;
            }
            else{
                month=dmonth;
                day=dday;
            }


            int day_today,month_today,hour_today,minute_today;
            if(dformat==0) {
                today.setCurrentDate();
                day_today = today.getDay();
                month_today = today.getMonth();

                hour_today = new Time(System.currentTimeMillis()).getHours();
                minute_today = new Time(System.currentTimeMillis()).getMinutes();
            }else{
                //day_today=new Time(System.currentTimeMillis()).getDay();
                //month_today=new Time(System.currentTimeMillis()).getMonth();
                Date dNow = new Date();
                SimpleDateFormat ft = new SimpleDateFormat("yyyy");
                int y = new Integer(ft.format(dNow));
                ft = new SimpleDateFormat("MM");
                int mo = new Integer(ft.format(dNow));
                ft = new SimpleDateFormat("dd");
                int d = new Integer(ft.format(dNow));

                day_today=d;
                month_today=mo;
                hour_today=new Time(System.currentTimeMillis()).getHours();
                minute_today=new Time(System.currentTimeMillis()).getMinutes();
            }


            if (day_today == day && month_today == month) {


                // db = dbhandler.getDb();
                //notification("inside");


                if (schedule_id == 0) {


                }
                else {
                    String query2 = "SELECT * FROM schedule_table WHERE schedule_id=" + schedule_id;

                    Cursor d = db.rawQuery(query2, null);

                    d.moveToFirst();
                    int hour = d.getInt(d.getColumnIndex("hour"));
                    int minute = d.getInt(d.getColumnIndex("minute"));
                    String message = d.getString(d.getColumnIndex("message"));
                    int test = d.getInt(d.getColumnIndex("test"));
                    d.close();




                    // Toast.makeText(context,""+test,Toast.LENGTH_LONG).show();

                    //notification("yoegsh");

                    if (hour == hour_today) {
                        //Toast.makeText(context,""+test,Toast.LENGTH_LONG).show();
                        //notification("inside");
                        if (minute == minute_today) {
                            //notification("inside");


                            if (test == 1) {
                                //Toast.makeText(context, "smS is not scheduled", Toast.LENGTH_LONG).show();

                            }
                            else if (test == 2) {
                                //Toast.makeText(context, "smS is not scheduled", Toast.LENGTH_LONG).show();

                                if (day != day_today || month != month_today) {
                                    query = "UPDATE schedule_table SET test=0 WHERE schedule_id=" + schedule_id;
                                    db.execSQL(query);

                                }

                            }
                            else if(test == 0)  {
                                //Toast.makeText(context, "smS sent", Toast.LENGTH_LONG).show();
                                //try {
                                if(notification==1) {
                                    message = message + ".";
                                    if(sms==1) {
                                        try {
                                            sendmessage(phone, message);
                                            b.putString("yogesh", "The birthday wish has been sent to,  phone:" + phone + ",  message:" + message);

                                        }catch(Exception e){
                                            b.putString("yogesh","Sorry Some error occured , your message was not sent " +"..."+ message);

                                            //Toast.makeText(context.getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
                                        }
                                    }else{
                                        b.putString("yogesh", "Today is birthday of,  phone:" + phone );

                                    }
                                    m = new Message();
                                    m.setData(b);
                                    handler.sendMessage(m);
                                }
                                if(sms==1) {
                                    try {
                                        if (phone != "") {
                                            sendmessage(phone, message);
                                        }
                                    }catch(Exception e){

                                    }
                                }
                                //notification("correct");

                                query = "UPDATE schedule_table SET test=2 WHERE schedule_id=" + schedule_id;
                                db.execSQL(query);
                                // }catch(Exception e){
                                // notification("correct");

                                // }
                            }
                        }

                    }

                }
            }

        }

        public void getGlobalValues(){
            String query = "select * FROM selector where 1";

                db = dbhandler.getDb();
                Cursor c = db.rawQuery(query, null);
                c.moveToFirst();
                notification = c.getInt(c.getColumnIndex("notification"));
                sms = c.getInt(c.getColumnIndex("sms"));

        }

    }
}
