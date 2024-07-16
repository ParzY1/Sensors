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

public class AccelerometerSensorActivity extends AppCompatActivity implements SensorEventListener {

    TextView accelerometerSensorName;
    TextView accelerometerSensorReadings;
    private SensorManager sensorManager;
    private Sensor accelerometerSensor;
    boolean isSensorPresent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer_sensor);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        accelerometerSensorName = findViewById(R.id.accelerometer_sensor_name);
        accelerometerSensorReadings = findViewById(R.id.accelerometer_sensor_readings);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
            accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            isSensorPresent = true;
            Log.v("SenTest", "Sensor detected.");
        } else {
            accelerometerSensorName.setText("Sensor is not present!");
            isSensorPresent = false;
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        accelerometerSensorReadings.setText(String.valueOf((int) event.values[0]));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v("SenTest", "------------> onResume()");

        if (accelerometerSensor != null){
            sensorManager.registerListener(this, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
            accelerometerSensorName.setText(accelerometerSensor.getName());
            Log.v("SenTest", "------------> Ok registerListener");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (accelerometerSensor != null){
            sensorManager.unregisterListener(this, accelerometerSensor);
        }

        Log.v("SenTest", "------------> onPause()");
    }
}
//Marcel Parzyszek4p