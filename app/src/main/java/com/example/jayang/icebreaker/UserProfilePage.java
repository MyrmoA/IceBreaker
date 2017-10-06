package com.example.jayang.icebreaker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class UserProfilePage extends AppCompatActivity {
    Toolbar profileToolbar;
    ImageView mImageView;
    Button mButton;
    TextView fullname_tv, emailaddress_tv,username_tv;
    String username;
    User user;
    FirebaseDatabase database;
    DatabaseReference myRef;
    String Uid;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_page);
        profileToolbar = (Toolbar) findViewById(R.id.profileToolbar);
        setSupportActionBar(profileToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mImageView = (ImageView) findViewById(R.id.profileButton);
        mButton = (Button) findViewById(R.id.compareBtn);
        fullname_tv = (TextView) findViewById(R.id.name_tv);
        emailaddress_tv = (TextView) findViewById(R.id.email_tv);

        Bundle bundle = getIntent().getExtras();
        username = bundle.getString("user_info");
        username_tv = (TextView) findViewById(R.id.username_tv);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Usernames");
       




    }


    public void updateUserProfilePage() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    if (childSnapshot.getValue().equals(username)) {
                        Uid = childSnapshot.getKey();
                        Toast.makeText(getBaseContext(), "this is" + Uid, Toast.LENGTH_SHORT).show();
                        findUserInfo(Uid);


                    }


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }



    @Override
    protected void onStart() {
        super.onStart();

        Toast.makeText(this,"onstart",Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this,"onResume",Toast.LENGTH_SHORT).show();
        updateUserProfilePage();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this,"onRestart",Toast.LENGTH_SHORT).show();

    }


    public void findUserInfo(final String mUid){


        myRef = database.getReference("Users");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot childSnapshot : dataSnapshot.getChildren()){
                 if(childSnapshot.getKey().equals(mUid)) {
                     User muser = childSnapshot.getValue(User.class);

                     user = muser;
                 }
                    if (user != null) {
                        Picasso.with(getBaseContext()).load(user.getPhotourl()).into(mImageView);
                        getSupportActionBar().setTitle(user.getFirstname());
                        fullname_tv.setText(user.getFirstname() +" "+ user.getLastname());
                        emailaddress_tv.setText(user.getEmail());
                        username_tv.setText(username);



                    }



                }
                Log.d("hehe", mUid);
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("haha","pfailed");
            }
        });
    }
}
