package com.example.pracsensores;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Slumino extends AppCompatActivity {

     // float valormax;
     ConstraintLayout fondopantalla2;
    Button regresar;
    Sensor misensorr;
    SensorManager admistradorsensoress;
    SensorEventListener escuchuadordeEventoss;

    TextView etiquetaresultados;
    ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slumino);
        setContentView(R.layout.activity_slumino);

        fondopantalla2 = findViewById(R.id.fonpantallas);

        etiquetaresultados = findViewById(R.id.TXVresultado);

        admistradorsensoress = (SensorManager) getSystemService(SENSOR_SERVICE);

        misensorr = admistradorsensoress.getDefaultSensor(Sensor.TYPE_LIGHT);

        if (misensorr == null) {
            Toast.makeText(Slumino.this, "No se detecto el sensor ", Toast.LENGTH_SHORT).show();
          //  finish();
        } else {
            Toast.makeText(Slumino.this, "El sensor se a detectado", Toast.LENGTH_SHORT).show();
        }
        //valormax = misensorr.getMaximumRange();
        escuchuadordeEventoss = new SensorEventListener() {

            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                // if (sensorEvent.values[0] < misensorr.getMaximumRange()); {
                //  etiquetaresultados.setText("Valor de el sensor: " + sensorEvent.values[0]+ "\n Se ha acercado al senser");

/*
                if (sensorEvent.values[0] < misensorr.getMaximumRange()) {
                    etiquetaresultados.setText("Valor de el sensor: " + sensorEvent.values[0] + "\n Se ha acercado al senser");
                    // fondopantalla2.setBackgroundColor(Color.RED);
                } else {
                    etiquetaresultados.setText("Valor de el sensor: " + sensorEvent.values[0] + "\n Se ha alejado del senser");
                    //fondopantalla2.setBackgroundColor(Color.GREEN);
                }*/


                //int newValue = (int) (255f *   / valormax);
               // fondopantalla2.setBackgroundColor(Color.rgb(newValue, newValue, newValue));

                etiquetaresultados.setText("Valor de el sensor: " + sensorEvent.values[0] );
            }
            // getSupportActionBar().setTitle("Luminosidad : " + value + " lx");


            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
        inicializarSensor();

        regresar= findViewById(R.id.BTNregresar);
        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public void inicializarSensor(){
        admistradorsensoress.registerListener(escuchuadordeEventoss,misensorr,SensorManager.SENSOR_DELAY_FASTEST);

    }

    public void  detenerSensor(){
        admistradorsensoress.unregisterListener(escuchuadordeEventoss,misensorr);
    }

    @Override
    protected void onResume() {
        inicializarSensor();
        super.onResume();
    }

    @Override
    protected void onPause() {
        detenerSensor();
        super.onPause();
    }

}
