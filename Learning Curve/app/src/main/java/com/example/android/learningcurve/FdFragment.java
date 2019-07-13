package com.example.android.learningcurve;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FdFragment extends Fragment {
    DatabaseReference databaseReference;

    Button mButton;
    EditText issue;
    EditText improve;
    RatingBar mRating;

    public FdFragment() {
        // Required empty public constructor
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container,@Nullable Bundle savedInstanceState){

        return inflater.inflate (R.layout.fragment_fd,null);
    }

    private void updatedetails() {

        String is=issue.getText().toString().trim();
        String imp=improve.getText().toString().trim();

        float f = mRating.getRating();
        String id =databaseReference.push().getKey();
        Feedback feedBack= new Feedback(is,imp,f);
        databaseReference.child(id).setValue(feedBack);

    }

    @Override
    public void onViewCreated(View view,@Nullable final Bundle savedInstanceState){
        super.onViewCreated (view,savedInstanceState );



        view.findViewById (R.id.submit_area).setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {

                improve   = (EditText)getView ().findViewById(R.id.Improvement);
                issue   = (EditText)getView ().findViewById(R.id.issues);
                mRating = (RatingBar) getView ().findViewById(R.id.ratingBar);
                mButton= (Button) getView ().findViewById(R.id.submit_area);
                databaseReference = FirebaseDatabase.getInstance().getReference("admin").child ("feedback");

                mButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        updatedetails();

                    }
                });
                Intent intent=new Intent (getContext (),TeachersActivity.class);
                startActivity (intent);
            }
        });
    }
}
