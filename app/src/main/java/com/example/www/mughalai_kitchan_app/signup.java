package com.example.www.mughalai_kitchan_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signup extends SharedResource {
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference mref=database.getReference("users");

    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        final TextInputEditText inputEmail, inputPassword,inputfname,inputlname,inputaddress,inputphone,txtconfirm;
        TextView heademail;

        Button btnSignIn, btnSignUp;

        final FirebaseAuth auth;


        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();
        final FirebaseUser user=auth.getCurrentUser();
        btnSignIn = (Button) findViewById(R.id.sign_in_button);
        btnSignUp = (Button) findViewById(R.id.sign_up_button);
        inputEmail = (TextInputEditText) findViewById(R.id.email);
        inputPassword = (TextInputEditText) findViewById(R.id.password);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        inputfname=(TextInputEditText) findViewById(R.id.txtfirstname);
        inputlname=(TextInputEditText) findViewById(R.id.txtfirstname);
        inputaddress=(TextInputEditText) findViewById(R.id.useraddress);

        inputphone=(TextInputEditText) findViewById(R.id.userphone);
        txtconfirm=(TextInputEditText) findViewById(R.id.confirmpass);

        //heademail.setText(SharedResource.username);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();
                final String firstname=inputfname.getText().toString().trim();
                final String lastname=inputlname.getText().toString().trim();
                String confirm=txtconfirm.getText().toString().trim();
                final String useraddress=inputaddress.getText().toString().trim();
                final String userphone=inputphone.getText().toString().trim();

                if (TextUtils.isEmpty(firstname))
                {
                    Toast.makeText(getApplicationContext(), "Enter the First Name !", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(lastname))
                {
                    Toast.makeText(getApplicationContext(), "Enter the Last Name!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(useraddress))
                {
                    Toast.makeText(getApplicationContext(), "Enter the your Address !", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(userphone))
                {
                    Toast.makeText(getApplicationContext(), "Enter the your Phone Number  !", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!password.equals(confirm))
                {
                    Toast.makeText(getApplicationContext(), "Enter the password not equals!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(confirm))
                {
                    Toast.makeText(getApplicationContext(), "Enter the Confirm password !", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }



                progressBar.setVisibility(View.VISIBLE);

                //create user
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(signup.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                progressBar.setVisibility(View.GONE);

                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Toast.makeText(signup.this, "You already created this email account!" ,
                                           Toast.LENGTH_SHORT).show();
                                    finish();
                                } else {
                                    userdata(firstname,lastname,useraddress,userphone,email);
                                    startActivity(new Intent(signup.this, MainActivity.class));
                                    finish();
                                }
                            }
                        });

            }

            private void userdata(String firstname, String lastname, String useraddress, String userphone, String email) {
                String key=mref.push().getKey();

                mref.child(key).child("firstName").setValue(firstname);
                mref.child(key).child("lastname").setValue(lastname);
                mref.child(key).child("email").setValue(email);
                mref.child(key).child("address").setValue(useraddress);
                mref.child(key).child("phone").setValue(userphone);
                mref.child(key).child("refID").setValue(key);
                SharedResource.refId=key;
                SharedResource.address=useraddress;
                 SharedResource.phoneno=userphone;
                SharedResource.username = email;

                return;

            }

        });

    }



    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }
}
