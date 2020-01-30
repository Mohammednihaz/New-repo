package com.example.www.mughalai_kitchan_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class confirmorder extends AppCompatActivity implements View.OnClickListener {
    Button btnorder;

    EditText add;
    EditText phone;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference mdatabase = database.getReference("cart");
    DatabaseReference user = database.getReference("users");
    FirebaseAuth mauth=FirebaseAuth.getInstance();
    FirebaseUser currentUser=mauth.getCurrentUser();
    public cartelement completeCart;
    String grandrtotal;
    DatabaseReference morder=database.getReference("orderPreConfirmation");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmorder);
        btnorder=(Button)findViewById(R.id.cmorder);
        add=(EditText)findViewById(R.id.add);
        phone = (EditText) findViewById(R.id.pone);
          final String address;
                 add.setText(SharedResource.address);
                 phone.setText(SharedResource.phoneno);

        if (getIntent() != null) {
          grandrtotal= getIntent().getStringExtra("total");

        }
        btnorder.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        String  address=add.getText().toString().trim();
        String phne=phone.getText().toString().trim();
        user.child(SharedResource.refId).child("address").setValue(address);
        user.child(SharedResource.refId).child("phone").setValue(phne);


        final String key=morder.push().getKey();
        String key2=morder.push().getKey();
        morder.child(key).child("confirmation").setValue("0");
        morder.child(key).child("username").setValue(SharedResource.username);
        morder.child(key).child("total").setValue(grandrtotal);

        Query query = mdatabase.child(SharedResource.refId).child("dish");
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String keynew = dataSnapshot.getKey();
                completeCart = dataSnapshot.getValue(cartelement.class);
                morder.child(key).child("dish").child(keynew).setValue(completeCart);
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
        Toast.makeText(getApplicationContext(),"successfully ordered",Toast.LENGTH_SHORT).show();
        mdatabase.child(SharedResource.refId).removeValue();
        Intent i=new Intent(getApplicationContext(),orderstatus.class);
        startActivity(i);
    }
}
