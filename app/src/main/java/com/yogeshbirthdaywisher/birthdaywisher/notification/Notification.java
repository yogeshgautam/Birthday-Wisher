package com.yogeshbirthdaywisher.birthdaywisher.notification;

import android.content.Context;
import android.support.v7.app.NotificationCompat;

import com.yogeshbirthdaywisher.birthdaywisher.R;

/**
 * Created by yogesh on 9/27/2017.
 */
public class Notification  {


    public void setNotification(Context con,String phone)
    {
        NotificationCompat.Builder mBuilder=new NotificationCompat.Builder(con);
        mBuilder.setSmallIcon(R.drawable.birthday_logo);
        mBuilder.setContentTitle("Birthday Wisher");
        mBuilder.setContentText("message sent to "+phone);
    }
}
