package com.example.apocalypse

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.apocalypse.Game.Joueur
import com.example.apocalypse.Game.Partie

class LancerDesSensor : AppCompatActivity(), SensorEventListener {

    lateinit var  sensorManager: SensorManager
    private var isDeLancee =false

    lateinit var tvPtsVie : TextView
    lateinit var tvPtsHydra : TextView
    lateinit var tvPtsSasiete : TextView


    lateinit var button : Button
    lateinit var imageViewDe1 : ImageView
    lateinit var imageViewDe2 : ImageView

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


        // init attributs joueur
        tvPtsVie = findViewById(R.id.tvPtsVie3)
        tvPtsHydra = findViewById(R.id.tvPtsHydra3)
        tvPtsSasiete = findViewById(R.id.tvPtsSasiete3)

        //init Dés
        imageViewDe1 = findViewById(R.id.ivDe1)
        imageViewDe2 = findViewById(R.id.ivDe2)
        button = findViewById(R.id.buttonLancer)







    }


    override fun onStart() {
        super.onStart()
        updateUI()
    }
    //mise a jour des attributs du Joueur
    private fun updateUI() {
        tvPtsVie.text ="Vie :"+ Joueur.getPtsVie().toString()
        tvPtsHydra.text = "Hydratation : "+ Joueur.getPtsHydra().toString()
        tvPtsSasiete.text = "Nourriture : "+ Joueur.getPtsSatiete().toString()
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
                displayRollDice(decomposerTotalEnDes(Partie.currentAction.lancerlesDés()))
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


    private fun displayRollDice(paire: Pair<Int, Int>){

        val image1 = when(paire.first){
            1->R.drawable.dice_1
            2->R.drawable.dice_2
            3->R.drawable.dice_3
            4->R.drawable.dice_4
            5->R.drawable.dice_5
            else->R.drawable.dice_6
        }
        val image2 = when(paire.second){
            1->R.drawable.dice_1
            2->R.drawable.dice_2
            3->R.drawable.dice_3
            4->R.drawable.dice_4
            5->R.drawable.dice_5
            else->R.drawable.dice_6
        }
        imageViewDe1.setImageResource(image1)
        imageViewDe2.setImageResource(image2)

    }


    fun decomposerTotalEnDes(total: Int): Pair<Int, Int> {
        require(total in 2..12) { "Total doit être entre 2 et 12" }

        val possibles = mutableListOf<Pair<Int, Int>>()
        for (d1 in 1..6) {
            for (d2 in 1..6) {
                if (d1 + d2 == total) possibles.add(d1 to d2)
            }
        }
        val paire = possibles.random()
        return paire
    }
}


