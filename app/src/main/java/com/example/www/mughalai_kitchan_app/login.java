package com.example.www.mughalai_kitchan_app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class login extends SharedResource implements View.OnClickListener {
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseDatabase mFireDB;
    private DatabaseReference mDbRef;
    UserInfo userInfo;
    uniqueid unique;
    private Button btn,btnResetPassword;
    EditText email1,pass;

    ProgressDialog progressDialog;
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mFireDB = FirebaseDatabase.getInstance();
        mDbRef = mFireDB.getReference().child("users");

        email1=(EditText)findViewById(R.id.editTextemail);
        pass=(EditText)findViewById(R.id.editTextpassword);
        btn=(Button)findViewById(R.id.userlogin);
            btnResetPassword = (Button) findViewById(R.id.btn_reset_password);

            btn.setOnClickListener(this);


            btnResetPassword.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(), ResetPasswordActivity.class));
                }
            });

        }


    public void userloin() {
        final String email=email1.getText().toString().trim();
        String password=pass.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {

            Toast.makeText(login.this, "Enter the  Email ID",
                    Toast.LENGTH_SHORT).show();
            return;

        }
        else if(TextUtils.isEmpty(password))
        {

            Toast.makeText(login.this, "Enter the Password",
                    Toast.LENGTH_SHORT).show();
            return;
        }
          progressDialog=new ProgressDialog(this);
          progressDialog.setMessage("Please wait.....");
          progressDialog.show();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            progressDialog.dismiss();
                            SharedResource.username = email;

                            Query query = mDbRef.orderByChild("email").equalTo(SharedResource.username);

                            query.addChildEventListener(new ChildEventListener() {
                                @Override
                                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                    userInfo = dataSnapshot.getValue(UserInfo.class);
                                    SharedResource.refId = userInfo.getId();
                                    SharedResource.phoneno=userInfo.getPhone();
                                    SharedResource.address=userInfo.getAddress();
                                }

                                @Override
                                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                                }

                                @Override
                                public void onChildRemoved(DataSnapshot dataSnapshot) {

                                }

                                @Override
                                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });




                            menu();
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);

                        } else {
                            // If sign in fails, display a message to the useer

                            Toast.makeText(login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                            finish();
                        }

                        // ...
                    }
                });

    }

    private void menu() {
        Intent i=new Intent(getApplicationContext(),menu.class);
        startActivity(i);
        Toast.makeText(login.this, "your successful login",
                Toast.LENGTH_SHORT).show();
        finish();

    }

    private void updateUI(FirebaseUser user) {

        if (user != null) {

           user.sendEmailVerification();

        }

    }

    @Override
    public void onClick(View view) {
        userloin();

    }
}
