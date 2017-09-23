package com.example.projectslddm.app02;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView diaNoite;
    private Sensor sensorLuz;
    private SensorManager sensorManager;
    //private ImageView imagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        diaNoite = (TextView)findViewById(R.id.dia_noite);

        //instanciando o sensor manager
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        //recupera do device o sensor de luminosidade
        sensorLuz = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        sensorManager.registerListener(new LuzSensor(), sensorLuz, SensorManager.SENSOR_DELAY_FASTEST);

        //imagem = (ImageView) findViewById(R.id.image);

    }//end onCreate

    public void onPause() {

        super.onPause();
        sensorManager.unregisterListener(new LuzSensor());

    }//end onPause

    public void onResume() {

        super.onResume();

    }//end onResume

    public void bomDia() {

        diaNoite.setText(R.string.bomDia);

        //imagem.setImageResource(R.drawable.sol);

    }//end day

    public void boaNoite() {

        diaNoite.setText(R.string.boaNoite);

        //imagem.setImageResource(R.drawable.lua);


    }//end nigth

    class LuzSensor implements SensorEventListener {

        public void onAccuracyChanged(Sensor sensor, int accuracy){}
        public void onSensorChanged(SensorEvent event){

            float vl = event.values[0];
            if(vl > 10) {

                bomDia();

            }
            else {

                boaNoite();

            }

        }

    }//end LuzSensor

}//end MainActivity