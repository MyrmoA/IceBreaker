package com.example.jayang.icebreaker;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RegisterPage extends AppCompatActivity {
    Button button;
    Toolbar mToolbar;
    EditText firstname,lastname,username;
    String user_firstname,user_lastname,user_username;
    DatabaseReference ref;
    ArrayList<String> mlist;
    final int USERNAME_LENGTH =3;
    boolean uniqueness;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //layout component
        button = (Button)findViewById(R.id.nextButton);
        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.mipmap.ic_launcher);
        firstname =(EditText)findViewById(R.id.Fname_R);
        lastname=(EditText)findViewById(R.id.Lname_R);
        username =(EditText)findViewById(R.id.Uname_R);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        mlist = new ArrayList<>();

        ref = database.getReference("Usernames");







        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               registerStepone();
            }
        });



    }

    @Override
    protected void onStart() {
        super.onStart();
        mlist.clear();
        new myAsyn().execute();



    }

    @Override
    protected void onResume() {
        super.onResume();
        mlist.clear();
        new myAsyn().execute();
    }

    private void registerStepone(){
        user_firstname =firstname.getText().toString();
        user_lastname = lastname.getText().toString();
        user_username = username.getText().toString();

        //some requirement for the username
        if(user_firstname.isEmpty()){
            firstname.setError(getString(R.string.error_field_required));
            firstname.requestFocus();
        }else if(user_lastname.isEmpty()){
            lastname.setError(getString(R.string.error_field_required));
            lastname.requestFocus();

            //check uniqueness of the username

        }else if(mlist.contains(user_username)) {
            username.setError("Username already exist");
            username.requestFocus();
        }else if(user_username.isEmpty()) {
            username.setError(getString(R.string.error_field_required));
            username.requestFocus();
        }else if(user_username.contains(" ")) {
            username.setError(getString(R.string.error_field_nospace));
            username.requestFocus();
        }else if(user_username.length()<USERNAME_LENGTH){

            username.setError(getString(R.string.error_invalid_username));
            username.requestFocus();
        }else {
            Intent intent = new Intent(getBaseContext(), Register2.class);
            intent.putExtra("username", user_username);
            intent.putExtra("firstname", user_firstname);
            intent.putExtra("lastname", user_lastname);

            startActivity(intent);// switch to the next activity Register2
            overridePendingTransition(0, 0);
        }
    }


    private void createUsernameList (){



        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot userSnapshot : dataSnapshot.getChildren()){

                    String s = userSnapshot.getValue().toString();
                   mlist.add(s);

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getBaseContext(),databaseError.getMessage(),Toast.LENGTH_LONG)
                        .show();

            }
        });

    }

    //do in the background at the same time
    class myAsyn extends AsyncTask<Void,String,String>{
        @Override
        protected String doInBackground(Void... voids) {
            createUsernameList();
            return null;
        }
    }

    }





