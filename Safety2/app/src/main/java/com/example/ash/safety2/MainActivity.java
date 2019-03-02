package com.example.ash.safety2;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
//import com.parse.Parse;




public class MainActivity extends AppCompatActivity {


    EditText name, address, contact, report, date;
    Button btn;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_issue);
        name = (EditText) findViewById(R.id.editTextName);
        address = (EditText) findViewById(R.id.editTextAddress);
        contact = (EditText) findViewById(R.id.editTextContact);
        report = (EditText) findViewById(R.id.editTextReport);
        date = (EditText) findViewById(R.id.editTextDate);
        btn = (Button) findViewById(R.id.buttonSubmit);
        /*
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseObject reportObj = new ParseObject("Report");
                String First_name = name.getText().toString();
                String complain = report.getText().toString();

                reportObj.put("First_Name", First_name);
                reportObj.put("Complain", complain);
                reportObj.put("Last_Name", First_name);

                reportObj.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e==null){
                            Toast.makeText(context,e.getMessage(),Toast.LENGTH_LONG).show();
                        }else Toast.makeText(MainActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    */
    }


}
