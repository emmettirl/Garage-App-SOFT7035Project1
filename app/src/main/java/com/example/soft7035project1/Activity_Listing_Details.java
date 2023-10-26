package com.example.soft7035project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.io.IOException;
import java.io.InputStream;

public class Activity_Listing_Details extends AppCompatActivity {

    FloatingActionButton fabBack;
    FloatingActionButton fabHome;
    FloatingActionButton fabWebsite;

    TabLayout tab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing_details);


        String contextModel = getIntent().getStringExtra("model");
        String contextYear = getIntent().getStringExtra("year");
        String contextPrice = getIntent().getStringExtra("price");
        String imageFilePath = getIntent().getStringExtra("imageFilePath");

        TextView textviewModel = findViewById(R.id.textviewCardModel);
        TextView textviewPrice = findViewById(R.id.textviewCardPrice);
        TextView textviewYear = findViewById(R.id.textviewCardYear);

        textviewModel.setText(contextModel);
        textviewPrice.setText(contextYear);
        textviewYear.setText(contextPrice);


        this.tab=findViewById(R.id.tabLayout2);


        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int selectedTab = tab.getPosition();
                Intent intent;

                if (selectedTab == 0) {
                    Uri uri = Uri.parse("https://www.donedeal.ie/cars?words=" + contextModel.replaceAll(
                            "[^a-zA-Z0-9 ]", ""));
                    intent = new Intent(Intent.ACTION_VIEW, uri);
                    try {
                        Log.d("myDebug", "onTabReselected: 0");
                        startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(Activity_Listing_Details.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                } else if (selectedTab == 1) {
                    intent = new Intent(Activity_Listing_Details.this, Activity_Main.class);
                    Log.d("myDebug", "onTabReselected: 1");
                    startActivity(intent);
                } else if (selectedTab ==2) {
                    intent = new Intent(Activity_Listing_Details.this, Activity_Listings.class);
                    Log.d("myDebug", "onTabReselected: 2");
                    startActivity(intent);
                } else {
                    Log.d("MyDebug", "onTabReselected: invalid tab selected");
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                int selectedTab = tab.getPosition();
                Intent intent;

                if (selectedTab == 0) {
                    Uri uri = Uri.parse("https://www.donedeal.ie/cars?words=" + contextModel.replaceAll(
                            "[^a-zA-Z0-9 ]", ""));
                    intent = new Intent(Intent.ACTION_VIEW, uri);
                    try {
                        Log.d("myDebug", "onTabReselected: 0");
                        startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(Activity_Listing_Details.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                } else if (selectedTab == 1) {
                    intent = new Intent(Activity_Listing_Details.this, Activity_Main.class);
                    Log.d("myDebug", "onTabReselected: 1");
                    startActivity(intent);
                } else if (selectedTab ==2) {
                    intent = new Intent(Activity_Listing_Details.this, Activity_Listings.class);
                    Log.d("myDebug", "onTabReselected: 2");
                    startActivity(intent);
                } else {
                    Log.d("MyDebug", "onTabReselected: invalid tab selected");
                }

            }
        });


        ImageView imageViewPhoto = findViewById(R.id.imageViewCard) ;

        InputStream ims;
        try {
            ims = getAssets().open(imageFilePath);
            Drawable d = Drawable.createFromStream(ims, null);
            imageViewPhoto.setImageDrawable(d);
            ims.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}