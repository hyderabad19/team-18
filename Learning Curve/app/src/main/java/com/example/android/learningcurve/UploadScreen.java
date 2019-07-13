package com.example.android.learningcurve;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UploadScreen extends AppCompatActivity {

    private RadioGroup radiofiles;
    private Button uploadbtn;
    private RadioButton radiobtn;
    content c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_screen);

        uploadbtn=findViewById(R.id.upload);
        radiofiles=findViewById(R.id.files);

        uploadbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // get selected radio button from radioGroup
                int selectedId = radiofiles.getCheckedRadioButtonId();
                radiobtn=findViewById(selectedId);

                // find the radiobutton by returned id
//                radiobtn = (RadioButton) findViewById(selectedId);

                Toast.makeText(UploadScreen.this, radiobtn.getText(), Toast.LENGTH_SHORT).show();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("content");

                c=new content();
                c.setUrl("demo");
                c.setLikes(2);
                c.setComments("hi");
                //myRef.child("product" + (++i)).setValue(p);
                myRef.child(String.valueOf(radiobtn.getText())).setValue(c);


            }

        });
    }
}
