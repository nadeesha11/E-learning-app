package com.sahandilshan.smartlearn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import static java.lang.Thread.sleep;

public class welcome_page extends AppCompatActivity {
    private TextView welcometxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        welcometxt = findViewById(R.id.welcometxt);
        Typeface typeface = ResourcesCompat.getFont(this, R.font.fft);
        welcometxt.setTypeface(typeface);

        Animation anim = AnimationUtils.loadAnimation(this,R.anim.textanim);
        welcometxt.setAnimation(anim);


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intent=new Intent(welcome_page.this, Log_in.class);
                startActivity(intent);
            }
        }).start();
    }
}
