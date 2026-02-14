package com.example.apocalypse.Game

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.apocalypse.ChoixBonusView
import com.example.apocalypse.R

class IntroGameView : AppCompatActivity() {

    lateinit var  buttonDialogue : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_intro_game_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        buttonDialogue = findViewById(R.id.buttonDialog)


        buttonDialogue.setOnClickListener {
            //TODO Partie.finIntro()
            val intent = Intent(this, ChoixBonusView::class.java)
            startActivity(intent)
        }
    }
}