package com.example.android.learningcurve;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminLogin extends AppCompatActivity {

    DatabaseReference databaseReference;
    EditText email, password;
    Button login;
    admin a;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        login = findViewById(R.id.buttonLogin);
        final EditText email = findViewById(R.id.editTextEmail);
        final EditText password = findViewById(R.id.editTextPassword);

        databaseReference = FirebaseDatabase.getInstance().getReference("admin");

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String aemail = email.getText().toString().trim();
                final String pass = password.getText().toString().trim();
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        mAuth = FirebaseAuth.getInstance();
                        mAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Intent intent2 = new Intent(AdminLogin.this, uploadadmin.class);
                                    email.setText("");
                                    password.setText("");
                                    startActivity(intent2);
                                    Toast.makeText(AdminLogin.this, "Admin logged in", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(AdminLogin.this, "not admin", Toast.LENGTH_LONG).show();
                                }
                            }
                        });

//                        Log.d(TAG, "Value is: " + value);
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
//                        Log.w(TAG, "Failed to read value.", error.toException());
                    }
                });

            }
        });
    }

}