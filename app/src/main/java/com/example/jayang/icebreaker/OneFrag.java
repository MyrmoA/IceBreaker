package com.example.jayang.icebreaker;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class OneFrag extends android.support.v4.app.Fragment{
    public OneFrag(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab1,container,false);
        //Convert xml to java view objext
        return view;
    }
}
