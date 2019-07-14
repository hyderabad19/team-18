package com.example.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    DatabaseReference databaseReference;

    Button mButton;
    EditText issue;
    EditText improve;
    RatingBar mRating;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        improve   = (EditText)findViewById(R.id.Improvement);
        issue   = (EditText)findViewById(R.id.issues);
        mRating = (RatingBar) findViewById(R.id.ratingBar);
        mButton= (Button) findViewById(R.id.submit_area);
        databaseReference = FirebaseDatabase.getInstance().getReference("feedback");

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatedetails();

            }
        });
    }

    private void updatedetails() {

        String is=issue.getText().toString().trim();
        String imp=improve.getText().toString().trim();

        float f = mRating.getRating();
        String id =databaseReference.push().getKey();
        FeedBack feedBack= new FeedBack(is,imp,f);
        databaseReference.child(id).setValue(feedBack);


    }

    }
