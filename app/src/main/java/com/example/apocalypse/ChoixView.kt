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
import com.example.apocalypse.Game.Partie

class ChoixView : AppCompatActivity()  {

    lateinit var ivCarteBonus1: ImageView
    lateinit var ivCarteBonus2: ImageView

    lateinit var tvPtsVie : TextView
    lateinit var tvPtsHydra : TextView
    lateinit var tvPtsSasiete : TextView




    override fun onStart() {
        super.onStart()
        updateUI()
        displayCarte()
    }

    private fun displayCarte() {
        //val cartes = Partie.getCurrentJour().getCartes()
        ivCarteBonus1.setImageResource(R.drawable.ic_launcher_background) //R.drawable.cartes[0].getImageId
        ivCarteBonus2.setImageResource(R.drawable.ic_launcher_background)

        ivCarteBonus1.setOnClickListener {
            //Partie.getCurrentJour().appliquerChoix(cartes[0])
            startActivity(createActionIntent("Carte bonus1"))//cartes[0].getTitre
        }
        ivCarteBonus2.setOnClickListener {
            //Partie.getCurrentJour().appliquerChoix(cartes[0])
            startActivity(createActionIntent("Carte bonus2"))//cartes[1].getTitre
        }


    }

    //mise a jour des attributs du Joueur
    private fun updateUI() {
        tvPtsVie.text ="Vie :"+ Joueur.getPtsHydra().toString()
        tvPtsHydra.text = "Hydratation : "+ Joueur.getPtsHydra().toString()
        tvPtsSasiete.text = "Nourriture : "+ Joueur.getPtsSatiete().toString()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.choix_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        ivCarteBonus1 = findViewById(R.id.ivCarteBonus1)
        ivCarteBonus2 = findViewById(R.id.ivCarteBonus2)
        // init attributs joueur
        tvPtsVie = findViewById(R.id.tvPtsVie)
        tvPtsHydra = findViewById(R.id.tvPtsHydra)
        tvPtsSasiete = findViewById(R.id.tvPtsSasiete)



        ivCarteBonus1.setOnClickListener {

            startActivity(createActionIntent("Carte bonus1"))
        }
        ivCarteBonus2.setOnClickListener {
            startActivity(createActionIntent("Carte bonus2"))
        }


    }
    private fun createActionIntent(choix: String): Intent {
        return Intent(this, ActionView::class.java).apply { this.putExtra("Choix",choix)
        }
    }

}