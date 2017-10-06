package com.example.jayang.icebreaker;

import android.content.Intent;
import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * Created by yjj781265 on 10/4/2017.
 */

public class ViewHolder extends RecyclerView.ViewHolder{
    public ImageView avatar;
    public TextView fullname;
    public TextView username;
    public ViewHolder(final View itemView) {
        super(itemView);
        avatar =(ImageView)itemView.findViewById(R.id.avatar);
        fullname =(TextView) itemView.findViewById(R.id.fullname);
        username =(TextView)itemView.findViewById(R.id.username_id);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

// rippleeffect on itemview in User tab
                int[] attrs = new int[]{R.attr.selectableItemBackground};
                TypedArray typedArray = itemView.getContext().obtainStyledAttributes(attrs);
                int backgroundResource = typedArray.getResourceId(0, 0);
                itemView.setBackgroundResource(backgroundResource);

                Intent intent = new Intent(view.getContext(), UserProfilePage.class);
                intent.putExtra("user_info",username.getText().toString());
                view.getContext().startActivity(intent);

            }
        });
    }
}
