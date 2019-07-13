package com.example.android.learningcurve;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    ProgressBar progressBar;
    EditText editTextEmail,editTextPassword,confirmPassword;
    EditText teacherid,institute,Name;
    DatabaseReference databaseReference;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        databaseReference= FirebaseDatabase.getInstance ().getReference ("teachers");
        confirmPassword = findViewById (R.id.confirmPassword);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword=findViewById(R.id.editTextPassword);
        Name=findViewById (R.id.name);
        teacherid=findViewById(R.id.TeacherId);
        institute=findViewById(R.id.institute);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);

        mAuth = FirebaseAuth.getInstance();
        findViewById(R.id.buttonSignUp).setOnClickListener(this);
        findViewById(R.id.textViewLogin).setOnClickListener(this);
    }

    private void registerUser(){
        String email =editTextEmail.getText().toString().trim();
        String password =editTextPassword.getText().toString().trim();
        String confirmpass=confirmPassword.getText ().toString ().trim ();
        String inst = institute.getText ().toString ().trim ();
        String tname=Name.getText ().toString ().trim ();

        if(email.isEmpty()){
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }

        if(!password.equals (confirmpass)){
            confirmPassword.setError ("Password does not match");
            confirmPassword.requestFocus ();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please enter avalid Email");
            editTextEmail.requestFocus();
            return;
        }

        if(password.length()<8){
            editTextPassword.setError("Mininmum length of password should be 8");
            editTextPassword.requestFocus();
            return;
        }

       if(!tname.isEmpty()&& !email.isEmpty () && !password.isEmpty () && !inst.isEmpty () )
        {
            String id = databaseReference.push ().getKey ();
            SignInDetails signInDetails=new SignInDetails (tname,password,email,inst);
            databaseReference.child (id).setValue (signInDetails);
        }
        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Registration successful", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(SignUpActivity.this,MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }else {
                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                        Toast.makeText(getApplicationContext(), "You are already registered", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonSignUp:
                registerUser();
                break;
            case R.id.textViewLogin:
                startActivity(new Intent(this,MainActivity.class));
                break;
        }
    }
}
