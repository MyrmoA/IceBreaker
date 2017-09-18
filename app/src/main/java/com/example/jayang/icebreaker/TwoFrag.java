package com.example.jayang.icebreaker;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Jayang on 9/18/2017.
 */

public class TwoFrag extends android.support.v4.app.Fragment{
public TwoFrag(){

}
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab2,container,false);
        //Convert xml to java view objext
        return view;
    }
}
