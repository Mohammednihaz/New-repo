package com.example.www.mughalai_kitchan_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class cart extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference mdatabase = database.getReference("cart");
    FirebaseAuth mauth = FirebaseAuth.getInstance();
    FirebaseUser currentUser = mauth.getCurrentUser();
    DatabaseReference morder = database.getReference("orderPreConfirmation");
    DatabaseReference user = database.getReference("users");
    RecyclerView cartlistfood;
    TextView alltotal;
    Button order;
    public cartelement completeCart;

    EditText txtadd;

    public static boolean add = true;
    RecyclerView.LayoutManager layoutManager;
    int tot=0;
    FirebaseRecyclerAdapter<cartelement, cartlistholder> adapter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        alltotal = (TextView) findViewById(R.id.alltotal);
        order = (Button) findViewById(R.id.order);
        cartlistfood = (RecyclerView) findViewById(R.id.cartlistfood);
        cartlistfood.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        cartlistfood.setLayoutManager(layoutManager);
        adapter = new FirebaseRecyclerAdapter<cartelement, cartlistholder>(
                cartelement.class,
                R.layout.cart_item_element,
                cartlistholder.class, mdatabase.child(SharedResource.refId).child("dish")) {
            @Override
            protected void populateViewHolder(final cartlistholder viewHolder, final cartelement model, int position) {
                viewHolder.txtname.setText(model.getName());
                viewHolder.txttotal.setText("\u20B9" + String.valueOf(model.getTotal()));
                tot = tot + Integer.parseInt(String.valueOf(model.getTotal()));
                alltotal.setText("\u20B9" + tot);

                viewHolder.setItemonClickListener(new ItemonClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                   adapter.getRef(position).removeValue();

                   //int totless=model.getTotal();
                 //  int finaltotal=tot-totless;
                  // mdatabase.child(SharedResource.refId).child("dish").child(rm).removeValue();


                    }
                });

            }
        };

        cartlistfood.setAdapter(adapter);

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              if (tot==0)
              {
                  Toast.makeText(getApplicationContext(),"Item is not added to cart",Toast.LENGTH_SHORT).show();

              }
               else {
                  Intent i = new Intent(getApplicationContext(), confirmorder.class);
                  i.putExtra("total", String.valueOf(tot));
                  startActivity(i);
              }
            }


        });
    }
}
