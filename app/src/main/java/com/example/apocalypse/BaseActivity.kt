package com.example.apocalypse

import android.content.Context
import android.content.Intent
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import com.example.apocalypse.Game.Joueur

/**
 * Activity de base qui gère l'affichage des stats du joueur
 * et fournit des helpers pour la navigation.
 */
open class BaseActivity : AppCompatActivity() {
    lateinit var tvPtsVie : TextView
    lateinit var tvPtsHydra : TextView
    lateinit var tvPtsSasiete : TextView

    /** Met à jour l'affichage des points de vie, d'hydratation et de satiété. */
     fun updateUI() {
        tvPtsVie.text ="Vie :"+ Joueur.getPtsVie().toString()
        tvPtsHydra.text = "Hydratation : "+ Joueur.getPtsHydra().toString()
        tvPtsSasiete.text = "Nourriture : "+ Joueur.getPtsSatiete().toString()
    }



    fun createIntent(context: Context, activity : Class<out AppCompatActivity>){
        val intent = Intent(context,activity)
        context.startActivity(intent)
    }


    /** Ouvre l'Activity avec un extra "Choix" pour transmettre le choix du joueur. */
    fun createActionIntent(context: Context, activity : Class<out AppCompatActivity>, choix : String){
        val intent = Intent(context,activity).apply { this.putExtra("Choix",choix)}
        context.startActivity(intent)
    }


}