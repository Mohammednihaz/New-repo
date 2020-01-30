package com.example.www.mughalai_kitchan_app;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class fooddetail extends  SharedResource {
    String foodid = "",fdname;
    int fdprice;
    static String fd="";
    FirebaseDatabase database;
    DatabaseReference mref,mdb;
    DatabaseReference mcart;
    DatabaseReference mfav;
    TextView txtn,txtp,txtd;
    int fla,fl;
     ImageView image;
     Button btnfav;
     int total;
    String key="2392398";
     ElegantNumberButton qty;

    FloatingActionButton btncart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fooddetail);
        txtn = (TextView) findViewById(R.id.finalname);
        txtd = (TextView) findViewById(R.id.finaldesc);
        txtp = (TextView) findViewById(R.id.finalprice);
        image = (ImageView) findViewById(R.id.finalimage);


        database = FirebaseDatabase.getInstance();
        mref = database.getReference("dishlist");
        mcart=database.getReference("cart");
        mfav=database.getReference("favorite");
      btncart=(FloatingActionButton) findViewById(R.id.addtocart);
      btnfav=(Button)findViewById(R.id.addtofav);
      qty=(ElegantNumberButton)findViewById(R.id.qty);

        if (getIntent() != null) {
            foodid = getIntent().getStringExtra("foodid");

        }
        if (foodid != null) {
            mref.child(foodid).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    fooditem fditem = dataSnapshot.getValue(fooditem.class);
                  txtn.setText(fditem.getName());
                  txtd.setText(fditem.getDescription());
                  txtp.setText("\u20B9"+String.valueOf(fditem.getPrice()));
                                      Picasso.with(getBaseContext()).load(fditem.getImage()).into(image);
                      fdname=fditem.getName();

                     fdprice=Integer.parseInt(String.valueOf(fditem.getPrice()));

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        }
        btncart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fla = 0;
                mdb = database.getReference().child("cart");
               /* Query query=mdb.child(SharedResource.refId).child("dish").orderByChild("name").equalTo(fdname);
                query.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                        String  keyid=dataSnapshot.getKey();
                        mcart.child(SharedResource.refId).child("dish").child(keyid).child("qty").setValue(qty.getNumber());
                        int total = fdprice * Integer.parseInt(qty.getNumber());
                        mcart.child(SharedResource.refId).child("dish").child(keyid).child("total").setValue(total);
                        fla = 1;



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
                           */
                if(fla==0) {
                    String key2 = mcart.push().getKey();

                    mcart.child(SharedResource.refId).child("username").setValue(SharedResource.username);
                    mcart.child(SharedResource.refId).child("dish").child(key2).child("name").setValue(fdname);
                    mcart.child(SharedResource.refId).child("dish").child(key2).child("qty").setValue(qty.getNumber());
                    int total = fdprice * Integer.parseInt(qty.getNumber());
                    mcart.child(SharedResource.refId).child("dish").child(key2).child("total").setValue(total);
                    Toast.makeText(getApplicationContext(), "successfully item add to cart", Toast.LENGTH_SHORT).show();
                }


            }
        });
        btnfav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fl = 0;
                Query query=mfav.child(SharedResource.refId).child("dish").orderByChild("name").equalTo(fdname);
                query.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        String  keyid=dataSnapshot.getKey();
                        mfav.child(SharedResource.refId).child("dish").child(keyid).child("name").setValue(fdname);
                        fl = 1;
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

                if(fl==0){
                    String key3 = mfav.push().getKey();
                    mfav.child(SharedResource.refId).child("dish").child(key3).child("name").setValue(fdname);
                }

            }
        });
    }


}
