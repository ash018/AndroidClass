package com.example.chapter2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private TextView capital,glT,sllT;
    private ToggleButton tgB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Spinner element
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        button = (Button) findViewById(R.id.btn);
        capital = (TextView) findViewById(R.id.capital);
        glT = (TextView) findViewById(R.id.glT);
        sllT = (TextView) findViewById(R.id.slT);
        tgB = (ToggleButton) findViewById(R.id.toggle_button);

        //spinner.setOnItemClickListener(AdapterView.OnItemClickListener);

        List<String> countries = new ArrayList<String>();
        countries.add("Bangladesh");
        countries.add("India");
        countries.add("Pakistan");
        countries.add("Srilanka");
        countries.add("Nepal");
        countries.add("Bhutan");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, countries);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String country = (String) spinner.getSelectedItem();
                String capitalStr = new String();
                Log.d("country",country);
                switch (country){
                    case "Bangladesh":
                        //capital.setText("Dhaka");
                        capitalStr="Dhaka";
                        break;
                    case "India":
                        //capital.setText("New Delhi");
                        capitalStr="New Delhi";
                        break;
                    case "Pakistan":
                        //capital.setText("Islamabad");
                        capitalStr="Islamabad";
                        break;
                    case "Srilanka":
                        //capital.setText("Colombo");
                        capitalStr="Colombo";
                        break;
                    case "Nepal":
                        //capital.setText("Kathmundu");
                        capitalStr="Kathmundu";
                        break;
                    case "Bhutan":
                        //capital.setText("Thimpu");
                        capitalStr="Thimpu";
                        break;
                    default:
                        capital.setText("Not Found");

                }

                //Intent myintent=new Intent(MainActivity.this, Main2Activity.class).putExtra("capital", capitalStr);
                Intent myintent=new Intent(Intent.ACTION_SEND);
                myintent.setType("text/plain");
                myintent.putExtra(Intent.EXTRA_TEXT, capitalStr);

                startActivity(myintent);

            }
        });
    }



    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    public void onToggleButtonClicked(View view) {
        // Get the state of the toggle button.
        boolean on = ((ToggleButton) view).isChecked();
        if (on) {
            // On
            glT.setText("Grid Layout Toggle Button On");

        } else {
            // Off
            glT.setText("Grid Layout Toggle Button off");
        }
    }

    public void onCheckboxClicked(View view) {
        // Has the checkbox that was clicked been checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Retrieve which checkbox was clicked
        switch(view.getId()) {
            case R.id.checkbox_milk:
                if (checked) {
                    // Milky coffee
                    sllT.setText("Milk Selected");
                }
                else {
                    // Black as the midnight sky on a moonless night
                    sllT.setText("Milk Unselected");
                }
                break;
            case R.id.checkbox_sugar:
                if (checked) {
                    // Sweet
                    sllT.setText("Sugar Selected");
                }
                 else{
                    sllT.setText("Sugar UnSelected");
                 }
                // Keep it bitter
                break;
        }
    }

    public void onRadioButtonClicked(View view) {
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        int id = radioGroup.getCheckedRadioButtonId();
        switch(id) {
            case R.id.radio_cavemen:
                // Cavemen win
                sllT.setText("Caveman Clicked");
                break;
            case R.id.radio_astronauts:
                // Astronauts win
                sllT.setText("Astronauts Clicked");
                break;
        }
    }

    public void onSwitchClicked(View view) {
        // Is the switch on?
        boolean on = ((Switch) view).isChecked();

        if (on) {
            // On
            glT.setText("Switch On");
        } else {
            // Off
            glT.setText("Switch Off");
        }
    }
}
