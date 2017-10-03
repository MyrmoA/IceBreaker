package com.example.jayang.icebreaker;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by LoLJay on 10/3/2017.
 */

public class User {
    private String username;
    private String email;
    private String firstname,lastname;


    public User(){

    }

    public User(String musername,String memail,String mfirstname,String mlastname){
       this.username =musername;
        this.email =memail;
        this.firstname = mfirstname;
        this.lastname = mlastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
