package edu.fr5881cw.finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyDbHelper extends SQLiteOpenHelper {

    public MyDbHelper(@Nullable Context context) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create table on that db
        db.execSQL(Constants.CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //drop older table if exists
        db.execSQL("DROP TABLE IF EXISTS "+ Constants.TABLE_NAME);
        //create table again
        onCreate(db);
    }

    //insert record to db
    public long insertRecord(String name, String image, String bio, String phone,
                             String email, String dob, String addedTime, String updatedTime) {
        //get writable database because we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // id will be inserted automatically as we set AUTOINCREMENT in query

        //insert data
        values.put(Constants.C_NAME, name);
        values.put(Constants.C_IMAGE, image);
        values.put(Constants.C_BIO, bio);
        values.put(Constants.C_PHONE, phone);
        values.put(Constants.C_EMAIL, email);
        values.put(Constants.C_DOB, dob);
        values.put(Constants.C_ADDED_TIMESTAMP, addedTime);
        values.put(Constants.C_UPDATED_TIMESTAMP, updatedTime);

        //insert row, it will return record id of saved record
        long id = db.insert(Constants.TABLE_NAME, null, values);

        //close db connection
        db.close();

        //return id of inserted record
        return id;
    }

    //get all data
    public ArrayList<ModelRecord> getAllRecords(String orderBy){

        ArrayList<ModelRecord> recordsList = new ArrayList<>();
        //query to select records
        String selectQuery = "SELECT * FROM " + Constants.TABLE_NAME + " ORDER BY " + orderBy;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //looping through all records and add to list
        if (cursor.moveToFirst()){
            do {
                ModelRecord modelRecord = new ModelRecord(
                        ""+cursor.getInt(cursor.getColumnIndex(Constants.C_ID)),
                        ""+cursor.getInt(cursor.getColumnIndex(Constants.C_NAME)),
                        ""+cursor.getInt(cursor.getColumnIndex(Constants.C_IMAGE)),
                        ""+cursor.getInt(cursor.getColumnIndex(Constants.C_BIO)),
                        ""+cursor.getInt(cursor.getColumnIndex(Constants.C_PHONE)),
                        ""+cursor.getInt(cursor.getColumnIndex(Constants.C_EMAIL)),
                        ""+cursor.getInt(cursor.getColumnIndex(Constants.C_DOB)),
                        ""+cursor.getInt(cursor.getColumnIndex(Constants.C_ADDED_TIMESTAMP)),
                        ""+cursor.getInt(cursor.getColumnIndex(Constants.C_UPDATED_TIMESTAMP))
                );

                //add record to list
                recordsList.add(modelRecord);
            }while (cursor.moveToNext());
        }

        //close db connection
        db.close();

        //return the list
        return recordsList;
    }

    //search data
    public ArrayList<ModelRecord> searchRecords(String query){

        ArrayList<ModelRecord> recordsList = new ArrayList<>();
        //query to select records
        String selectQuery = "SELECT * FROM " + Constants.TABLE_NAME + " WHERE " + Constants.C_NAME + " LIKE '%" + query +"%'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //looping through all records and add to list
        if (cursor.moveToFirst()){
            do {
                ModelRecord modelRecord = new ModelRecord(
                        ""+cursor.getInt(cursor.getColumnIndex(Constants.C_ID)),
                        ""+cursor.getInt(cursor.getColumnIndex(Constants.C_NAME)),
                        ""+cursor.getInt(cursor.getColumnIndex(Constants.C_IMAGE)),
                        ""+cursor.getInt(cursor.getColumnIndex(Constants.C_BIO)),
                        ""+cursor.getInt(cursor.getColumnIndex(Constants.C_PHONE)),
                        ""+cursor.getInt(cursor.getColumnIndex(Constants.C_EMAIL)),
                        ""+cursor.getInt(cursor.getColumnIndex(Constants.C_DOB)),
                        ""+cursor.getInt(cursor.getColumnIndex(Constants.C_ADDED_TIMESTAMP)),
                        ""+cursor.getInt(cursor.getColumnIndex(Constants.C_UPDATED_TIMESTAMP))
                );

                //add record to list
                recordsList.add(modelRecord);
            }while (cursor.moveToNext());
        }
        //close db connection
        db.close();

        //return the list
        return recordsList;
    }

    //get number of records
    public int getRecordsCount(){
        String countQuery = "SELECT * FROM " + Constants.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();

        cursor.close();

        return count;
    }
}
