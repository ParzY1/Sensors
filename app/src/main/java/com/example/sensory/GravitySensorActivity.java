package com.example.sensory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;

public class GravitySensorActivity extends AppCompatActivity implements SensorEventListener {

    TextView gravitySensorName;
    TextView gravitySensorReadings;
    private SensorManager sensorManager;
    private Sensor gravitySensor;
    boolean isSensorPresent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gravity_sensor);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        gravitySensorName = findViewById(R.id.gravity_sensor_name);
        gravitySensorReadings = findViewById(R.id.gravity_sensor_readings);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        if (sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY) != null){
            gravitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
            isSensorPresent = true;
            Log.v("SeTest", "detect  sensor");
        } else {
            gravitySensorName.setText("Sensor is not present!");
            isSensorPresent = false;
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        gravitySensorReadings.setText(String.valueOf((int) sensorEvent.values[0]));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.v("SenTest", "onResume()");

        if (gravitySensor != null){
            sensorManager.registerListener(this, gravitySensor, SensorManager.SENSOR_DELAY_NORMAL);
            gravitySensorName.setText(gravitySensor.getName());
            Log.v("SenTest", "Ok registerListener");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (gravitySensor != null){
            sensorManager.unregisterListener(this, gravitySensor);
        }
        Log.v("SenTest", "onPause()");
    }
}
//Marcel Parzyszek4p