package com.example.sensory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;

public class StepCounterActivity extends AppCompatActivity implements SensorEventListener {

    private static final int MY_PERMISSIONS_REQUEST_ACTIVITY_RECOGNITION = 1;
    TextView stepCounterName;
    TextView stepCounterReadings;
    private SensorManager sensorManager;
    private Sensor stepCounterSensor;
    boolean isSensorPresent;
    int stepCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_step_counter);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        stepCounterName = findViewById(R.id.step_counter_name);
        stepCounterReadings = findViewById(R.id.step_counter_readings);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACTIVITY_RECOGNITION)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACTIVITY_RECOGNITION}, 1);
        }

        if (sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null) {
            stepCounterSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
            isSensorPresent = true;
            Log.v("SenTest", "Sensor detected.");
            stepCounterName.setText(stepCounterSensor.getName());
        } else {
            stepCounterName.setText("Sensor is not present!");
            isSensorPresent = false;
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
            stepCount++;
            stepCounterReadings.setText(String.valueOf(stepCount));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v("SenTest", "------------> onResume()");

        if (stepCounterSensor != null){
            sensorManager.registerListener(this, stepCounterSensor, SensorManager.SENSOR_DELAY_NORMAL);
            Log.v("SenTest", "Ok registerListener");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (stepCounterSensor != null){
            sensorManager.unregisterListener(this, stepCounterSensor);
        }
        Log.v("SenTest", "------------> onPause()");
    }
}
//Marcel Parzyszek4p