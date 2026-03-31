package com.example.apocalypse

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.LinearLayout
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.apocalypse.Game.Joueur

class DialogCredits(private val context: Context) {
    val dialog = Dialog(context)
    lateinit var  llCauseFinParti : LinearLayout
    fun show() {
        dialog.setContentView(R.layout.credit)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        val window = dialog.window
        if (window != null) {
            val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)
            windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())
            windowInsetsController.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

            window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
            window.setDimAmount(0.7f)

            // Taille du popup (90% largeur, 70% hauteur)
            val width = (context.resources.displayMetrics.widthPixels * 0.90).toInt()
            val height = (context.resources.displayMetrics.heightPixels * 0.70).toInt()
            window.setLayout(width, height)
        }

        // 2. Affiche le dialogue (plus besoin de LayoutInflater ni de llCauseFinParti)
        dialog.show()
    }
}

