package com.example.www.mughalai_kitchan_app;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class feedback extends Fragment  {
    int lastno;
     public Button btnsnd;
     public EditText txtfd;
     View view;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser users = mAuth.getCurrentUser();
   Spinner sp;
    String select;
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference mref=database.getReference("feedback");






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.feedback, container, false);
          sp=(Spinner)view.findViewById(R.id.rate);
        btnsnd= (Button) view.findViewById(R.id.btnsend);
        txtfd=(EditText)view.findViewById(R.id.txtfeedback);
        String[] rating={"1.0","2.0","3.0","4.0","5.0"};
        ArrayAdapter rate=new ArrayAdapter(getContext(),R.layout.support_simple_spinner_dropdown_item,rating);
        sp.setAdapter(rate);
        select=sp.getSelectedItem().toString();


        btnsnd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String key=mref.push().getKey();
                    String name=users.getEmail().toString().trim();
                    String fed=txtfd.getText().toString().trim();
                    mref.child(key).child("message").setValue(fed);
                    mref.child(key).child("name").setValue(name);
                    mref.child(key).child("rating").setValue(select);
                    Toast.makeText(getContext(), "sucessfull send your feedback", Toast.LENGTH_SHORT).show();

                }
            });



                return view;
    }


}
