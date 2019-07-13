package com.example.starrating;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.support.v7.app.AlertDialog;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    DatabaseReference rootRef;
    EditText et;
    RatingBar MYRATE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         MYRATE=(RatingBar)findViewById(R.id.MyRatings);
         et=(EditText) findViewById(R.id.et);
        Button b=(Button)findViewById(R.id.b1);
        rootRef= FirebaseDatabase.getInstance().getReference("StarRating");
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    updatetodatabase();
            }
        });


    }
    private void updatetodatabase(){
        String feedback=et.getText().toString().trim();
        float f=MYRATE.getRating();
        String id=rootRef.push().getKey();
        Appfeedback afb=new Appfeedback(f,feedback);
        rootRef.child(id).setValue(afb);
    }
}
