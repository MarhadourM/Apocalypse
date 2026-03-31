package com.example.apocalypse

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.apocalypse.Game.*
import eightbitlab.com.blurview.BlurView

class MainActivity : BaseActivity() {
    lateinit var buttonSurvivre: Button

    lateinit var tvNbParties: TextView
    lateinit var backgroundView : ImageView
    lateinit var  buttonHistoire  : BlurView


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val controller = WindowInsetsControllerCompat(window, window.decorView)
        controller.hide(WindowInsetsCompat.Type.systemBars())
        controller.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

        Partie.currentContext = this
        //init
        buttonSurvivre = findViewById(R.id.buttonSurvivre2)
        tvNbParties = findViewById(R.id.tvNbParties)
        buttonHistoire = findViewById(R.id.glassHistoire)

        buttonSurvivre.setOnClickListener {
            Partie.demarrerParti()
            createIntent(this, IntroGameView::class.java)
        }

        buttonHistoire.setOnClickListener {
            HistoireDialog(this).show()
        }
        val glassBox = findViewById<BlurView>(R.id.glassBox)
        val glassCredit = findViewById<BlurView>(R.id.glassCredit2)
        backgroundView = findViewById(R.id.bgImage)

        setupDynamicGlassmorphism(this,glassBox,backgroundView, 5f,153)
        setupDynamicGlassmorphism(this,glassCredit,backgroundView, 5f,153)
        setupDynamicGlassmorphism(this,buttonHistoire,backgroundView,5f,153)
        colorizeButtonFromBackground(buttonSurvivre,backgroundView)

        glassCredit.setOnClickListener {DialogCredits(this).show()}





    }

    override fun onStart() {
        super.onStart()
        displaySauvgarde()
    }

    fun displaySauvgarde() {
        val nbParties =  "Nombre de Parties Joués : " + Sauvgarde.getSauvNbPartie(this).toString()
        tvNbParties.text = nbParties
    }

}



