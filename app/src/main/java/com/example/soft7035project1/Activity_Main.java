package com.example.soft7035project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class Activity_Main extends AppCompatActivity {

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
                    startIntent(0);
                } else if(radioBike.isChecked()) {
                    startIntent(1);
                } else if(radioOther.isChecked()) {
                    startIntent(2);
                } else {
                    Context context = getApplicationContext();
                    CharSequence text = "Please Select an option, then click \"Start\".";
                    Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void startIntent(int selectedTab){
        Intent intentStart = new Intent(Activity_Main.this, Activity_VehicleListing.class);
        intentStart.putExtra("selectedTab", selectedTab);
        startActivity(intentStart);
    }
}