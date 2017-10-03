package com.example.jayang.icebreaker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterPage extends AppCompatActivity {
    Button button;
    Toolbar mToolbar;
    EditText firstname,lastname,username;
    String user_firstname,user_lastname,user_username;

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





        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user_firstname =firstname.getText().toString();
                user_lastname = lastname.getText().toString();
                user_username = username.getText().toString();
                Intent intent = new Intent(getBaseContext(),Register2.class);
                intent.putExtra("username",user_username);
                intent.putExtra("firstname",user_firstname);
                intent.putExtra("lastname",user_lastname);

                startActivity(intent);// switch to the next activity Register2
                overridePendingTransition(0,0);
            }
        });

    }
}
