package com.boudjemaa.hamicimohamed.myapplication;

/**
 * Created by Hamici Mohamed on 31/12/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class DatabaseHandler extends SQLiteOpenHelper {

    // Database version
    private static final int DATABASE_VERSION = 4;

    // Database name
    private static final String DATABASE_NAME = "eventManager";

    // Table name
    private static final String TABLE_DAYS = "days";

    // Table columns names
    private static final String KEY_NAME = "name";
    private static final String KEY_STEPS = "steps";
    private static final String KEY_CALORIES = "calories";
    private static final String KEY_WEIGHT = "weight";
    private static final String KEY_DATE = "date";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Create the table
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("dbase", "here");
        String CREATE_EVENTS_TABLE = "CREATE TABLE " + TABLE_DAYS + "("
                + KEY_NAME + " TEXT,"
                + KEY_STEPS + " TEXT," + KEY_CALORIES + " TEXT," + KEY_WEIGHT + " TEXT," + KEY_DATE + " DATE" + ")";
        db.execSQL(CREATE_EVENTS_TABLE);

    }

    // Upgrade the database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("dbase", "here1");
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DAYS);

        // Create tables again
        onCreate(db);
    }

    // Add a new row
    void addRow(Day d) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, d.getName());
        values.put(KEY_STEPS, Double.toString(d.getSteps()));
        values.put(KEY_CALORIES, Double.toString(d.getCalories()));
        values.put(KEY_WEIGHT, d.getWeight());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(d.getDate());
        values.put(KEY_DATE, date);

        // Insert row
        db.insert(TABLE_DAYS, null, values);
        db.close();

    }

    // Get all rows
    public List<Day> getAllRows() throws ParseException {
        List<Day> l = new ArrayList<>();

        // Select all query
        String selectQuery = "SELECT * FROM " + TABLE_DAYS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Loop through all the rows and addi the to the list
        if (cursor.moveToFirst()) {
            do {
                Day d = new Day();
                d.setName(cursor.getString(0));
                d.setSteps(cursor.getInt(1));
                d.setCalories(cursor.getInt(2));
                d.setWeight(cursor.getDouble(3));
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date date = format.parse(cursor.getString(4));

                d.setDate(date);

                // Add row to list
                l.add(d);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        // Return the list
        return l;
    }

    // Clear the table
    public void clear() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_DAYS);
        db.close();
    }

}