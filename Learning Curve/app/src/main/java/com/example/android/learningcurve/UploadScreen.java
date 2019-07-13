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

public class UploadScreen extends AppCompatActivity {

    private RadioGroup radioGroup;
    private TextView textView;
    private Button btn;
    public static int selectedId;

    private StorageReference StorageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_screen);

        StorageRef = FirebaseStorage.getInstance().getReference();
        btn=findViewById(R.id.upload);
        radioGroup=(RadioGroup)findViewById(R.id.files);
        textView=findViewById(R.id.tv);

        selectedId = radioGroup.getCheckedRadioButtonId();
        textView.setText(String.valueOf(selectedId));
        if(selectedId==-1){
            Toast.makeText(UploadScreen.this,"Nothing selected", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(UploadScreen.this,String.valueOf(selectedId), Toast.LENGTH_SHORT).show();
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri file = Uri.fromFile(new File("path/to/images/rivers.jpg"));
                StorageReference riversRef = StorageRef.child("images/rivers.jpg");

                riversRef.putFile(file)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                // Get a URL to the uploaded content
                                Task<Uri> downloadUrl = taskSnapshot.getStorage().getDownloadUrl();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                // Handle unsuccessful uploads
                                // ...
                            }
                        });
//                Intent i=new Intent(UploadScreen.this,demo.class);
//                startActivity(i);
                Toast.makeText(UploadScreen.this,"Upload done", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
