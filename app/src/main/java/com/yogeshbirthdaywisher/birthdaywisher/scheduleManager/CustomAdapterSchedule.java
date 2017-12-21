package com.yogeshbirthdaywisher.birthdaywisher.scheduleManager;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.yogeshbirthdaywisher.birthdaywisher.R;
import com.yogeshbirthdaywisher.birthdaywisher.database.model_BirthTable;

/**
 * Created by yogesh on 6/5/2017.
 */
public class CustomAdapterSchedule extends ArrayAdapter<String> {
    //Activity mactivity;


    public CustomAdapterSchedule(Context context, String [] resource) {

        super(context,R.layout.layout_schedule_adapter ,resource);
        Context mactivity=context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater yogeshInflater=LayoutInflater.from(getContext());
        View myView=yogeshInflater.inflate(R.layout.layout_schedule_adapter,parent,false);

        String singleItem=getItem(position);


        String lines[] = singleItem.split("\\r?\\n");



        //model_BirthTable person=new model_BirthTable();
        //person.getValue(mactivity,id);


        TextView mytext=(TextView)myView.findViewById(R.id.mytext);
        TextView name=(TextView)myView.findViewById(R.id.name_adapter);
        TextView phone=(TextView)myView.findViewById(R.id.phone_adapter);
        TextView dob=(TextView)myView.findViewById(R.id.dob_adapter);
        TextView timer=(TextView)myView.findViewById(R.id.time_adapter);

        name.setText(lines[0]);
        phone.setText(lines[1]);
        dob.setText(lines[2]);
        timer.setText(lines[3]);

        //mytext.setText(singleItem);

        return myView;
    }
}
