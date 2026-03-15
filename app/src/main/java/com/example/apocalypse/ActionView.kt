package com.example.apocalypse


import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.apocalypse.Game.Partie
import com.example.apocalypse.Game.Partie.currentContext
import com.example.apocalypse.R.*
/**
 * Écran qui affiche l'action en cours
 *
 * Montre la description de l'action, sa cible,
 * puis envoie vers l'écran de lancer de dés.
 */
class ActionView: BaseActivity() {

    lateinit var tvChoixCarteBonus: TextView
    lateinit var buttonLancerDes : Button


    lateinit var tvDescriptionAction : TextView
    lateinit var tvCible : TextView
    lateinit var tvModificateurBonus : TextView

    lateinit var tvModificateurMalus : TextView
    lateinit var background : ImageView



    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(layout.action_view)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val controller = WindowInsetsControllerCompat(window, window.decorView)
        controller.hide(WindowInsetsCompat.Type.systemBars())
        controller.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        Partie.currentContext=this
        tvChoixCarteBonus = findViewById(id.tvChoixCarteBonus)
        buttonLancerDes  = findViewById(id.buttonLancerDes)

        tvPtsVie = findViewById(id.progressBarVie3)
        tvPtsSasiete = findViewById(id.progressBarSatiete3)
        tvPtsHydra = findViewById(id.progressBarHydra3)
        //init des TextView Lié à l'action
        tvDescriptionAction = findViewById(id.tvDescriptionAction)
        tvCible = findViewById(id.tvCible)
        //init des TextView Lié au Bonus et au Malus de l'action
        tvModificateurBonus = findViewById(R.id.tvModificateurBonus)
        tvModificateurMalus = findViewById(R.id.tvModificateurMalus)

        background = findViewById(R.id.background3)

        background.setImageResource(Partie.currentJour.getBackgroundRessources())


        //si c'est la Première action, Affiche le choix qui a été fait
        val choix = intent.getStringExtra("Choix")
        if(Partie.currentActionIndex == 1){
            setTextTvChoixCarte(choix)
        }else{
            tvChoixCarteBonus.visibility = View.GONE
        }


        object : CountDownTimer(3000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                buttonLancerDes.visibility = View.VISIBLE
            }
        }.start()


        buttonLancerDes.setOnClickListener {
            //Modification de l'Etat de la Partie en LancesDes et ouvre l'écran associé
            Partie.etatLancerDes()
            createIntent(this, LancerDesSensor::class.java)
        }


    }
     fun setTextTvChoixCarte(txt: String?){
         tvChoixCarteBonus.visibility = View.VISIBLE
        tvChoixCarteBonus.text = "Vous avez Choisi "+txt

    }

    override fun onStart() {
        super.onStart()
        displayAttributs()
        displayAction()
    }


    private fun displayAction() {
        tvDescriptionAction.text= Partie.currentAction.getAction().getDesc()
        var cible = " Cible - " + Partie.currentAction.getAction().getCible().toString()
        tvCible.text = cible

        tvModificateurMalus.text = Partie.currentAction.getAction().getMalus().getAttribut()
        tvModificateurBonus.text = Partie.currentAction.getAction().getBonus().getAttribut()


    }

}