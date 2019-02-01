package com.example.chapter2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private TextView capital;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Spinner element
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        button = (Button) findViewById(R.id.btn);
        capital = (TextView) findViewById(R.id.capital);

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
                Log.d("country",country);
                switch (country){
                    case "Bangladesh":
                        capital.setText("Dhaka");
                        break;
                    case "India":
                        capital.setText("New Delhi");
                        break;
                    case "Pakistan":
                        capital.setText("Islamabad");
                        break;
                    case "Srilanka":
                        capital.setText("Colombo");
                        break;
                    case "Nepal":
                        capital.setText("Kathmundu");
                        break;
                    case "Bhutan":
                        capital.setText("Thimpu");
                        break;
                    default:
                        capital.setText("Not Found");

                }

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
}
