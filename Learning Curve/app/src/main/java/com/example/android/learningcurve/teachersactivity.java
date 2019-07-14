package com.example.android.learningcurve;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class teachersactivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teachersactivity);

        Button feedback = (Button) findViewById(R.id.feedback);
        Button viewcontent = (Button) findViewById(R.id.viewcontent);
        Button profile = (Button) findViewById(R.id.profile);
        Button logout = (Button) findViewById(R.id.logout);

        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(teachersactivity.this, FeedbackActivity.class);

                startActivity(intent);
            }
        });
        viewcontent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(teachersactivity.this, contentlistview.class);

                startActivity(intent);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(teachersactivity.this, profile.class);

                startActivity(intent);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(teachersactivity.this, StartActivity.class);

                startActivity(intent);
            }
        });

    }
}
