package com.example.apocalypse

import android.content.Context
import android.content.Intent
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import com.example.apocalypse.Game.Joueur

open class BaseActivity : AppCompatActivity() {
    lateinit var tvPtsVie : TextView
    lateinit var tvPtsHydra : TextView
    lateinit var tvPtsSasiete : TextView


     fun updateUI() {
        tvPtsVie.text ="Vie :"+ Joueur.getPtsVie().toString()
        tvPtsHydra.text = "Hydratation : "+ Joueur.getPtsHydra().toString()
        tvPtsSasiete.text = "Nourriture : "+ Joueur.getPtsSatiete().toString()
    }



    fun createIntent(context: Context, activity : Class<out AppCompatActivity>){
        val intent = Intent(context,activity)
        context.startActivity(intent)
    }
    fun createActionIntent(context: Context, activity : Class<out AppCompatActivity>, choix : String){
        val intent = Intent(context,activity).apply { this.putExtra("Choix",choix)}
        context.startActivity(intent)
    }


}