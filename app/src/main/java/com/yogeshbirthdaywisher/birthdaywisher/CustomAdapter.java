package com.yogeshbirthdaywisher.birthdaywisher;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by yogesh on 6/5/2017.
 */
public class CustomAdapter extends ArrayAdapter<String> {


    CustomAdapter(Context context, String [] resource) {
        super(context,R.layout.fragment_viewlist_custom ,resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater yogeshInflater=LayoutInflater.from(getContext());
        View myView=yogeshInflater.inflate(R.layout.fragment_viewlist_custom,parent,false);

        String singleItem=getItem(position);
        TextView mytext=(TextView)myView.findViewById(R.id.mytext);

        mytext.setText(singleItem);

        return myView;
    }
}
