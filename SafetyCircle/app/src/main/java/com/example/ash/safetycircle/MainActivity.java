package com.example.ash.safetycircle;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;


import android.app.Activity;
import android.content.Context;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity

{
    private EditText name;
    private EditText address;
    private EditText contact;
    private Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report1);
    name = (EditText)findViewById(R.id.editTextName);
    address=(EditText)findViewById(R.id.editTextAddress);
    contact=(EditText) findViewById(R.id.editTextContact);
    btn=(Button)findViewById(R.id.buttonSubmit);

    btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final ParseUser user = ParseUser.getCurrentUser();

            ParseQuery<ParseObject> query = ParseQuery.getQuery("_User");
            ParseObject reportObj = new ParseObject("Report");
            String userName = name.getText().toString();
            String userAddress = address.getText().toString();
            String userContact = contact.getText().toString();

           reportObj.put("First_Name",userName);

            user.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if(e==null){
                        Toast.makeText(MainActivity.this, "Successfully Added", Toast.LENGTH_SHORT).show();
                    }else Toast.makeText(MainActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    });
    }


}