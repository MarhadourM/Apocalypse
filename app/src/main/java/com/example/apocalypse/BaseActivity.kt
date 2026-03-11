package com.example.apocalypse

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.animation.DecelerateInterpolator
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import com.example.apocalypse.Game.Joueur

/**
 * Activity de base qui gère l'affichage des stats du joueur
 * et fournit des helpers pour la navigation.
 */
open class BaseActivity : AppCompatActivity() {
    lateinit var tvPtsVie : ProgressBar
    lateinit var tvPtsHydra : ProgressBar
    lateinit var tvPtsSasiete : ProgressBar

    /** Met à jour l'affichage des points de vie, d'hydratation et de satiété. */
     fun updateUI() {
        val cibleVie = Joueur.getPtsVie() * 4
        val cibleHydra = Joueur.getPtsHydra() * 4
        val cibleSatiete = Joueur.getPtsSatiete() * 4

        val animVie = ObjectAnimator.ofInt(tvPtsVie, "progress", tvPtsVie.progress, cibleVie)
        val animHydra = ObjectAnimator.ofInt(tvPtsHydra, "progress", tvPtsHydra.progress, cibleHydra)
        val animSatiete = ObjectAnimator.ofInt(tvPtsSasiete, "progress", tvPtsSasiete.progress, cibleSatiete)

        // 3. Regrouper les animations pour les jouer en même temps
        val animatorSet = AnimatorSet()
        animatorSet.playTogether(animVie, animHydra, animSatiete)
        animatorSet.duration = 1000

        animatorSet.start()

        val result = "Hydratation : ${Joueur.getPtsHydra()}, Satiété : ${Joueur.getPtsSatiete()}, Vie : ${Joueur.getPtsVie()}"
        Log.i("Attribut",result)
    }

    fun displayAttributs(){
        tvPtsVie.progress= Joueur.getPtsVie() * 4
        tvPtsHydra.progress= Joueur.getPtsHydra() * 4
        tvPtsSasiete.progress= Joueur.getPtsSatiete() * 4

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