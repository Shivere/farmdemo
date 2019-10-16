package com.shawnshivere.farmdemo;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddMilkPopup extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = AddMilkPopup.class.getSimpleName();

    private EditText todaysMilkDate_EditText;
    private DatePickerDialog todaysMilkDatePickerDialog;
    private SimpleDateFormat simpleDateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_milk_popup);
        getSupportActionBar().setTitle(R.string.add_treatment);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.6));

        //set Date format
        simpleDateFormat = new SimpleDateFormat("dd-MM-yy", Locale.UK);

        //Define views
        findViews();
    }

    public void findViews(){
        todaysMilkDate_EditText = findViewById(R.id.todayMilkDate);
        todaysMilkDate_EditText.setInputType(InputType.TYPE_NULL);
        //set date and click listener for milk date.
        setMilkDateField();

    }


    public void setMilkDateField(){
        todaysMilkDate_EditText.setOnClickListener(this);

        Calendar newCalendar = Calendar.getInstance();
        todaysMilkDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                todaysMilkDate_EditText.setText(simpleDateFormat.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

    }

    //display the date picker dialog by calling show() method on dialog instance in onClick listener of EditText.
    @Override
    public void onClick(View view) {
        if(view == todaysMilkDate_EditText) {
            todaysMilkDatePickerDialog.show();
        }
        else {
            Log.d(TAG, "onClick: dateEditText in treatment was not clicked!");
        }
    }
}
