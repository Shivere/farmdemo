package com.shawnshivere.farmdemo;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static android.os.Environment.getExternalStoragePublicDirectory;

public class AddAnimal extends AppCompatActivity  implements AdapterView.OnItemSelectedListener{
    private static final String TAG = AddAnimal.class.getSimpleName();

    private EditText dateEditText;
    private ImageView addImageView;
    private Spinner gender_spinner, mother_spinner;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat simpleDateFormat;
    private String mSpinnerLabel="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_animal);
        getSupportActionBar().setTitle("Add Animal");
        Log.d(TAG, "onCreate: Started");
        //set Date format
        simpleDateFormat = new SimpleDateFormat("dd-MM-yy", Locale.UK);
        findViews();
        setclicklisteners();
        setDateField();
    }

    static final int REQUEST_TAKE_PHOTO = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,"com.shawnshivere.farmdemo.fileprovider",
                        photoFile);
              //takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
        Log.d(TAG, "dispatchTakePictureIntent: Picture intent complete");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null){
            if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                addImageView.setImageBitmap(imageBitmap);
                galleryAddPic();
            }
        }
        else{
            Log.d(TAG, "onActivityResult: data is null");
        }
    }

    String mCurrentPhotoPath;

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat(getString(R.string.DateFormatPattern)).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        Log.d(TAG, "createImageFile: Image file created");
        return image;
    }

    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }

    private void setPic() {
        // Get the dimensions of the View
        int targetW = addImageView.getWidth();
        int targetH = addImageView.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        addImageView.setImageBitmap(bitmap);
    }

    public void findViews(){
        dateEditText = (EditText)findViewById(R.id.add_birthDate);
        dateEditText.setInputType(InputType.TYPE_NULL);
        addImageView = (ImageView)findViewById(R.id.add_imageView);

        //create spinner
        gender_spinner = (Spinner) findViewById(R.id.gender_spinner);
        if (gender_spinner != null) {
            gender_spinner.setOnItemSelectedListener(this);
            genderSpinners();
        }
        mother_spinner = (Spinner) findViewById(R.id.mother_spinner);
        if (mother_spinner != null) {
            mother_spinner.setOnItemSelectedListener(this);
            motherSpinners();
        }
    }

    public void genderSpinners(){
        // Create ArrayAdapter using the string array and default spinner layout.
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.gender_array,android.R.layout.simple_spinner_item);
        //Specify the layout to use when the list of choices appear.
        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        if (gender_spinner != null){
            gender_spinner.setAdapter(adapter);
        }
    }
    public void motherSpinners(){
        // Create ArrayAdapter using the string array and default spinner layout.
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.mother_array,android.R.layout.simple_spinner_item);
        //Specify the layout to use when the list of choices appear.
        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        if (mother_spinner != null){
            mother_spinner.setAdapter(adapter);
        }
    }

    public void setDateField(){

        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                dateEditText.setText(simpleDateFormat.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

    }

    //display the date picker dialog by calling show() method on dialog instance in onClick listener of EditText.
//    @Override
//    public void onClick(View view) {
//        if(view == dateEditText) {
//            datePickerDialog.show();
//        }
//        if(view == addImageView) {
//            dispatchTakePictureIntent();
//        }
////        if (view == spinner){
////
////        }
//    }
    public void showText(View view) {
        Toast.makeText(this, mSpinnerLabel, Toast.LENGTH_SHORT).show();
    }

    //set click listeners.
    public void setclicklisteners(){
        dateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });

        addImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        mSpinnerLabel = parent.getItemAtPosition(position).toString();
        showText(view);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Log.d(TAG, "onNothingSelected: Nothing selected");
    }
}
