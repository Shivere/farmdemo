package com.shawnshivere.farmdemo;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class BipedalsProfile extends AppCompatActivity{
    private static final String TAG = "BipedalsProfile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bipedals_profile);
        Log.d(TAG, "onCreate: BipedalsProfile started");
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Get intents from recycler view
        getIncomingIntents();

        //collapsing toolbar
        initCollapsingToolbar();

//        Find views
        findviews();

        try {
            //Set profile image.
            ImageView image = findViewById(R.id.imageLoad);
            image.setImageResource(Image);
        } catch (Exception e) {
            e.printStackTrace();
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with edit  profile", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


    }

    private void findviews() {

    }

    /**
          * Initializing collapsing toolbar
          * Will show and hide the toolbar title on scroll
          */
    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar = findViewById(R.id.collapsingToolbar_layout);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = findViewById(R.id.app_bar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    Log.d(TAG, "onOffsetChanged: Entered if scrollRange == -1");
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    Log.d(TAG, "onOffsetChanged: Entered scrollRange + verticalOffset == 0");
                    collapsingToolbar.setTitle(Name + " profile");
                    isShow = true;
                }
                else if (isShow) {
                    Log.d(TAG, "onOffsetChanged: Entered if isShow");
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

    String Name, Type, Gender;
    int Image;
    private void getIncomingIntents() {
        Log.d(TAG, "getIncomingIntents: Getting intents for Bipedals profile");
        if (getIntent().hasExtra("bipedals_name")
                && getIntent().hasExtra("bipedals_img")
                && getIntent().hasExtra("bipedals_type")
                && getIntent().hasExtra("bipedals_gender")) {
            Log.d(TAG, "getIncomingIntent: Found Bipedal intent extras.");
            Image = getIntent().getIntExtra("bipedals_img",-1);
            Name = getIntent().getStringExtra("bipedals_name");
            Type = getIntent().getStringExtra("bipedals_type");
            Gender = getIntent().getStringExtra("bipedals_gender");

            setIntents(Name, Type, Gender);
        }
        else {
            Log.d(TAG, "getIncomingIntents: Failed to store intents in variables");
        }
    }

    private void setIntents(String Name, String Type, String Gender) {
        Log.d(TAG, "setIntents: Setting intents into widgets");
        TextView name = findViewById(R.id.profile_name);
        name.setText(Name);
        TextView type = findViewById(R.id.profileType);
        type.setText(Type);
        TextView gender = findViewById(R.id.profile_gender);
        gender.setText(Gender);

        //check if all intent are set successfully
        Log.d(TAG, "setIntents: Intents set to widgets successfully.");
    }

    public void AddPopup(View view) {
        Intent i = new Intent(BipedalsProfile.this, bipedalsAddTreatment.class);
        startActivity(i);
    }
}
