package com.sahandilshan.smartlearn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import com.google.firebase.auth.FirebaseAuth;

public class reg extends AppCompatActivity {
    private FirebaseAuth mAuth;

    private Button sign;
    private com.google.android.material.textfield.TextInputLayout name;
    private com.google.android.material.textfield.TextInputLayout age;
    private com.google.android.material.textfield.TextInputLayout email;
    private com.google.android.material.textfield.TextInputLayout password;
    private RadioButton rbtnteacher;
    private RadioButton rbtnstudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        mAuth = FirebaseAuth.getInstance();

        sign=findViewById(R.id.sign);
        name=findViewById(R.id.name);
        age=findViewById(R.id.age);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        rbtnteacher=findViewById(R.id.rbtnteacher);
        rbtnstudent=findViewById(R.id.rbtnstudent);


        //check radio button only one at a time...

        rbtnteacher.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(buttonView.isChecked()){
                    rbtnstudent.setChecked(false);
                }
            }
        });
        rbtnstudent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(buttonView.isChecked()){
                    rbtnteacher.setChecked(false);
                }
            }
        });
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int id = v.getId();
                if (id == R.id.sign) {
                    startActivity(new Intent(reg.this, Login.class));
                } else if (id == R.id.go) {
                    register();
                }
            }

            private void register() {
                System.out.println("www");
            }
        });
    }
}
