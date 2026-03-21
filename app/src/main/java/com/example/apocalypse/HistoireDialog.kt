package com.example.apocalypse

import android.app.Dialog
import android.content.Context
import android.view.WindowManager
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

class HistoireDialog(private val context: Context) {

    private val dialog = Dialog(context)
    private lateinit var llHistoireContent: ConstraintLayout
    private val pagesLore = listOf(
        // Page 1
        "2097. Cela fait vingt ans que le Déluge a commencé.\n\nUne tempête de pluie acide a ravagé la surface de la Terre. Pour survivre, l'humanité s'est terrée dans des abris antiatomiques.\n\nVous incarnez Patrik, un homme condamné pour vol, devenu \"Testeur\" pour la Brigade d’Exploration. Votre peine consiste à remonter à la surface pour vérifier si le monde du dehors est de nouveau habitable. Une tâche mortelle qui détruit votre santé à petit feu.",

        // Page 2
        "Si Patrik accepte de respirer ce poison, c'est pour une seule raison.\n\nSon fils est atteint d'une maladie incurable, causée par l'air vicié des souterrains. Chaque expédition à la surface permet de gagner de quoi soulager ses souffrances.\n\nRécemment, Patrik a remarqué un changement. L'air extérieur semble moins corrosif. Il a même aperçu la pousse d'un champignon. Une information qu'il garde secrète dans ses rapports, préparant en silence une évasion avec d'autres condamnés.",

        // Page 3
        "Mais le temps a joué contre lui.\n\nLe 4 avril, son fils succombe à la maladie, à peine 16 jours après la découverte de la plante.\n\nN'ayant plus rien à perdre, Patrik prend son masque et remonte à la surface sans un mot. Personne n'essaie de le retenir. Les autorités parlent d'un suicide. Pourtant, des mois plus tard, les prisonniers qui ont suivi ses plans découvrent ses traces de pas, un vieux masque fendu... et un champignon intact.\n\nC'est ici que votre histoire commence."
    )
    private  var indexPageActuelle  = 0


    fun show() {
        // 1. Définition du layout principal du popup
        dialog.setContentView(R.layout.histoire_dialog)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        // 2. Configuration de l'affichage (Plein écran, Fond sombre)
        val window = dialog.window
        if (window != null) {
            val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)
            windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())
            windowInsetsController.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

            window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
            window.setDimAmount(0.7f)


            val width = (context.resources.displayMetrics.widthPixels * 0.80).toInt()
            val height = (context.resources.displayMetrics.heightPixels * 0.80).toInt()
            window.setLayout(width, height)

        }

        val tvHistoire  = dialog.findViewById<TextView>(R.id.tvHistoire)
        val buttonSuivant = dialog.findViewById<ImageButton>(R.id.btnSuivantHistoire)

        tvHistoire.text = pagesLore[indexPageActuelle]


        buttonSuivant.setOnClickListener {
            indexPageActuelle++

            if (indexPageActuelle < pagesLore.size) {
                tvHistoire.text = pagesLore[indexPageActuelle]

            } else {
                dialog.dismiss()
            }
        }

            // 3. Liaison avec les éléments du Layout
            llHistoireContent = dialog.findViewById(R.id.llHistoireContent)



            dialog.show()
        }

}