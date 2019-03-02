package com.example.ash.safety;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;

public class MainActivity extends AppCompatActivity {

    EditText name,address,contact,report;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report1);
       // Parse.enableLocalDatastore(this);
        //Parse.initialize(this, "sq6YoPYvkbyinY7hi6XL3jzxsLE6IqpwX5imu7uk", "kd6O3ChBgE8DqUozCU4zyVsW2f2PNtfPpNpk5uqx");


        name=(EditText)findViewById(R.id.editTextName);
        address=(EditText)findViewById(R.id.editTextAddress);
        contact=(EditText)findViewById(R.id.editTextContact);
        btn = (Button)findViewById(R.id.buttonSubmit);

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //ParseObject reportObj = new ParseObject("Report");
                //reportObj.put("First_Name", name.toString());

               // reportObj.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            Toast.makeText(MainActivity.this, "Successfully Added", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }
                }}});
            }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
