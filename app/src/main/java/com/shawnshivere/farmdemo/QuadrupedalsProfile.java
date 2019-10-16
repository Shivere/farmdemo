package com.shawnshivere.farmdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import android.app.DatePickerDialog;
//import android.app.DialogFragment;
import android.text.InputType;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static android.graphics.Color.GREEN;

public class QuadrupedalsProfile extends AppCompatActivity{
    private static final String TAG = QuadrupedalsProfile.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quadrupedals_profile);
        Log.d(TAG, "onCreate: started");
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initCollapsingToolbar();

        getIncomingIntent();

        //Define views
        //findViews();

        //set graph
        animalGraph();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with edit  profile", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        try {
            //Set profile image.
            ImageView image = findViewById(R.id.imageLoad);
            image.setImageResource(Image);
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    String Name;
    int Image;
    public void getIncomingIntent(){
        Log.d(TAG, "getIncomingIntent: Checking for incoming intents");
        if (getIntent().hasExtra("name")
                && getIntent().hasExtra("img")
                && getIntent().hasExtra("type")
                && getIntent().hasExtra("gender")){
            Log.d(TAG, "getIncomingIntent: Found intent extras.");
            Image = getIntent().getIntExtra("img",-1);
            Name = getIntent().getStringExtra("name");
            String Type = getIntent().getStringExtra("type");
            String Gender = getIntent().getStringExtra("gender");

            setIntents(Image, Name, Type, Gender);
        }
        else {
            Log.d(TAG, "getIncomingIntent: Failed to store intents");
        }
    }

    public void setIntents(int Image, String Name, String Type, String Gender){
        Log.d(TAG, "setIntents: Setting intents to widgets.");
//        ImageView image = findViewById(R.id.imageLoad);
//        image.setImageResource(Image);
        TextView name = findViewById(R.id.profile_name);
        name.setText(Name);
        TextView type = findViewById(R.id.profileType);
        type.setText(Type);
        TextView gender = findViewById(R.id.profile_gender);
        gender.setText(Gender);
        TextView eachCowsMilk = findViewById(R.id.cowDailyProduction);
        eachCowsMilk.setText(Name + " daily milk production graph:");
        //check if all intent are set successfully
        Log.d(TAG, "setIntents: All intents set to widgets successfully");

    }

    public void TreatmentAddPopup(View view) {
        Intent i = new Intent(QuadrupedalsProfile.this, QuadrupedsAddTreatment.class);
        startActivity(i);
    }

    public void AddCowMilk(View view) {
        Intent i = new Intent(QuadrupedalsProfile.this, AddMilkPopup.class);
        startActivity(i);
    }

    public void animalGraph(){
        // generate Dates
        Calendar calendar = Calendar.getInstance();
        Date d1 = calendar.getTime();
        calendar.add(Calendar.DATE, 1);
        Date d2 = calendar.getTime();
        calendar.add(Calendar.DATE, 1);
        Date d3 = calendar.getTime();
        calendar.add(Calendar.DATE, 1);
        Date d4 = calendar.getTime();
        calendar.add(Calendar.DATE, 1);
        Date d5 = calendar.getTime();
        calendar.add(Calendar.DATE, 1);
        Date d6 = calendar.getTime();
        calendar.add(Calendar.DATE, 1);
        Date d7 = calendar.getTime();
        calendar.add(Calendar.DATE, 1);
        Date d8 = calendar.getTime();
        calendar.add(Calendar.DATE, 1);
        Date d9 = calendar.getTime();
        calendar.add(Calendar.DATE, 1);
        Date d10 = calendar.getTime();
        calendar.add(Calendar.DATE, 1);
        Date d11 = calendar.getTime();
        calendar.add(Calendar.DATE, 1);
        Date d12 = calendar.getTime();
        calendar.add(Calendar.DATE, 1);
        Date d13 = calendar.getTime();
        calendar.add(Calendar.DATE, 1);
        Date d14 = calendar.getTime();
        calendar.add(Calendar.DATE, 1);
        Date d15 = calendar.getTime();
        calendar.add(Calendar.DATE, 1);
        Date d16 = calendar.getTime();
        calendar.add(Calendar.DATE, 1);
        Date d17 = calendar.getTime();
        calendar.add(Calendar.DATE, 1);
        Date d18 = calendar.getTime();
        calendar.add(Calendar.DATE, 1);
        Date d19 = calendar.getTime();
        calendar.add(Calendar.DATE, 1);
        Date d20 = calendar.getTime();
        calendar.add(Calendar.DATE, 1);
        Date d21 = calendar.getTime();
        calendar.add(Calendar.DATE, 1);
        Date d22 = calendar.getTime();
        calendar.add(Calendar.DATE, 1);
        Date d23 = calendar.getTime();
        calendar.add(Calendar.DATE, 1);
        Date d24 = calendar.getTime();
        calendar.add(Calendar.DATE, 1);
        Date d25 = calendar.getTime();
        calendar.add(Calendar.DATE, 1);
        Date d26 = calendar.getTime();
        calendar.add(Calendar.DATE, 1);
        Date d27 = calendar.getTime();
        calendar.add(Calendar.DATE, 1);
        Date d28 = calendar.getTime();
        calendar.add(Calendar.DATE, 1);
        Date d29 = calendar.getTime();
        calendar.add(Calendar.DATE, 1);
        Date d30 = calendar.getTime();

        GraphView graph = (GraphView) findViewById(R.id.profile_graph);

// you can directly pass Date objects to DataPoint-Constructor
// this will convert the Date to double via Date#getTime()
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(d1, 1),
                new DataPoint(d2, 5),
                new DataPoint(d3, 3),
                new DataPoint(d4, 1),
                new DataPoint(d5, 5),
                new DataPoint(d6, 3),
                new DataPoint(d7, 1),
                new DataPoint(d8, 5),
                new DataPoint(d9, 3),
                new DataPoint(d10, 0),
                new DataPoint(d11, 5),
                new DataPoint(d12, 3),
                new DataPoint(d13, 1),
                new DataPoint(d14, 5),
                new DataPoint(d15, 3),
                new DataPoint(d16, 1),
                new DataPoint(d17, 5),
                new DataPoint(d18, 3),
                new DataPoint(d19, 5),
                new DataPoint(d20, 10),
                new DataPoint(d21, 5),
                new DataPoint(d22, 3),
                new DataPoint(d23, 1),
                new DataPoint(d24, 5),
                new DataPoint(d25, 3),
                new DataPoint(d26, 1),
                new DataPoint(d27, 5),
                new DataPoint(d28, 3),
                new DataPoint(d29, 5),
                new DataPoint(d30, 10)
        });

        graph.addSeries(series);
        //Mark points
        series.setDrawDataPoints(true);

// set date label formatter
        graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(getApplicationContext()));
        graph.getGridLabelRenderer().setNumHorizontalLabels(4); // only 4 because of the space

// set manual x bounds to have nice steps
        graph.getViewport().setMinX(d1.getTime());
        graph.getViewport().setMaxX(d30.getTime());
        graph.getViewport().setXAxisBoundsManual(true);

//        //        set title
//        graph.setTitle(Name + " milk production");
//        graph.setTitleTextSize(30);
//        graph.setTitleColor(GREEN);
//        Set label for horizontal and vertical axes
        graph.getGridLabelRenderer().setHorizontalAxisTitle("Date");
        graph.getGridLabelRenderer().setHorizontalAxisTitleColor(GREEN);
        graph.getGridLabelRenderer().setHorizontalAxisTitleTextSize(30);
        graph.getGridLabelRenderer().setHorizontalLabelsAngle(20);
        graph.getGridLabelRenderer().setVerticalAxisTitle("Litres");
        graph.getGridLabelRenderer().setVerticalAxisTitleColor(GREEN);
        graph.getGridLabelRenderer().setVerticalAxisTitleTextSize(30);

// as we use dates as labels, the human rounding to nice readable numbers
// is not necessary
        graph.getGridLabelRenderer().setHumanRounding(false);
        //        Enable scrolling and zooming
        graph.getViewport().setScalable(true);
    }

}
