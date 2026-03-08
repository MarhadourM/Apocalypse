package com.example.apocalypse

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.apocalypse.Game.Joueur
import com.example.apocalypse.Game.Partie

class IntroGameView : BaseActivity() {

    lateinit var  buttonPasserRegles : Button
    lateinit var  buttonRegleSuivante : Button

    var RessourcesRegles = arrayOf(R.layout.regle1,R.layout.regle2 ,R.layout.regle3,R.layout.regle4)
    var iRegle = 0

    lateinit var llRegles  : LinearLayout

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_intro_game_view)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val controller = WindowInsetsControllerCompat(window, window.decorView)
        controller.hide(WindowInsetsCompat.Type.systemBars())
        controller.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

        Partie.currentContext = this
        buttonPasserRegles = findViewById(R.id.buttonPasserRegles)
        buttonRegleSuivante = findViewById(R.id.buttonRegleSuivante)
        llRegles = findViewById(R.id.llRegles)

        buttonRegleSuivante.setOnClickListener {
            regleSuivante()
        }
        regleSuivante()

        buttonPasserRegles.setOnClickListener {
            Partie.finIntro()
            createIntent(this, ChoixBonusView::class.java)
        }
        if(Partie.isFirstPartie){
            buttonPasserRegles.text = "Survivre"

        }else{
            buttonPasserRegles.visibility = View.VISIBLE
        }


    }

    private fun regleSuivante() {
        llRegles.removeAllViews()
        val inflater = LayoutInflater.from(this)
        val itemView = inflater.inflate(RessourcesRegles[iRegle], llRegles, false)
        llRegles.addView(itemView)

        if (iRegle==3){
            buttonRegleSuivante.visibility = View.INVISIBLE
            buttonPasserRegles.visibility = View.VISIBLE
            iRegle = 0
        }else{
            iRegle++
        }


    }
}