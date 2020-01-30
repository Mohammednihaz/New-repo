package com.example.www.mughalai_kitchan_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class menu extends SharedResource
        implements NavigationView.OnNavigationItemSelectedListener {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference mdatabase = database.getReference("category");
    RecyclerView mlistfood;
    FirebaseAuth mauht;
    UserInfo userInfo;
    TextView txtuser;
    private FirebaseDatabase mFireDB;
    private DatabaseReference mDbRef;
    FirebaseRecyclerAdapter<category,FoodViewholder> adapter;
    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mFireDB = FirebaseDatabase.getInstance();
        mDbRef = mFireDB.getReference().child("users");
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),cart.class);
                startActivity(i);


            }
        });
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        mlistfood=(RecyclerView)findViewById(R.id.listfood);
       mlistfood.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getApplicationContext(),2, LinearLayoutManager.VERTICAL,false);

        mlistfood.setLayoutManager(gridLayoutManager);
        adapter=new FirebaseRecyclerAdapter<category, FoodViewholder>(
                category.class,
                R.layout.menu_item,
                FoodViewholder.class,
                mdatabase
        ) {
            @Override
            protected void populateViewHolder(FoodViewholder viewHolder, category model, int position) {
                viewHolder.name.setText(model.getName());
                Picasso.with(getBaseContext()).load(model.getImage()).into(viewHolder.image);
                viewHolder.setItemonClickListener(new ItemonClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Intent list=new Intent(getApplicationContext(),foodlist.class);
                        list.putExtra("categoryid",adapter.getRef(position).getKey());

                        startActivity(list);


                    }
                });
            }
        };
        mlistfood.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment=null;
        if (id == R.id.nav_home) {
            // Handle the camera action
            setTitle("Home");
            fragment=new home();

        } else if (id == R.id.nav_order) {
            setTitle("Oder History");
            fragment=new orderhistory();
        } else if (id == R.id.nav_fav) {
            setTitle("Favorite");

        } else if (id == R.id.nav_feedback) {
            setTitle("Favorite");
            fragment=new feedback();
        } else if (id == R.id.nav_sinout) {
                mauht=FirebaseAuth.getInstance();
                mauht.signOut();
                 Intent i=new Intent(getApplicationContext(),MainActivity.class);
                 startActivity(i);
        } else if (id == R.id.nav_book) {
            setTitle("Table Booking");
            fragment=new tablebk(username,phoneno);


        }else if (id == R.id.nav_menu) {
            Intent i=new Intent(getApplicationContext(),menu.class);
            startActivity(i);
        }else if(id==R.id.orderstatus)
        {
            Intent i=new Intent(getApplicationContext(),orderstatus.class);
            startActivity(i);
        }


        if (fragment!=null)
        {
            FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.menu,fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
