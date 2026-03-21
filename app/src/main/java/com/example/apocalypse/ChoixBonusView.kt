package com.example.apocalypse

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.apocalypse.Game.Jour
import com.example.apocalypse.Game.Partie
import com.example.apocalypse.Game.Rarete
import eightbitlab.com.blurview.BlurView

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

    lateinit var  background: ImageView
    lateinit var tvjour: TextView
    val context = this

    var backgroudRareter = arrayOf(R.drawable.commun,R.drawable.rare,R.drawable.legendaire)




    @SuppressLint("MissingInflatedId")
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
        background = findViewById(R.id.background2)

        // init attributs joueur
        tvPtsVie = findViewById(R.id.progressBarVie3)
        tvPtsHydra = findViewById(R.id.progressBarHydra3)
        tvPtsSasiete = findViewById(R.id.progressBarSatiete3)

        tvjour = findViewById(R.id.tvJour)


        background.setImageResource(Partie.currentJour.getBackgroundRessources())
        val titleView = findViewById<BlurView>(R.id.glassTitle)
        val attributView = findViewById<BlurView>(R.id.glassAttribut2)
        val glassInstruction  = findViewById<BlurView>(R.id.glassInstruction)
        val glassCard1 =findViewById<BlurView>(R.id.glassCard1)
        val glassCard2 =findViewById<BlurView>(R.id.glassCard2)


        setupDynamicGlassmorphism(this,titleView,background, 5f , 153)
        setupDynamicGlassmorphism(this,attributView,background, 5f , 153)
        setupDynamicGlassmorphism(this,glassCard1,background,5f,153)
        setupDynamicGlassmorphism(this,glassCard2,background,5f,153)
        setupDynamicGlassmorphism(this,glassInstruction,background,5f,153)


    }

    override fun onStart() {
        super.onStart()
        displayAttributs()
        displayCarte()
        displayTvJour()
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
            updateUI()
            object : CountDownTimer(1000, 1000) {
                override fun onTick(millisUntilFinished: Long) {}
                override fun onFinish() {
                    createActionIntent(context, ActionView::class.java,carte1.getTitre())
                }
            }.start()

        }
        ivCarteBonus2.setOnClickListener {
            Partie.choixBonusFait()
            j.appliquerChoix(carte2)
            updateUI()
            object : CountDownTimer(1000, 1000) {
                override fun onTick(millisUntilFinished: Long) {}
                override fun onFinish() {
                    createActionIntent(context, ActionView::class.java,carte2.getTitre())
                }
            }.start()
        }


    }

    private fun displayTvJour(){
        val i = Partie.currentJourIndex +1
        val currentDay = "Jour $i"
        tvjour.text = currentDay
    }


}