package com.example.apocalypse

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.apocalypse.Game.*
import kotlin.apply

class MainActivity : BaseActivity() {
    lateinit var buttonSurvivre: Button

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        Partie.currentContext = this

        buttonSurvivre = findViewById(R.id.buttonSurvivre)

        buttonSurvivre.setOnClickListener {
            Partie.demarrerParti()
            createIntent(this, IntroGameView::class.java)
        }



    }


}



