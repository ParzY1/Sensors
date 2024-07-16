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

public class GyroscopeSensorActivity extends AppCompatActivity implements SensorEventListener {

    TextView gyroscopeSensorName;
    TextView gyroscopeSensorReadings;
    private SensorManager sensorManager;
    private Sensor gyroscopeSensor;
    boolean isSensorPresent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyroscope_sensor);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        gyroscopeSensorName = findViewById(R.id.gyroscope_sensor_name);
        gyroscopeSensorReadings = findViewById(R.id.gyroscope_sensor_readings);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        if (sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE) != null){
            gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
            isSensorPresent = true;
            Log.v("SenTest", "Sensor detected.");
        } else {
            gyroscopeSensorName.setText("Sensor is not present!");
            isSensorPresent = false;
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        gyroscopeSensorReadings.setText(String.valueOf((int) event.values[0]));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v("SenTest", "------------> onResume()");

        if (gyroscopeSensor != null){
            sensorManager.registerListener(this, gyroscopeSensor, SensorManager.SENSOR_DELAY_NORMAL);
            gyroscopeSensorName.setText(gyroscopeSensor.getName());
            Log.v("SenTest", "------------> OK sensor registerListener");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (gyroscopeSensor != null){
            sensorManager.unregisterListener(this, gyroscopeSensor);
        }
        Log.v("SenTest", "------------> onPause()");
    }
}
//Marcel Parzyszek4p