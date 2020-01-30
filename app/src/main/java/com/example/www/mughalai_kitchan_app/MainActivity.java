package com.example.www.mughalai_kitchan_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class MainActivity extends SharedResource  {
    private FirebaseAuth mAuth;
    FirebaseUser users;
    TextView txtuser;
        private FirebaseDatabase mFireDB;
    private DatabaseReference mDbRef;
    UserInfo userInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        users = mAuth.getCurrentUser();
        mFireDB = FirebaseDatabase.getInstance();
        mDbRef = mFireDB.getReference().child("users");
        txtuser=(TextView)findViewById(R.id.txtemail);

        if(users!=null)
        {

            call();

            Intent i = new Intent(getApplicationContext(),menu.class);
            startActivity(i);

        }
    }
    public void signin(View view) {

        Intent i = new Intent(getApplicationContext(), login.class);
        startActivity(i);


    }

    public void signup(View view) {
        Intent i = new Intent(getApplicationContext(), signup.class);
        startActivity(i);
    }



    void  call(){
        if(SharedResource.username!=null) {
            SharedResource.username = users.getEmail();


            Toast.makeText(getApplicationContext(), "" + SharedResource.username, Toast.LENGTH_SHORT).show();
            Query query = mDbRef.orderByChild("email").equalTo(SharedResource.username);

            query.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    userInfo = dataSnapshot.getValue(UserInfo.class);
                    SharedResource.address=userInfo.getAddress();
                    SharedResource.refId = userInfo.getId();
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
        }
        else{
            SharedResource.username = users.getEmail();
            call();
        }
        return;
    }
}
