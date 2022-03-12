package com.sahandilshan.smartlearn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;



public class Log_in extends AppCompatActivity {


    private FirebaseAuth mAuth;
    private FirebaseFirestore fstore;

    private Button sign;
    private Button gobtn;
    private com.google.android.material.textfield.TextInputLayout email;
    private com.google.android.material.textfield.TextInputLayout password;
    private Button fpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        mAuth = FirebaseAuth.getInstance();
        fstore= FirebaseFirestore.getInstance();

        sign = findViewById(R.id.sign);
        gobtn=findViewById(R.id.gobtn);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        fpass=findViewById(R.id.fpass);

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Log_in.this,Regstr.class);
                startActivity(intent);
            }
        });

        gobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
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

    public void login(){
        if(! validateEmail() | ! validatePassword()){
            return;

        }else{
            mAuth.signInWithEmailAndPassword(email.getEditText().getText().toString().trim(),password.getEditText().getText().toString().trim())
                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            Toast.makeText(Log_in.this,"Loggedin successfully",Toast.LENGTH_LONG).show();
                            checkroll(authResult.getUser().getUid());
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Log_in.this,"Loggin failed",Toast.LENGTH_LONG).show();
                }

            });

        }

    }

    private void checkroll(String uid) {
        DocumentReference df = fstore.collection("users").document(uid);

        //get data from firebase
        df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Log.d("TAG", "onSuccess: " + documentSnapshot.getData());

                //set roll of user to global variable userRoll
                global global= (global)getApplicationContext();

                String tmp=documentSnapshot.getString("roll");
                global.setUserRoll(tmp);


                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    finish();


            }
        });

    }
}
