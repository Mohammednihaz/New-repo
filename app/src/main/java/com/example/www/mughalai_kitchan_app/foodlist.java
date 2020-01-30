package com.example.www.mughalai_kitchan_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class foodlist extends AppCompatActivity {
    String cateoryid = "";
    FirebaseDatabase database;
    DatabaseReference mref;
    RecyclerView recyclerView;
    FirebaseRecyclerAdapter<food,Foodlistholder > adapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodlist);

        database = FirebaseDatabase.getInstance();
        mref = database.getReference("dishlist");
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
       if(getIntent()!=null) {
           cateoryid = getIntent().getStringExtra("categoryid");
                  }
        adapter=new FirebaseRecyclerAdapter<food, Foodlistholder>(food.class,R.layout.food_item,Foodlistholder.class,
                mref.orderByChild("menuId").equalTo(cateoryid)) {

            @Override
            protected void populateViewHolder(Foodlistholder viewHolder, food model, int position) {
                if(model.getAvailability().equals("true")) {
                    viewHolder.txtname.setText(model.getName());
                    Picasso.with(getBaseContext()).load(model.getImage()).into(viewHolder.imagefood);

                    viewHolder.setItemonClickListener(new ItemonClickListener() {
                        @Override
                        public void onClick(View view, int position, boolean isLongClick) {

                            Intent iteminfo = new Intent(getApplicationContext(), fooddetail.class);
                            iteminfo.putExtra("foodid", adapter.getRef(position).getKey());
                            startActivity(iteminfo);

                        }
                    });
                }else {
                               adapter.getRef(position).removeValue();  }
            }
        };
        recyclerView.setAdapter(adapter);
}
}
