package com.sahandilshan.smartlearn;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ChatMain extends AppCompatActivity {

    Button sendBtn;
    EditText msgEt, senderEt;
    ListView listView;
    ArrayAdapter msgAdapter;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_chat_main);

        //getSupportActionBar().setTitle("Smart Learn Group Chat");

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("message");



        sendBtn = findViewById(R.id.sendBtn);
        senderEt = findViewById(R.id.senderEt);
        msgEt = findViewById(R.id.msgEt);
        listView = findViewById(R.id.listview);


        ArrayList<String> msgList = new ArrayList<String>();

        msgAdapter = new ArrayAdapter<String>(this, R.layout.chatlistitem, msgList);

        listView.setAdapter(msgAdapter);


        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sender = senderEt.getText().toString();
                String msg = msgEt.getText().toString();

               // msgAdapter.add(sender + ">-->>" + msg);
                myRef.push().setValue(sender + ">-->>" + msg);
                msgEt.setText("");

            }
        });
        loadMsg();
    }

private void loadMsg(){
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                msgAdapter.add(snapshot.getValue().toString());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
}


}