package com.example.friends.motionsensor;

import java.util.Random;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.app.Activity;
import android.content.Context;

public class MainActivity extends Activity implements SensorEventListener {
    SensorManager sm;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=(TextView)findViewById(R.id.textView1);
        //get sensor service
        sm=(SensorManager)this.getSystemService(Context.SENSOR_SERVICE);
        //Tell which sensor you are going to use
        //And declare delay of sensor
        //Register all to your sensor object to use
        sm.registerListener(this,sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy)
    {
        // TODO Auto-generated method stub
    }
    //This method is called when your mobile moves any direction
    @Override
    public void onSensorChanged(SensorEvent event)
    {
        if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER)
        {
            //get x, y, z values
            float value[]=event.values;
            float x=value[0];
            float y=value[1];
            float z=value[2];
            //use the following formula
            //use gravity according to your place if you are on moon than use moon gravity
            float asr=(x*x+y*y+z*z)/(SensorManager.GRAVITY_EARTH*
                    SensorManager.GRAVITY_EARTH);
            //If mobile move any direction then the following condition will become true
            if(asr>=2)
            {
                //Generate random number every time and display on text view
                Random r=new Random();
                int i=r.nextInt(10);
                tv.setText(""+i);
            }
        }
    }
}

