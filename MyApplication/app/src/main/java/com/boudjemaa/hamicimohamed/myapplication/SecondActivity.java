package com.boudjemaa.hamicimohamed.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.util.Calendar;

public class SecondActivity extends AppCompatActivity implements SensorEventListener, StepListener{
    private SharedPreferences sharedPref = null;
    private TextView textView;
    private StepDetector simpleStepDetector;
    private SensorManager sensorManager;
    private Sensor accel;
    private static final String TEXT_NUM_STEPS = "Number of Steps: ";
    private static int numSteps;
    TextView TvSteps;
    ProgressBar PbSteps;
    Button  BtnStart;
    Button BtnStop;
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        db = new DatabaseHandler(this);

        sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        // Get the information from the intent
        String name = sharedPref.getString(getString(R.string.user_name), "");

        // Create the text view
        TextView textView = findViewById(R.id.saved_name);

        // Show the user's name on the screen
        textView.setText("Wassup "+name);

        // Get an instance of the SensorManager
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        simpleStepDetector = new StepDetector();
        simpleStepDetector.registerListener(this);

       TvSteps = (TextView) findViewById(R.id.tv_steps);
       PbSteps = (ProgressBar) findViewById(R.id.pb_steps);
       BtnStart = (Button) findViewById(R.id.btn_start);
       BtnStop = (Button) findViewById(R.id.btn_stop);


        //when we click on the start button
        BtnStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                numSteps = 0;
                sensorManager.registerListener(SecondActivity.this, accel, SensorManager.SENSOR_DELAY_FASTEST);

            }
        });

        //when we click on the stop button
        BtnStop.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Log.d("fu", "abc");
                sensorManager.unregisterListener(SecondActivity.this);

                Intent intent = new Intent(getApplicationContext(), History.class);
                String name = sharedPref.getString(getString(R.string.user_name), "");
                Double weight =Double.valueOf(sharedPref.getString(getString(R.string.weight), ""));
                Log.d("fu1", "abc");
                int calories = numSteps*2;
                Day d = new Day(name, numSteps, calories, weight, Calendar.getInstance().getTime());
                db.addRow(d);
                Log.d("fu2", "abc");
                startActivity(intent);

            }
        });



    }



    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            simpleStepDetector.updateAccel(
                    event.timestamp, event.values[0], event.values[1], event.values[2]);
        }
    }

    @Override
    public void step(long timeNs) {
        numSteps++;
        TvSteps.setText(TEXT_NUM_STEPS + numSteps);
        PbSteps.setProgress(numSteps);
    }


}
