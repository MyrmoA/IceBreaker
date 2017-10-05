package com.example.jayang.icebreaker;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by yjj781265 on 10/4/2017.
 */

public class ViewHolder extends RecyclerView.ViewHolder{
    public ImageView avatar;
    public TextView fullname;
    public TextView username;
    public ViewHolder(View itemView) {
        super(itemView);
        avatar =(ImageView)itemView.findViewById(R.id.avatar);
        fullname =(TextView) itemView.findViewById(R.id.fullname);
        username =(TextView)itemView.findViewById(R.id.username_id);
    }
}
