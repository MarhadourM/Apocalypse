package com.example.apocalypse

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LancerDesSensor : AppCompatActivity(), SensorEventListener {

    lateinit var  sensorManager: SensorManager
    private var isDeLancee =false
    lateinit var sensor: Sensor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_lancer_des)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager;
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)  !!

        sensorManager.registerListener(this,sensor, SensorManager.SENSOR_DELAY_GAME)


    }

    override fun onStop() {
        super.onStop()
        sensorManager.unregisterListener(this)
    }


    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this,sensor, SensorManager.SENSOR_DELAY_GAME)

    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        return
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if(event?.sensor?.getType()==Sensor.TYPE_ACCELEROMETER){
            val  values =event.values
            val x = values[0]
            val y = values[1]
            val z = values[2]

            var EG = SensorManager.GRAVITY_EARTH
            var devaccel = (x*x+y*y+z*z)/(EG*EG)

            if (devaccel>=3 && !isDeLancee){
                //lancer de dés
                //display Results ()
                //Partie.lancéeDesFait()
                /*TODO
                * si l'etat est  action  =view action
                * si l"etat est choix  = view choix bonux
                * si fin de parti  = view fin de parti
                * */

                isDeLancee = true
            }

        }
    }
}