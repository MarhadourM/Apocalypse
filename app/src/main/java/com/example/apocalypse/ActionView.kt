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
import com.example.apocalypse.Game.Partie
import com.example.apocalypse.R.*

class ActionView: BaseActivity() {

    lateinit var tvChoixCarteBonus: TextView
    lateinit var buttonLancerDes : Button


    lateinit var tvDescriptionAction : TextView
    lateinit var tvCible : TextView





    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(layout.action_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(id.actionview)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        Partie.currentContext=this
        tvChoixCarteBonus = findViewById(id.tvChoixCarteBonus)
        buttonLancerDes  = findViewById(id.buttonLancerDes)

        tvPtsVie = findViewById(id.tvPtsVie2)
        tvPtsHydra = findViewById(id.tvPtsHydra2)
        tvPtsSasiete = findViewById(id.tvPtsSasiete2)

        //init des TextView Lié à l'action
        tvDescriptionAction = findViewById(id.tvDescriptionAction)
        tvCible = findViewById(id.tvCible)


        val mode = intent.getStringExtra("Choix")
        setTextTvChoixCarte(mode)

        buttonLancerDes.setOnClickListener {
            Partie.etatLancerDes()
            val intent = Intent(this, LancerDesSensor::class.java)
            startActivity(intent)
        }

    }
     fun setTextTvChoixCarte(txt: String?){
        tvChoixCarteBonus.text = "Vous avez Choisi "+txt

    }

    override fun onStart() {
        super.onStart()
        updateUI()
        displayAction()
    }




    private fun displayAction() {
        tvDescriptionAction.text= Partie.currentAction.getAction().getDesc()
        var cible = " Cible - " + Partie.currentAction.getAction().getCible().toString()
        tvCible.text = cible

        // TODO affichage des bonus et malus
    }

}