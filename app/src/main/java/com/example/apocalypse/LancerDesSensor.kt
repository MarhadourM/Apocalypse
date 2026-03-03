package com.example.apocalypse

import android.annotation.SuppressLint

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.apocalypse.Game.Choix
import com.example.apocalypse.Game.EtatJeu
import com.example.apocalypse.Game.Joueur
import com.example.apocalypse.Game.Partie

class LancerDesSensor : BaseActivity(), SensorEventListener {

    lateinit var  sensorManager: SensorManager
    lateinit var sensor: Sensor
    private var isDeLancee =false


    lateinit var llBonusMalus: LinearLayout
    lateinit var imageViewDe1 : ImageView
    lateinit var imageViewDe2 : ImageView

    lateinit var tvResult: TextView
    lateinit var buttonSuivant: Button
    lateinit var  currentAction :Choix




    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_lancer_des)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val controller = WindowInsetsControllerCompat(window, window.decorView)
        controller.hide(WindowInsetsCompat.Type.systemBars())
        controller.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        Partie.currentContext= this
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager;
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)  !!

        sensorManager.registerListener(this,sensor, SensorManager.SENSOR_DELAY_GAME)


        // init attributs joueur
        tvPtsVie = findViewById(R.id.progressBarVie4)
        tvPtsHydra = findViewById(R.id.progressBarHydra4)
        tvPtsSasiete = findViewById(R.id.progressBarSatiete4)

        //init Dés
        imageViewDe1 = findViewById(R.id.ivDe1)
        imageViewDe2 = findViewById(R.id.ivDe2)

        //init resultat du lancée
        tvResult = findViewById(R.id.tvResult)

        //init contraint Layout
        llBonusMalus = findViewById(R.id.llBonusMalus)

        currentAction= Partie.currentAction

        //init Bouton pour l'action suivante
        buttonSuivant = findViewById(R.id.buttonSuivant)
        buttonSuivant.setOnClickListener {
            when(Partie.etatJeu){
                //Fin de partie
                EtatJeu.CHOIXBONUS -> createIntent(this , ChoixBonusView::class.java)
                EtatJeu.ACTION -> createIntent(this , ActionView::class.java)
                else -> Log.i("EtatParti","Etat de la partie  = "+ Partie.etatJeu )
            }

        }
        Log.i("EtatParti","Current Action  = "+ Partie.currentAction.getAction().getDesc() )

    }


    override fun onStart() {
        super.onStart()
        updateUI()
    }
    //mise a jour des attributs du Joueur


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

                var i = currentAction.lancerlesDés()
                displayRollDice(decomposerTotalEnDes(i))
                tvResult.text = i.toString()
                val result  = Partie.lancéeDesFait(i)
                if(result){
                    updateBonus(currentAction.getAction().getBonus().getAttribut())
                    updateUI()
                }else{
                    updateMalus(currentAction.getAction().getMalus().getAttribut())
                    updateUI()
                }
                Log.i("EtatParti","Etat de la partie  = "+ Partie.etatJeu )
                isDeLancee = true
                if(Partie.etatJeu != EtatJeu.FINPARTI){
                    buttonSuivant.visibility = View.VISIBLE
                }

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


    fun updateBonus(attributModifier : String){

        val inflater = LayoutInflater.from(this)
        val itemView = inflater.inflate(R.layout.bonus_view, llBonusMalus, false)
        var tvDescBonus = itemView.findViewById<TextView>(R.id.tvDescBonus)
        var tvBonusAttribut = itemView.findViewById<TextView>(R.id.tvBonusAttribut)

        tvDescBonus.text = currentAction.getAction().getBonus().getDesc()
        tvBonusAttribut.text = attributModifier

        llBonusMalus.addView(itemView)
    }

    fun updateMalus(attributModifier : String){

        val inflater = LayoutInflater.from(this)
        val itemView = inflater.inflate(R.layout.malus_view, llBonusMalus, false)
        var tvDescMalus = itemView.findViewById<TextView>(R.id.tvDescMalus)
        var tvMalusAttribut = itemView.findViewById<TextView>(R.id.tvMalusAttribut)

        tvDescMalus.text = currentAction.getAction().getMalus().getDesc()
        tvMalusAttribut.text = attributModifier

        llBonusMalus.addView(itemView)
    }



}


