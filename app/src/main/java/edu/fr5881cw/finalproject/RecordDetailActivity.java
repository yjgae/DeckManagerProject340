package edu.fr5881cw.finalproject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TextView;

import com.blogspot.atifsoftwares.circularimageview.CircularImageView;

import java.util.Calendar;
import java.util.Locale;

public class RecordDetailActivity extends AppCompatActivity {

    //views
    private CircularImageView profileIv;
    private TextView bioTv, nameTv, phoneTv, emailTv, dobTv, addedTimeTv, updatedTimeTv;

    //actionbar
    private ActionBar actionBar;

    //db helper
    private MyDbHelper dbHelper;

    private String recordID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_detail);

        //setting up actionbar with title and back button
        actionBar = getSupportActionBar();
        actionBar.setTitle("Record Details");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        //get record id from adapter through intent
        Intent intent = getIntent();
        recordID = intent.getStringExtra("RECORD_ID");

        //init db helper class
        dbHelper = new MyDbHelper(this);

        //init views
        profileIv = findViewById(R.id.profileIv);
        bioTv = findViewById(R.id.bioTv);
        nameTv = findViewById(R.id.nameTv);
        phoneTv = findViewById(R.id.phoneTv);
        emailTv = findViewById(R.id.emailTv);
        dobTv = findViewById(R.id.dobTv);
        addedTimeTv = findViewById(R.id.addedTimeTv);
        updatedTimeTv = findViewById(R.id.updatedTimeTv);

        showRecordDetails();
    }

    private void showRecordDetails() {
        //get record details

        //query tto select record based on record id
        String selectQuery = "SELECT * FROM " + Constants.TABLE_NAME + " WHERE " + Constants.C_ID +" =\"" + recordID+"\"";

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //keep checking in whole db for that record
        if (cursor.moveToFirst()){
            do {
                //get data
                String id = ""+ cursor.getInt(cursor.getColumnIndex(Constants.C_ID));
                String name = ""+ cursor.getInt(cursor.getColumnIndex(Constants.C_NAME));
                String image = ""+ cursor.getInt(cursor.getColumnIndex(Constants.C_IMAGE));
                String bio = ""+ cursor.getInt(cursor.getColumnIndex(Constants.C_BIO));
                String phone = ""+ cursor.getInt(cursor.getColumnIndex(Constants.C_PHONE));
                String email = ""+ cursor.getInt(cursor.getColumnIndex(Constants.C_EMAIL));
                String dob = ""+ cursor.getInt(cursor.getColumnIndex(Constants.C_DOB));
                String timestampAdded = ""+ cursor.getInt(cursor.getColumnIndex(Constants.C_ADDED_TIMESTAMP));
                String timestampUpdated = ""+ cursor.getInt(cursor.getColumnIndex(Constants.C_UPDATED_TIMESTAMP));

                //convert timestamp to dd/mm/yy hh:mm aa e.g 25/04/2020 08:01 AM
                Calendar calendar1 = Calendar.getInstance(Locale.getDefault());
                calendar1.setTimeInMillis(Long.parseLong(timestampAdded));
                String timeAdded = ""+DateFormat.format("dd/MM/yyyy hh:mm:aa", calendar1);


                Calendar calendar2 = Calendar.getInstance(Locale.getDefault());
                calendar2.setTimeInMillis(Long.parseLong(timestampUpdated));
                String timeUpdated = ""+DateFormat.format("dd/MM/yyyy hh:mm:aa", calendar2);

                //set data
                nameTv.setText(name);
                bioTv.setText(bio);
                phoneTv.setText(phone);
                emailTv.setText(email);
                dobTv.setText(dob);
                addedTimeTv.setText(timeAdded);
                updatedTimeTv.setText(timeUpdated);
                if (image.equals("null")) {
                    // no image in record
                    profileIv.setImageResource(R.drawable.ic_person_black);
                }
                else {
                    //have image in record
                    profileIv.setImageURI(Uri.parse(image));
                }

            }while (cursor.moveToNext());
        }

        //close db connection
        db.close();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();// goto previous activity
        return super.onSupportNavigateUp();
    }
}
