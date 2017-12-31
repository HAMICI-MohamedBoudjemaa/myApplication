package com.boudjemaa.hamicimohamed.myapplication;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class History extends AppCompatActivity {
DatabaseHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("history", "hais");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        print_history();

    }

    public void print_history()
    {
        db = new DatabaseHandler(this);
        List<Day> l = null;
        try {
            l = db.getAllRows();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        TableLayout table = (TableLayout) findViewById(R.id.history);
        TableRow row=new TableRow(this);
        TextView tvDebt=new TextView(this);
        table.removeAllViews();

        // We print the history
        for(int i=0;i<l.size();i++)
        {
            row=new TableRow(this);
            Day d = l.get(i);
            tvDebt=new TextView(this);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String date = sdf.format(d.getDate());
            tvDebt.setText("\n"+date+"\nUser "+d.getName()+"\n"+d.getSteps()+" Steps \n"+d.getCalories()+" Calories burned\n"+d.getWeight()+"Kg\n");
            row.addView(tvDebt);
            table.addView(row);
        }
    }

    public void flush (View view)
    {
        Log.d("flush", "fl");
        db = new DatabaseHandler(this);
        SQLiteDatabase mydb = db.getWritableDatabase();
        mydb.execSQL("delete from days");
        print_history();

    }
}
