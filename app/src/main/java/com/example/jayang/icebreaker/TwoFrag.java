package com.example.jayang.icebreaker;

import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Jayang on 9/18/2017.
 */

public class TwoFrag extends android.support.v4.app.Fragment{
    FirebaseDatabase database;
    DatabaseReference ref;
    ArrayList<User> mUserArrayList;
    RecyclerView mRecyclerView;
    public TwoFrag() {
    }




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserArrayList = new ArrayList<>();

        Log.d("haha","on create");
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Users");







    }


    @Override
    public void onResume() {
        super.onResume();
        new mAsyn().execute();


    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.tab2,container,false);
        mRecyclerView = view.findViewById(R.id.userlist);








        //Convert xml to java view objext

        return view;
    }


    public void RetrieveUserData(){
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mUserArrayList.clear();
                for(DataSnapshot userSnapshot: dataSnapshot.getChildren()){
                    User user = userSnapshot.getValue(User.class);
                   Log.d("haha",user.getEmail().toString());
                    mUserArrayList.add(user);
                 }

                if(mUserArrayList!=null) {
                    Collections.sort(mUserArrayList);
                    RecyclerAdapter recyclerAdapter = new RecyclerAdapter(mUserArrayList,getActivity());
                    recyclerAdapter.notifyDataSetChanged();
                    mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),1));

                    mRecyclerView.setAdapter(recyclerAdapter);
                    mRecyclerView.setHasFixedSize(true);
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getActivity().getBaseContext(),"The Read Failed:"+
                        databaseError.getMessage().toString(),Toast.LENGTH_SHORT).show();

            }
        });

        }

        private class mAsyn extends AsyncTask<Void,Void,Void>{
            @Override
            protected Void doInBackground(Void... voids) {
                RetrieveUserData();
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);



            }
        }

    }



