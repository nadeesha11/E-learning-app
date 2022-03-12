package com.sahandilshan.smartlearn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;


public class clzroom extends AppCompatActivity {
    private Button btnmat;
    private Button btnquiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clzroom);

        btnmat=findViewById(R.id.btnmat);
        btnquiz=findViewById(R.id.btnquiz);

        btnmat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(clzroom.this,Mat_MainActivity.class);
                startActivity(intent);
            }
        });
        btnquiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                Intent intent = new Intent(clzroom.this,Client_SplashActivity.class);
                startActivity(intent);
                */

                //check user roll and redirect appropriate page
                global global=(global)getApplicationContext();
                String s ;
                s=global.getUserRoll();

                if(s.equals("student")){
                    startActivity(new Intent(getApplicationContext(), Client_SplashActivity.class));
                    finish();
                }
                if(s.equals("teacher")){
                    startActivity(new Intent(getApplicationContext(), Admin_CategoryActivity.class));
                    finish();
                }



            }
        });

        Toolbar toolbar = findViewById(R.id.clztoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Classroom");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()== android.R.id.home){
            clzroom.this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
