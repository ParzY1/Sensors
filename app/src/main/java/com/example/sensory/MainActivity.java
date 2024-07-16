package com.example.sensory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SensorManager sensorManager;
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.light_sensor){
            runLightSensorActivity();
        } else if (item.getItemId() == R.id.gravity_sensor) {
            runGravitySensorActivity();
        } else if (item.getItemId() == R.id.accelerometer_sensor) {
            runAccelerometerSensorActivity();
        } else if (item.getItemId() == R.id.step_counter_sensor) {
            runStepCounterSensorActivity();
        } else if (item.getItemId() == R.id.gyroscope_sensor) {
            runGyroscopeSensorActivity();
        } else if (item.getItemId() == R.id.magnetic_sensor) {
            runMagneticSensorActivity();
        } else {
            super.onOptionsItemSelected(item);
        }

        return true;
    }

    private void runLightSensorActivity(){
        Intent intent = new Intent(this, LightSensorActivity.class);
        startActivity(intent);
    }

    private void runGravitySensorActivity(){
        Intent intent = new Intent(this, GravitySensorActivity.class);
        startActivity(intent);
    }

    private void runAccelerometerSensorActivity(){
        Intent intent = new Intent(this, AccelerometerSensorActivity.class);
        startActivity(intent);
    }

    private void runStepCounterSensorActivity(){
        Intent intent = new Intent(this, StepCounterActivity.class);
        startActivity(intent);
    }

    private void runGyroscopeSensorActivity(){
        Intent intent = new Intent(this, GyroscopeSensorActivity.class);
        startActivity(intent);
    }

    private void runMagneticSensorActivity(){
        Intent intent = new Intent(this, MagneticSensorActivity.class);
        startActivity(intent);
    }
}
//Marcel Parzyszek4p