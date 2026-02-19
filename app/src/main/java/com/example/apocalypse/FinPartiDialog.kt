package com.example.apocalypse

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.Button
import android.widget.LinearLayout
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.apocalypse.Game.Attribut
import com.example.apocalypse.Game.EtatJeu
import com.example.apocalypse.Game.Joueur
import com.example.apocalypse.Game.Partie

class FinPartiDialog(private val context: Context) {
    val dialog = Dialog(context)
    lateinit var  llCauseFinParti : LinearLayout

    fun show() {
        Log.i("dialog","dans la methode show ")
        Log.i("dialog","context"+ Partie.currentContext)
        dialog.setContentView(R.layout.fin_parti)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        val window = dialog.window
        if (window != null) {
            val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)
            windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())
            windowInsetsController.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

            window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
            window.setDimAmount(0.7f)

            //taille du popup
            val width = (context.resources.displayMetrics.widthPixels * 0.95).toInt()
            val height = (context.resources.displayMetrics.heightPixels * 0.90).toInt()
            window.setLayout(width, height)
        }
        llCauseFinParti = dialog.findViewById<LinearLayout>(R.id.llCauseFinParti)
        val inflater = LayoutInflater.from(context)
        if(Joueur.isDead()){
            val itemView = inflater.inflate(R.layout.fin_parti_mort, llCauseFinParti, false)
            llCauseFinParti.addView(itemView)
        }else{
            val itemView = inflater.inflate(R.layout.fin_parti_victoire, llCauseFinParti, false)
            llCauseFinParti.addView(itemView)
        }




        val closebtn = dialog.findViewById<Button>(R.id.buttonFermer)
        closebtn.setOnClickListener {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
            dialog.dismiss()
        }

        dialog.show()



    }

}