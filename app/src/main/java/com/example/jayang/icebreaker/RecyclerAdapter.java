package com.example.jayang.icebreaker;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yjj781265 on 10/4/2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<ViewHolder>{
    private ArrayList<User> mUserList;
    private Context mContext;

    public RecyclerAdapter(ArrayList<User> userList, Context context) {
        mUserList = userList;
        mContext =context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User user = mUserList.get(position);
        holder.fullname.setText(user.getFirstname()+" "+user.getLastname());
        holder.username.setText(user.getUsername());
        Picasso.with(mContext).load(user.getPhotourl()).into(holder.avatar);
    }

    @Override
    public int getItemCount() {
        return mUserList.size();
    }
}
