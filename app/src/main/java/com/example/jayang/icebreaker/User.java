package com.example.jayang.icebreaker;

import android.support.annotation.NonNull;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by LoLJay on 10/3/2017.
 */

public class User implements Comparable<User> {
    private String username;
    private String email;
    private String firstname,lastname;
    private String photourl;



    public User(){

    }

    public User(String musername,String memail,String mfirstname,String mlastname,String url){
       this.username =musername;
        this.email =memail;
        this.firstname = mfirstname;
        this.lastname = mlastname;
        this.photourl=url;
    }


    public int compareTo(User mUser) {
        return firstname.compareTo(mUser.getFirstname());
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

    public String getPhotourl() {
        return photourl;
    }

    public void setPhotourl(String photourl) {
        this.photourl = photourl;
    }
}
