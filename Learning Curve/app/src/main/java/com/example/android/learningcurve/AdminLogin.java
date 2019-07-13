package com.example.android.learningcurve;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.learningcurve.ui.login.admin;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminLogin extends AppCompatActivity {

    DatabaseReference databaseReference;
    EditText email,password;
    Button login;
    admin a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_admin_login);
        login=findViewById (R.id.buttonLogin);
        final EditText email=findViewById (R.id.editTextEmail);
        final EditText password=findViewById(R.id.editTextPassword);

        databaseReference= FirebaseDatabase.getInstance ().getReference ("admin");

        login.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                final String aemail=email.getText ().toString ().trim ();
                final String pass=password.getText ().toString ().trim ();
                databaseReference.addValueEventListener(new ValueEventListener () {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        if(aemail.equals (String.valueOf(dataSnapshot.child("email").getValue ())) && pass.equals (String.valueOf(dataSnapshot.child("password").getValue ())))

                            Toast.makeText (AdminLogin.this,"ADmin logged in",Toast.LENGTH_SHORT).show ();

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
