package com.example.apocalypse

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.apocalypse.Game.Joueur
import com.example.apocalypse.Game.Jour
import com.example.apocalypse.Game.Partie
import com.example.apocalypse.Game.Rarete

/**
 * Écran de choix du bonus en début de jour.
 *
 * Affiche deux cartes bonus et applique celle choisie avant de passer à l'écran d'action.
 */
class ChoixBonusView : BaseActivity()  {

    lateinit var ivCarteBonus1: ImageView
    lateinit var ivCarteBonus2: ImageView
    lateinit var rareter1: ImageView
    lateinit var rareter2: ImageView

    var backgroudRareter = arrayOf(R.drawable.commun,R.drawable.rare,R.drawable.legendaire)




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.choix_view)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val controller = WindowInsetsControllerCompat(window, window.decorView)
        controller.hide(WindowInsetsCompat.Type.systemBars())
        controller.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

        Partie.currentContext = this

        ivCarteBonus1 = findViewById(R.id.ivCarteBonus1)
        ivCarteBonus2 = findViewById(R.id.ivCarteBonus2)
        rareter1 = findViewById(R.id.rarete1)
        rareter2 = findViewById(R.id.rarete2)

        // init attributs joueur
        tvPtsVie = findViewById(R.id.progressBarVie2)
        tvPtsHydra = findViewById(R.id.progressBarHydra2)
        tvPtsSasiete = findViewById(R.id.progressBarSatiete2)



    }

    override fun onStart() {
        super.onStart()
        updateUI()
        displayCarte()
    }
    /**Affichage des Cartes Bonus + Instantiation du OnClick*/
    private fun displayCarte() {
        var j : Jour = Partie.currentJour
        var carte1 = j.getCartes()[0]
        var carte2 = j.getCartes()[1]

        ivCarteBonus1.setImageResource(carte1.getImageID())
        ivCarteBonus2.setImageResource(carte2.getImageID())

        var rareterCarte1 = when(carte1.getRarete()){
            Rarete.COMMUN -> backgroudRareter[0]
            Rarete.LEGENDAIRE -> backgroudRareter[2]
            else -> backgroudRareter[1]

        }
        var rareterCarte2 = when(carte2.getRarete()){
            Rarete.COMMUN -> backgroudRareter[0]
            Rarete.LEGENDAIRE -> backgroudRareter[2]
            else -> backgroudRareter[1]

        }

        rareter1.setImageResource(rareterCarte1)
        rareter2.setImageResource(rareterCarte2)


        ivCarteBonus1.setOnClickListener {
            Partie.choixBonusFait()
            j.appliquerChoix(carte1)
            createActionIntent(this, ActionView::class.java,carte1.getTitre())
        }
        ivCarteBonus2.setOnClickListener {
            Partie.choixBonusFait()
            j.appliquerChoix(carte2)
            createActionIntent(this, ActionView::class.java,carte2.getTitre())
        }


    }


}