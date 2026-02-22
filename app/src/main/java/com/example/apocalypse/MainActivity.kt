package com.example.apocalypse

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.apocalypse.Game.*
import kotlin.apply

class MainActivity : BaseActivity() {
    lateinit var buttonSurvivre: Button
    lateinit var etName: EditText
    lateinit var tvNbParties: TextView


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
        buttonSurvivre = findViewById(R.id.buttonSurvivre)
        etName = findViewById(R.id.etName)
        tvNbParties = findViewById(R.id.tvNbParties)

        etName.setOnEditorActionListener { view, i, event ->
            if (i == EditorInfo.IME_ACTION_DONE) {
                val nom = etName.text.toString()
                if (nom.isBlank()) {
                    Toast.makeText(this, "Le nom ne peut pas être vide", Toast.LENGTH_SHORT).show()
                } else {
                    Joueur.setNom(this, nom)
                    //cache le clavier quand le nom est validé
                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(etName.windowToken, 0)
                    etName.clearFocus()
                    displaySauvgarde()
                }
                true
            } else {
                false
            }
        }

        buttonSurvivre.setOnClickListener {
            Partie.demarrerParti()
            createIntent(this, IntroGameView::class.java)
        }

    }

    override fun onStart() {
        super.onStart()
        initNomJoueur()
        displaySauvgarde()
    }

    fun displaySauvgarde() {
        etName.setText(Joueur.getNom(this))
        val nbParties =  "Nombre de Parties Joués :" + Sauvgarde.getSauvNbPartie(this).toString()
        tvNbParties.text = nbParties
    }

    fun initNomJoueur() {
        if (!Sauvgarde.isSauvJoueurNomCreated(this)) {
            val defaultName = "patrik"
            etName.setText(defaultName)
            Joueur.setNom(this, defaultName)
        }


    }
}



