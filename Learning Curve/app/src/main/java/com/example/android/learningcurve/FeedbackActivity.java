package com.example.android.learningcurve;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FeedbackActivity extends AppCompatActivity {

    DatabaseReference databaseReference;

    Button mButton;
    EditText issue;
    EditText improve;
    RatingBar mRating;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        improve   = (EditText)findViewById(R.id.Improvement);
        issue   = (EditText)findViewById(R.id.issues);
        mRating = (RatingBar) findViewById(R.id.ratingBar);
        mButton= (Button) findViewById(R.id.submit_area);
        databaseReference = FirebaseDatabase.getInstance().getReference("admin/feedback");

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatedetails();
                Intent intent = new Intent (FeedbackActivity.this,teachersactivity.class);
                startActivity (intent);

            }
        });
    }

    private void updatedetails() {

        String is=issue.getText().toString().trim();
        String imp=improve.getText().toString().trim();

        float f = mRating.getRating();
        String id =databaseReference.push().getKey();
        Feedback feedBack= new Feedback(is,imp,f);
        databaseReference.child(id).setValue(feedBack);


    }

}
