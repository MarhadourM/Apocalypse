package com.example.apocalypse

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.apocalypse.Game.Partie

class IntroGameView : BaseActivity() {

    lateinit var  buttonPasserRegles : Button
    lateinit var  buttonRegleSuivante : Button

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

        buttonRegleSuivante.setOnClickListener {
            regleSuivante()
        }

        if(!Partie.isFirstPartie){
            buttonPasserRegles.visibility = View.VISIBLE
            buttonPasserRegles.setOnClickListener {
                Partie.finIntro()
                createIntent(this, ChoixBonusView::class.java)
            }
        }

    }

    private fun regleSuivante() {
        TODO("Not yet implemented")
    }
}