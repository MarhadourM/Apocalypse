package com.example.apocalypse

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.apocalypse.Game.Joueur
import com.example.apocalypse.Game.Jour
import com.example.apocalypse.Game.Partie

class ChoixBonusView : BaseActivity()  {

    lateinit var ivCarteBonus1: ImageView
    lateinit var ivCarteBonus2: ImageView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.choix_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        Partie.currentContext = this
        ivCarteBonus1 = findViewById(R.id.ivCarteBonus1)
        ivCarteBonus2 = findViewById(R.id.ivCarteBonus2)
        // init attributs joueur
        tvPtsVie = findViewById(R.id.tvPtsVie)
        tvPtsHydra = findViewById(R.id.tvPtsHydra)
        tvPtsSasiete = findViewById(R.id.tvPtsSasiete)



    }

    //initialisation de la Page
    override fun onStart() {
        super.onStart()
        updateUI()
        displayCarte()
    }
    //Affichage des Cartes Bonus
    private fun displayCarte() {
        var j : Jour = Partie.currentJour
        var carte1 = j.getCartes()[0]
        var carte2 = j.getCartes()[1]

        ivCarteBonus1.setImageResource(carte1.getImageID())
        ivCarteBonus2.setImageResource(carte2.getImageID())



        ivCarteBonus1.setOnClickListener {
            j.appliquerChoix(carte1)
            createActionIntent(this, ActionView::class.java,carte1.getTitre())
        }
        ivCarteBonus2.setOnClickListener {
           j.appliquerChoix(carte2)
            createActionIntent(this, ActionView::class.java,carte2.getTitre())
        }


    }


}