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

public class MagneticSensorActivity extends AppCompatActivity implements SensorEventListener {

    TextView magneticSensorName;
    TextView magneticSensorReadings;
    private SensorManager sensorManager;
    private Sensor magneticSensor;
    boolean isSensorPresent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magnetic_sensor);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        magneticSensorName = findViewById(R.id.magnetic_sensor_name);
        magneticSensorReadings = findViewById(R.id.magnetic_sensor_readings);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        if (sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD) != null){
            magneticSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
            isSensorPresent = true;
            Log.v("SenTest", "Sensor detected.");
        } else {
            magneticSensorName.setText("Sensor is not present!");
            isSensorPresent = false;
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        magneticSensorReadings.setText(String.valueOf((int) event.values[0]));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v("SenTest", "------------> onResume()");

        if (magneticSensor != null){
            sensorManager.registerListener(this, magneticSensor, SensorManager.SENSOR_DELAY_NORMAL);
            Log.v("SenTest", "------------> Ok registerListener");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (magneticSensor != null){
            sensorManager.unregisterListener(this, magneticSensor);
            Log.v("SenTest", "------------> onPause()");
        }
    }
}
//Marcel Parzyszek4p