package com.square1.ubuntu.square1;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.square1.ubuntu.square1.Model.User;

public class SignIn extends AppCompatActivity {
    EditText edtID,edtPassward;
    Button btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
    edtPassward = (MaterialEditText)findViewById(R.id.edtPassward) ;
    edtID = (MaterialEditText)findViewById(R.id.edtID);
    btnSignIn = (Button)findViewById(R.id.btnSignIn);

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
                    if(dataSnapshot.child(edtID.getText().toString()).exists());
                    //get user information
                    //mDialog.dismiss();
                    User user=dataSnapshot.child(edtID.getText().toString()).getValue(User.class);

                    if(user.getPassward().equals(edtPassward.getText().toString()))
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
