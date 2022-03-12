package com.sahandilshan.smartlearn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;


public class Client_SplashActivity extends AppCompatActivity {

    private TextView appName;

    public static List<Client_CategoryModel> catList = new ArrayList<>();
    public static int selected_cat_index = 0;
    private FirebaseFirestore firestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client__splash);


        appName = findViewById(R.id.appName);

        Typeface typeface = ResourcesCompat.getFont(this,R.font.fft);
        appName.setTypeface(typeface);

        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.textanim);
        appName.setAnimation(anim);

        firestore = FirebaseFirestore.getInstance();

        new Thread() {
            public void run() {
                // sleep(3000);

                loadData();


            }
        }.start();



    }

    private void loadData()
    {
        catList.clear();

        firestore.collection("QUIZ1").document("Categories")
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                if(task.isSuccessful())
                {
                    DocumentSnapshot doc = task.getResult();

                    if(doc.exists())
                    {
                        long count = (long)doc.get("COUNT");

                        for(int i=1; i <= count; i++)
                        {
                            String catName = doc.getString("CAT" + String.valueOf(i) + "_NAME");
                            String catID = doc.getString("CAT" + String.valueOf(i) + "_ID");

                            catList.add(new Client_CategoryModel(catID,catName));
                        }


                        Intent intent = new Intent(Client_SplashActivity.this,Client_MAinActivity.class);
                        startActivity(intent);
                        Client_SplashActivity.this.finish();

                    }
                    else
                    {
                        Toast.makeText(Client_SplashActivity.this,"No Category Document Exists!",Toast.LENGTH_SHORT).show();
                        finish();
                    }

                }
                else
                {

                    Toast.makeText(Client_SplashActivity.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
