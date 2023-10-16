package com.example.soft7035project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.io.InputStream;

public class Activity_Listing_Details extends AppCompatActivity {

    FloatingActionButton fabBack;
    FloatingActionButton fabHome;
    FloatingActionButton fabWebsite;




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

        ImageView imageViewPhoto = findViewById(R.id.imageViewCard) ;

        InputStream ims = null;
        try {
            ims = getAssets().open(imageFilePath);
            Drawable d = Drawable.createFromStream(ims, null);
            imageViewPhoto.setImageDrawable(d);
            ims.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        this.fabBack = findViewById(R.id.fabBackCardView);

        fabBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Listing_Details.this, Activity_Listings.class);
                startActivity(intent);
            }
        });


        this.fabHome = findViewById(R.id.fabHomeCardView);
        fabHome.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Activity_Listing_Details.this, Activity_Main.class);
            startActivity(intent);
        }
    });

        this.fabWebsite = findViewById(R.id.fabWebsiteCardView);



    }



}