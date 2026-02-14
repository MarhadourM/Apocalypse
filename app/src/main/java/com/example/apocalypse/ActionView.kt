package com.example.apocalypse

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.apocalypse.Game.Joueur
import com.example.apocalypse.R.*

class ActionView: AppCompatActivity() {

    lateinit var tvChoixCarteBonus: TextView
    lateinit var buttonLancerDes : Button

    lateinit var tvPtsVie : TextView
    lateinit var tvPtsHydra : TextView
    lateinit var tvPtsSasiete : TextView



    override fun onStart() {
        super.onStart()
        updateUI()
    }
    //mise a jour des attributs du Joueur
    private fun updateUI() {
        tvPtsVie.text ="Vie :"+ Joueur.getPtsHydra().toString()
        tvPtsHydra.text = "Hydratation : "+ Joueur.getPtsHydra().toString()
        tvPtsSasiete.text = "Nourriture : "+ Joueur.getPtsSatiete().toString()
    }

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(layout.action_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(id.actionview)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        tvChoixCarteBonus = findViewById(R.id.tvChoixCarteBonus)
        buttonLancerDes  = findViewById(R.id.buttonLancerDes)

        tvPtsVie = findViewById(R.id.tvPtsVie2)
        tvPtsHydra = findViewById(R.id.tvPtsHydra2)
        tvPtsSasiete = findViewById(R.id.tvPtsSasiete2)

        val mode = intent.getStringExtra("Choix")
        setTextTvChoixCarte(mode)

        buttonLancerDes.setOnClickListener {
            val intent = Intent(this, LancerDesSensor::class.java)
            startActivity(intent)
        }

    }
    public fun setTextTvChoixCarte(txt: String?){
        tvChoixCarteBonus.text = "Vous avez Choisi"+txt

    }

}