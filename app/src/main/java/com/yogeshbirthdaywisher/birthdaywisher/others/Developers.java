package com.yogeshbirthdaywisher.birthdaywisher.others;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yogeshbirthdaywisher.birthdaywisher.R;

/**
 * Created by yogesh on 7/12/2017.
 */
public class Developers extends Fragment {

    public Developers(){

    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myview = inflater.inflate(R.layout.developers, container, false);

        return myview;
    }
}
