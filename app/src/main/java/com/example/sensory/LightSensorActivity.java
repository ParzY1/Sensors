package com.example.sensory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class LightSensorActivity extends AppCompatActivity implements SensorEventListener {

    private Sensor lightSensor;
    private SensorManager sensorManager;

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_sensor);

        textView = findViewById(R.id.light_sensor_measurements);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (lightSensor == null){
            return;
        }

        String measurements = lightSensor.getName() + ":\n\n";

        for (float m: sensorEvent.values) {
            measurements += m + "\n";
        }

        Log.i("SenTest", measurements);

        textView.setText(measurements);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onPause() {
        super.onPause();

        sensorManager.unregisterListener(this, lightSensor);
    }

    @Override
    protected void onResume() {
        super.onResume();

        sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }
}
//Marcel Parzyszek4p