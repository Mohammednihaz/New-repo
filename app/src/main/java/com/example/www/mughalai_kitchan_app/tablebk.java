package com.example.www.mughalai_kitchan_app;


import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class tablebk extends Fragment {
  Button btnrequest,date,start,end;
     FirebaseAuth maut=FirebaseAuth.getInstance();
     FirebaseUser user=maut.getCurrentUser();
    public tablebk() {
    }

    FirebaseDatabase database;
    DatabaseReference mref;
  View view;
  String name,phone,select;
    Button btnDatePicker;
    EditText txtstart, txtend,txtDate;
    private int mYear, mMonth, mDay, mHour, mMinute;
    Spinner sp;



    @SuppressLint("ValidFragment")
    public tablebk(String username, String phoneno) {
            name=username;
            phone=phoneno;
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view=inflater.inflate(R.layout.tablebk, container, false);
        database = FirebaseDatabase.getInstance();
        mref = database.getReference("tablebooking");
        btnrequest=(Button)view.findViewById(R.id.subtable);
        btnDatePicker=(Button)view.findViewById(R.id.datepicker);
        txtDate=(EditText)view.findViewById(R.id.txtdate);
        txtstart=(EditText)view.findViewById(R.id.txtstart);
        txtend=(EditText)view.findViewById(R.id.txtend);
             start=(Button)view.findViewById(R.id.btnstart);
        end=(Button)view.findViewById(R.id.endbtn);
        sp=(Spinner)view.findViewById(R.id.sptable);
        date=(Button)view.findViewById(R.id.datepicker);
        String[] table={"Table 1","Table 2","Table 3","Table 4","Table 5","Table 6",};
        ArrayAdapter  tno=new ArrayAdapter(getContext(),R.layout.support_simple_spinner_dropdown_item,table);
        sp.setAdapter(tno);
        select=sp.getSelectedItem().toString();

                start.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Calendar c = Calendar.getInstance();
                        mHour = c.get(Calendar.HOUR_OF_DAY);
                        mMinute = c.get(Calendar.MINUTE);

                        // Launch Time Picker Dialog
                        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),
                                new TimePickerDialog.OnTimeSetListener() {

                                    @Override
                                    public void onTimeSet(TimePicker view, int hourOfDay,
                                                          int minute) {

                                        txtstart.setText(hourOfDay + ":" + minute);
                                    }
                                }, mHour, mMinute, false);
                        timePickerDialog.show();

                    }
                });
                end.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Calendar c = Calendar.getInstance();
                        mHour = c.get(Calendar.HOUR_OF_DAY);
                        mMinute = c.get(Calendar.MINUTE);

                        // Launch Time Picker Dialog
                        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),
                                new TimePickerDialog.OnTimeSetListener() {

                                    @Override
                                    public void onTimeSet(TimePicker view, int hourOfDay,
                                                          int minute) {

                                        txtend.setText(hourOfDay + ":" + minute);
                                    }
                                }, mHour, mMinute, false);
                        timePickerDialog.show();

                    }
                });
                btnDatePicker.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Calendar c = Calendar.getInstance();
                        mYear = c.get(Calendar.YEAR);
                        mMonth = c.get(Calendar.MONTH);
                        mDay = c.get(Calendar.DAY_OF_MONTH);


                        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                                new DatePickerDialog.OnDateSetListener() {

                                    @Override
                                    public void onDateSet(DatePicker view, int year,
                                                          int monthOfYear, int dayOfMonth) {

                                        txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                                    }
                                }, mYear, mMonth, mDay);
                        datePickerDialog.show();
                    }

                });
            btnrequest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String key=mref.push().getKey();
                    mref.child(key).child("name").setValue(SharedResource.username);
                    mref.child(key).child("phoneno").setValue(phone);
                    mref.child(key).child("tableno").setValue(select);
                    mref.child(key).child("date").setValue(txtDate.getText().toString().trim());
                    mref.child(key).child("startevent").setValue(txtstart.getText().toString().trim());
                    mref.child(key).child("endevent").setValue(txtend.getText().toString().trim());


                    Toast.makeText(getContext(),"Successssfully TableBooked",Toast.LENGTH_SHORT).show();
                }
            });

                        return  view;
    }



 }


