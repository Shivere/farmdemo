package com.shawnshivere.farmdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Calendar;
import java.util.Date;

import static android.graphics.Color.GREEN;

public class total_milk_graph extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_milk_graph);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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


        GraphView graph = (GraphView) findViewById(R.id.graph);

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
        //graph.getGraphContentWidth(15);
        graph.setTitle("Graphical Representation");
        graph.setTitleTextSize(40);
        graph.setTitleColor(GREEN);
//        Set label for horizontal and vertical axes
        graph.getGridLabelRenderer().setHorizontalAxisTitle("Date");
        graph.getGridLabelRenderer().setHorizontalAxisTitleColor(GREEN);
        graph.getGridLabelRenderer().setHorizontalAxisTitleTextSize(30);
        graph.getGridLabelRenderer().setHorizontalLabelsAngle(20);
        graph.getGridLabelRenderer().setVerticalAxisTitle("Litres");
        graph.getGridLabelRenderer().setVerticalAxisTitleColor(GREEN);
        graph.getGridLabelRenderer().setVerticalAxisTitleTextSize(30);

// set manual x bounds to have nice steps
        graph.getViewport().setMinX(d1.getTime());
        graph.getViewport().setMaxX(d30.getTime());
        graph.getViewport().setXAxisBoundsManual(true);

// as we use dates as labels, the human rounding to nice readable numbers
// is not necessary
        graph.getGridLabelRenderer().setHumanRounding(false);

//        Enable scrolling and zooming
        graph.getViewport().setScalable(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                // todo: goto back activity from here

                Intent intent = new Intent(getApplicationContext(), Quadrupedals.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

