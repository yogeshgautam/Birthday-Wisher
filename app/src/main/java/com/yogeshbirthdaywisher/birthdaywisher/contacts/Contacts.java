package com.yogeshbirthdaywisher.birthdaywisher.contacts;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yogeshbirthdaywisher.birthdaywisher.R;

/**
 * Created by yogesh on 7/6/2017.
 */
public class Contacts extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myview=inflater.inflate(R.layout.fragment_details, container, false);

        return myview;
    }
}
