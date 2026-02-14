package com.example.apocalypse

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.apocalypse.Game.*
import kotlin.apply

class MainActivity : AppCompatActivity() {
    lateinit var buttonSurvivre: Button
    lateinit var partie: Partie

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        buttonSurvivre = findViewById(R.id.buttonSurvivre)

        buttonSurvivre.setOnClickListener {
            //Partie.demarrerParti()
            val intent = Intent(this, ChoixView::class.java)
            startActivity(intent)
        }

        testGameLog()

    }

    private fun testGameLog (){
        //Log.i("testGameConsole","")
//TEST LANCER DE DES
        val a = De6()
        val b = De6()
        val d = ArrayList<De>()
        d.add(a)
        d.add(b)
        val lancer = LancerDes(d)
        for (i in 1..25){
            Log.i("testGameConsole","lancer  = "+lancer.lancer())
        }

//TEST MODIFICATEUR
//        val basique  = HashMap<Attribut, Int>(3).apply {
//            this[Attribut.VIE] = 0
//            this[Attribut.SASIETE] = 0
//            this[Attribut.HYDRA] = 0
//        }


//TEST JOUEUR
//    val joueur  = Joueur()
//        Log.i("testGameConsole","Nom du joueur :"+joueur.getNom() +" ptsVie"+joueur.getPtsVie())
    }
}



