package com.example.jayang.icebreaker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class RegisterPage extends AppCompatActivity {
    Button button;
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        button = (Button)findViewById(R.id.nextButton);
        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.mipmap.ic_launcher);




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(),Register2.class);
                startActivity(intent);// switch to the next activity Register2
                overridePendingTransition(0,0);
            }
        });

    }
}
