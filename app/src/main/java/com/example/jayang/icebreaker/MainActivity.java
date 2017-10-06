package com.example.jayang.icebreaker;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
     Button mlogin;
    TextView needAccount;
    EditText email_tb,password_tb;
    String email,password;
    final String TAG = "LoinScreen";
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mlogin = (Button)findViewById(R.id.login_button);
        needAccount =(TextView)findViewById(R.id.register_button);
        email_tb =(EditText)findViewById(R.id.email_textbox);
        password_tb =(EditText)findViewById(R.id.password_textbox);
        mAuth = FirebaseAuth.getInstance();






    }

    @Override
    protected void onStart() {
        super.onStart();
        needAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(),RegisterPage.class);
                startActivity(intent);



            }
        });

        mlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email =email_tb.getText().toString();
                password = password_tb.getText().toString();
                if(email.isEmpty()){
                    email_tb.setError(getString(R.string.error_field_required));
                    email_tb.requestFocus();
                }else if(password.isEmpty()){
                    password_tb.setError(getString(R.string.error_field_required));
                    password_tb.requestFocus();
                }else {

                    userSignin(email, password);
                    Intent intent = new Intent(getBaseContext(), HomePage.class);
                    startActivity(intent);
                    finish();

                }

            }
        });

    }

    private void userSignin(String email,String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());
                        if(task.isSuccessful()) {

                        }

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        else if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithEmail:failed", task.getException());
                            Toast.makeText(MainActivity.this, task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });

    }

}
