package com.example.soft7035project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonStart = findViewById(R.id.buttonStart);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RadioButton radioCar = (RadioButton) findViewById(R.id.radioCar);
                RadioButton radioBike = (RadioButton) findViewById(R.id.radioBike);
                RadioButton radioOther = (RadioButton) findViewById(R.id.radioOther);

                if(radioCar.isChecked()) {
                    Intent intentStart = new Intent(MainActivity.this, Recycler_View_Vehicle_Listing.class);
                    startActivity(intentStart);
                } else if(radioBike.isChecked()) {
                    Intent intentStart = new Intent(MainActivity.this, Recycler_View_Vehicle_Listing.class);
                    startActivity(intentStart);
                } else if(radioOther.isChecked()) {
                    Intent intentStart = new Intent(MainActivity.this, Recycler_View_Vehicle_Listing.class);
                    startActivity(intentStart);
                } else {
                    Context context = getApplicationContext();
                    CharSequence text = "Please Select an option, then click \"Start\".";
                    Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}