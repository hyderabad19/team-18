package com.example.android.learningcurve;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class profile extends AppCompatActivity {

    String pwd;
    FirebaseDatabase database;
    DatabaseReference myref;
    TextView tvname;
    TextView tvemail;
    EditText pass;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        pass = findViewById(R.id.password);
        tvemail = findViewById(R.id.email);
        button = findViewById(R.id.button);
        pwd = pass.getText().toString();
        tvemail.setText(MainActivity.email);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                updateData(pwd1);
                database = FirebaseDatabase.getInstance();
                myref = database.getReference();
                myref.child("teachers").child(MainActivity.email).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        dataSnapshot.getRef().child("password").setValue(pwd);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.d("User", databaseError.getMessage());
                    }
                });
            }
        });
    }



}