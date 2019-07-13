package com.example.android.learningcurve;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.android.learningcurve.UploadScreen;


public class demo extends AppCompatActivity {

    private TextView textview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        textview=findViewById(R.id.tv);
        textview.setText(String.valueOf(UploadScreen.selectedId));
    }
}
