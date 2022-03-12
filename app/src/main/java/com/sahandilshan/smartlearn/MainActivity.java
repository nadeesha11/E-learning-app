package com.sahandilshan.smartlearn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.Typeface;
import android.icu.text.CaseMap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btnclzroom;
    private Button btncht;
    private Button btnvid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnclzroom=findViewById(R.id.btnclassroom);
        btncht=findViewById(R.id.btnchat);
        btnvid=findViewById(R.id.btnlecture);

        btnclzroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, clzroom.class);//classroom materials
                startActivity(intent);
            }
        });
        btncht.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChatMain.class);//chat area
                startActivity(intent);
            }
        });
        btnvid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,JitsiVideo.class);//video conferencing with react native jitsi
                startActivity(intent);
            }
        });
    }
}
