package com.sahandilshan.smartlearn;

import android.app.Application;

public class global extends Application {
        private String userRoll;


    public String getUserRoll() {
        return userRoll;
    }

    public void setUserRoll(String userRoll) {
        this.userRoll = userRoll;
    }
}



/*
    //set   -  already done...
        global global= (global)getApplicationContext();

        String tmp=documentSnapshot.getString("roll");
                global.setUserRoll(tmp);


    // get and check for redirect to appropreate page...

        global global=(global)getApplicationContext();
        String s ;
        s=global.getUserRoll();

            if(s.equals("student")){
                startActivity(new Intent(getApplicationContext(), student_redirect.class));
                finish();
            }
            if(s.equals("teacher")){
                startActivity(new Intent(getApplicationContext(), teacher_redirect.class));
                finish();
            }
 */