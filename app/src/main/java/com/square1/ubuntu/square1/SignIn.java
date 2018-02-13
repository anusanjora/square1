package com.square1.ubuntu.square1;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.square1.ubuntu.square1.Model.User;

import static com.square1.ubuntu.square1.R.id.edtPassward;

public class SignIn extends AppCompatActivity {
    private FirebaseAnalytics mFirebaseAnalytics;
    EditText edtID,edtPassword;
    Button btnSignIn = findViewById(R.id.btnSignIn);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
    edtPassword = findViewById(edtPassward) ;
    edtID = findViewById(R.id.edtID);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("USER");
        btnSignIn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
            ProgressDialog xDialog = new ProgressDialog(SignIn.this);
            xDialog.setMessage("Please Waiting");
            xDialog.show();
            table_user.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    //Check user exist
                    //if(dataSnapshot.child(edtID.getText().toString()).exists());
                        //get user information
                        //mDialog.dismiss();
                        User user = dataSnapshot.child(edtID.getText().toString()).getValue(User.class);


                    assert user != null;
                    if(user.getPassward().equals(edtPassword.getText().toString()))
                    {
                        Toast.makeText(SignIn.this,"Sign In succssfull !",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(SignIn.this,"Sign In Failed !",Toast.LENGTH_SHORT).show();
                    }
//                    else
//                    {
//                        mDialog.dismiss();
//                        Toast.makeText(SignIn.this,"User doesn't exist.",Toast.LENGTH_SHORT
//                                .show());
//                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
        });
    }
}
