package com.sahandilshan.smartlearn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Regstr extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore fstore;

    private Button sign;
    private Button go;
    private com.google.android.material.textfield.TextInputLayout name;
    private com.google.android.material.textfield.TextInputLayout age;
    private com.google.android.material.textfield.TextInputLayout email;
    private com.google.android.material.textfield.TextInputLayout password;
    private RadioButton rbtnteacher;
    private RadioButton rbtnstudent;
    private String roll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regstr);

        mAuth = FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();

        sign=findViewById(R.id.sign);
        go=findViewById(R.id.go);
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
                    roll = "teacher";
                }
            }
        });
        rbtnstudent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(buttonView.isChecked()){
                    rbtnteacher.setChecked(false);
                    roll ="student";
                }
            }
        });


        //click already registered? button
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Regstr.this,Log_in.class);
                startActivity(intent);
            }


        });

        //click Go button
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();

            }
        });
    }

    //validate checked one of radiobtn
    private boolean validaterbtn(){
        if(!(rbtnstudent.isChecked() || rbtnteacher.isChecked())){
            rbtnstudent.setError("select your roll");
            rbtnteacher.setError("select your roll");
            return false;
        }else {
            rbtnstudent.setError(null);
            rbtnteacher.setError(null);
            return true;
        }
    }

    //validate name field
    private boolean validateName(){
        String val = name.getEditText().getText().toString();
        if(val.isEmpty()){
            name.setError("Field cannot be empty");
            return false;
        }else{
            name.setError(null);
            name.setErrorEnabled(false);
            return true;
        }
    }

    //validate age field
    private boolean validateAge(){
        String val = age.getEditText().getText().toString();
        if(val.isEmpty()){
            age.setError("Field cannot be empty");
            return false;
        }else if(val.length()>=3){
            age.setError("enter the real age");
            return false;
        }else{
            age.setError(null);
            age.setErrorEnabled(false);
            return true;
        }
    }

    //validate email field
    private boolean validateEmail(){
        String emailPattern ="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String val = email.getEditText().getText().toString();
        if(val.isEmpty()){
            email.setError("Field cannot be empty");
            return false;
        }else if(!val.matches(emailPattern)){
            email.setError("Enter valid email address");
            return false;
        } else{
            email.setError(null);
            email.setErrorEnabled(false);
            return true;
        }
    }

    //validate password field
    private boolean validatePassword(){
        String passwordPattern = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        String val = password.getEditText().getText().toString();
        if(val.isEmpty()){
            password.setError("Field cannot be empty");
            return false;
        }else if(!val.matches(passwordPattern)){
            password.setError("password is too weak");
            return false;
        }else{
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }



    //GO button method
    public void register(){

        if(! validaterbtn() | !validateName() | !validateAge() | !validateEmail() | !validatePassword()){
            return;
        }else{

            //save in firebase
            mAuth.createUserWithEmailAndPassword(email.getEditText().getText().toString().trim(),password.getEditText().getText().toString().trim())
                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(Regstr.this,"User registered successfully",Toast.LENGTH_LONG).show();
                            DocumentReference df = fstore.collection("users").document(user.getUid());
                            Map<String,Object> userInfo = new HashMap<>();
                            userInfo.put("name",name.getEditText().getText().toString().trim());
                            userInfo.put("age",age.getEditText().getText().toString().trim());
                            userInfo.put("roll",roll);
                            userInfo.put("email",email.getEditText().getText().toString().trim());

                            df.set(userInfo);

                            startActivity(new Intent(getApplicationContext(),Log_in.class));
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Regstr.this,"User registration unsuccessfull,try again",Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
