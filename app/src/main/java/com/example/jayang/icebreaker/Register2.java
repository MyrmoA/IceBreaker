package com.example.jayang.icebreaker;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register2 extends AppCompatActivity {
    Button mButton ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        mButton =  (Button)findViewById(R.id.signup);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Register2.this);
                alertDialogBuilder.setTitle("Email Verification");
                alertDialogBuilder.setIcon(R.mipmap.ic_launcher);
                alertDialogBuilder.setMessage("Enter email verification code we send to your registered email");

                EditText editText = new EditText(Register2.this);
                alertDialogBuilder.setView(editText);




                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Confirm",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int id) {
                                        Toast.makeText(getBaseContext(),"Sign up success",Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                        startActivity(intent);
                                        finish();

                                    }
                                })
                        .setNegativeButton("Discard",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int id) {
                                        dialog.cancel();
                                    }
                                });
                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();
                // show it
                alertDialog.show();


            }
        });



    }
}
