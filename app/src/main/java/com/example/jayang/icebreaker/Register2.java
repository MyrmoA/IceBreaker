package com.example.jayang.icebreaker;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register2 extends AppCompatActivity {
    Button mButton ; // signup button
    Toolbar mToolbar;
    EditText email,password,cpassword;
    String  userEmail, userPassword,userCpassword,firstname,lastname,username,url;
    final String TAG ="IcebreakerApp" ;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
 /*Layout Component*/
        mButton = (Button) findViewById(R.id.signup);
        mToolbar = (Toolbar) findViewById(R.id.toolbar2);
        mToolbar.setNavigationIcon(R.mipmap.ic_launcher);
        email = (EditText) findViewById(R.id.email_R);
        password = (EditText) findViewById(R.id.password_R);
        cpassword = (EditText) findViewById(R.id.Cpassword_R);
        url = "https://st3.depositphotos.com/1006318/13470/v/1600/depositphotos_134700318-stock-illustration-profile-icon-male-avatar-man.jpg";





        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptRegistration();

            }
        });
        cpassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                attemptRegistration();
                return false;
            }
        });
    }




   private void attemptRegistration(){
       email.setError(null);
       password.setError(null);
       cpassword.setError(null);

        /*String*/
       // Store values at the time of the login attempt.
       userEmail = email.getText().toString();
       userPassword =password.getText().toString();
       userCpassword = cpassword.getText().toString();

       Bundle extras = getIntent().getExtras();
       if(extras!=null) {
           firstname =extras.getString("firstname");
           lastname = extras.getString("lastname");
           username = extras.getString("username");
       }

       boolean cancel = false;
       View focusView = null;

       // Check for a valid email address.
       if (TextUtils.isEmpty(userEmail)) {
           email.setError(getString(R.string.error_field_required));
           focusView = email;
           cancel = true;
       } else if (!isEmailValid(userEmail)) {
           email.setError(getString(R.string.error_invalid_email));
           focusView = email;
           cancel = true;
       }

       // Check for a valid password, if the user entered one.
       if (!TextUtils.isEmpty(userPassword) && userPassword.length()<6) {
           password.setError(getString(R.string.error_invalid_password));
           focusView = password;
           cancel = true;
       }else if (TextUtils.isEmpty(userPassword)){
           password.setError(getString(R.string.error_field_required));
           focusView = password;
           cancel = true;
       }else if (TextUtils.isEmpty(userCpassword)){
           cpassword.setError(getString(R.string.error_field_required));
           focusView = cpassword;
           cancel = true;
       }
       else if(!TextUtils.isEmpty(userPassword) &&!TextUtils.isEmpty(userCpassword) &&
               !userPassword.equals(userCpassword)){
           cpassword.setError("Password does not match");
           focusView = cpassword;
           cancel = true;
       }



       if (cancel) {
           // There was an error; don't attempt login and focus the first
           // form field with an error.
           focusView.requestFocus();
       } else {
           // firebase signup
           hideKeyboard();
           createAccount(userEmail,userPassword,firstname,lastname,username);
       }
   }

    //Firebase auth user signup method createAccount(,);
    public void createAccount(final String memail, String mpassword, final String mfirstName, final String mlastName,
                              final String muserName) {

        final ProgressDialog progressDialog = ProgressDialog.show(this, "Icebreaker", "Signing-up", true);
        mAuth.createUserWithEmailAndPassword(memail, mpassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.d(TAG, "onComplete: Failed=" + task.getException().getMessage()); //ADD THIS

                            progressDialog.dismiss();
                            Toast.makeText(Register2.this, task.getException().getMessage(),
                                    Toast.LENGTH_LONG).show();

                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(Register2.this, "Sign-up success",
                                    Toast.LENGTH_SHORT).show();
                            FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                            String Uid = currentFirebaseUser.getUid();

                            writeNewUser(Uid, muserName, memail,mfirstName,mlastName,url);
                            addtoUsernameList(muserName);
                            finishAffinity();
                            Intent intent = new Intent(getBaseContext(),MainActivity.class);
                            startActivity(intent);

                        }
                    }
                });
    }

    //write userdata info to the firebase user table/documents
    private void writeNewUser(String userId, String username, String email,String firstname,
                              String lastname,String url) {


        User user = new User(username,email,firstname,lastname,url);

        mDatabase.child("Users").child(userId).setValue(user);
    }

    private void addtoUsernameList(String mUsername){
        mDatabase.child("Usernames").push().setValue(mUsername);
    }


    //hide softkeyboard
    private void hideKeyboard(){
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private boolean isEmailValid(String email) {
        // You can add more checking logic here.
        return email.contains("@");
    }




}
