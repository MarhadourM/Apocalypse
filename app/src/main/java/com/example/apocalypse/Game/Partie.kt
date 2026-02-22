package com.example.apocalypse.Game

import android.annotation.SuppressLint
import android.content.Context
import android.os.CountDownTimer
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.core.os.postDelayed
import com.example.apocalypse.FinPartiDialog
import com.example.apocalypse.R
import kotlinx.coroutines.delay
import java.util.logging.Handler



//Permet d'avoir un attribut static. Ici Context
@SuppressLint("StaticFieldLeak")
/**
 * Gère l'état global de la partie (jour courant, action en cours, fin de partie, etc.).
 * Singleton accessible depuis toutes les Activities.
 */
object Partie {

    lateinit var currentContext: Context
    private val journees: ArrayList<Jour>
    private val malusjournee: Modificateur

    var etatJeu : EtatJeu = EtatJeu.MENU
    private var currentJourIndex  : Int  = 0
     lateinit  var currentJour  : Jour
     var currentActionIndex  : Int  = 1

     lateinit var currentAction  : Choix
     //TODO A modifier pour teste quand les  regles seront implementers
     var isFirstPartie : Boolean = false



    /**
     * A la création de l'application
     *
     */

    init{
        this.journees = ArrayList<Jour>()

        // malusjournee:  Dans le jeu la nuit est considere comme un malus
        malusjournee = Malus(
            "Vous venez de vous reveillez à cause de la faim et et de la soif qui vous tiraillent le ventre",
             HashMap<Attribut, Int>().apply {
            this[Attribut.VIE] = 0
            this[Attribut.SATIETE] = 2
            this[Attribut.HYDRA] = 2
        }
            )
        initParti()
    }

    /**
     * Création des Jours, Actions, Cartes Bonus,
     */
    private fun initParti() {


        /* JOUR 1
        * Création des Actions 1
        * */
        val actionJC1 = Action(
            6, "Vous vous baladez dans la foret et vous tombez sur un abris de Fortune vous rentez dans la Grotte",
            Bonus( "Vous avez trouvez de l'eau ",  HashMap<Attribut, Int>(3).apply {
                this[Attribut.VIE] = 0
                this[Attribut.SATIETE] = 0
                this[Attribut.HYDRA] = 5
        }),
            Malus(
                "Vous tombez sur un grotte toxique",
                HashMap<Attribut, Int>().apply {
                     this[Attribut.VIE] = 5
                     this[Attribut.SATIETE] = 0
                     this[Attribut.HYDRA] = 0
                 })
        )

        val actionJC2 = Action(
            6, "Dans la foret vous trouver des champignons que vous etudiez puis mangez",
            Bonus(
                "Votre analyse semble bonne vous n'etes pas malade",
                 HashMap<Attribut, Int>().apply {
                     this[Attribut.VIE] = 0
                     this[Attribut.SATIETE] = 4
                     this[Attribut.HYDRA] = 0
                 }),
            Malus(
                "l'espece du champignion vous à échapper, vous avez une intoxication",
                 HashMap<Attribut, Int>().apply {
                     this[Attribut.VIE] = 4
                     this[Attribut.SATIETE] = 2
                     this[Attribut.HYDRA] = 0
                 })
        )

        val actionJC3 = Action(
            6,
            "Vous avez trouvez un champignon vous decidez de l'etudier,le champignion  semble avoir un proprieter filtrante",
            Bonus( "Creation d'un filtre ",  HashMap<Attribut, Int>().apply {
                this[Attribut.VIE] = 0
                this[Attribut.SATIETE] = 0
                this[Attribut.HYDRA] = 8
            }),
            Malus(
                "Creation d'un filtre mais vous buvez avec un troupeau d'elephants roses",
                 HashMap<Attribut, Int>().apply {
                     this[Attribut.VIE] = 3
                     this[Attribut.SATIETE] = 0
                     this[Attribut.HYDRA] = 5
                 })
        )
        val actionJC4 = Action(
            6,
            "Grace au fiole que vous avez sur vous vous recolter un spore  pour l'etudier si vous trouvez une ville",
            Bonus( "Extraction reussite",  HashMap<Attribut, Int>().apply {
                this[Attribut.VIE] = 5
                this[Attribut.SATIETE] = 0
                this[Attribut.HYDRA] = 0
            }),
            Malus(
                "echec de l'extraction,il est toxique mais votre corps s'y habitue ",
                 HashMap<Attribut, Int>().apply {
                     this[Attribut.VIE] = 2
                     this[Attribut.SATIETE] = 0
                     this[Attribut.HYDRA] = 0
                 })
        )
        // Création des Dés
        val a = De6()
        val b = De6()
        val d = ArrayList<De>()
        d.add(a)
        d.add(b)
        val lancer = LancerDes(d)

        //Ajout de actions dans des choix
        val choixJC1 = Choix(actionJC1,lancer)
        val choixJC2 = Choix(actionJC2,lancer)
        val choixJC3 = Choix(actionJC3,lancer)
        val choixJC4 = Choix(actionJC4,lancer)

        //Création des cartes Bonus pour le jour 1
        val carteJC1 = Carte(
            "Bouteille d'eau",
            "Vous avez un bouteille d'eau",
             HashMap<Attribut, Int>().apply {
                 this[Attribut.VIE] = 0
                 this[Attribut.SATIETE] = 0
                 this[Attribut.HYDRA] = 5
             },
            Rarete.COMMUN,
            R.drawable.cartebouteille
        )
        val carteJC2 = Carte(
            "Boite de Conserve",
            "Vous avez un boîte de conserve",
             HashMap<Attribut, Int>().apply {
                 this[Attribut.VIE] = 0
                 this[Attribut.SATIETE] = 5
                 this[Attribut.HYDRA] = 0
             },
            Rarete.COMMUN,
            R.drawable.carteconserve
        )

        val cartes = ArrayList<Carte>()

        cartes.add(carteJC1)
        cartes.add(carteJC2)

        val jour1 = Jour(choixJC1, choixJC2, choixJC3, choixJC4, cartes)

        journees.add(jour1)
        //ajout du jour actuelle
        currentJourIndex = 0
        currentJour = journees[0]

    }



    fun demarrerParti() {
        etatJeu = EtatJeu.ONSTART
        currentActionIndex = 1
        currentAction = currentJour.getAction(currentActionIndex)
    }

    fun finIntro(){
        etatJeu = EtatJeu.CHOIXBONUS
    }

    fun choixBonusFait(){
        etatJeu = EtatJeu.ACTION

    }

    /**
     * Applique les modifications nessesaires sur les currents Jour et Action.
     *
     * @param
     * @return Unit.
     *
     * Effets de bord :
     * - passe le jour suivant si c'était la dernière action
     * - appelle declancherFindeparti() si c'est la fin du jeu
     * - passe à l'action suivante si ce n'étais pas la dernière action du jour
     */

    private fun actionFinit() {
        //Si c'est la dernière Action du Jour
        if (currentActionIndex == 4){
            //si c'est le dernier Jour
            if(currentJourIndex == 0){
                declancherFindeparti()
            }else{
                Log.i("partie", currentJourIndex.toString())
                currentJourIndex ++
                currentJour = journees[currentJourIndex]
                currentActionIndex = 1
                currentAction = currentJour.getAction(currentActionIndex)
                //Application du Malus de la nuit
                malusjournee.appliquerModificateur()
                //Jour suivant = Carte bonus
                etatJeu = EtatJeu.CHOIXBONUS
            }
        }else{
            currentActionIndex++
            currentAction = currentJour.getAction(currentActionIndex)
            etatJeu = EtatJeu.ACTION
        }
    }


    /**
     * Applique le résultat d'un lancer de dés à l'action en cours.
     *
     * @param i Résultat du lancer (somme des dés).
     * @return true si le lancer atteint la cible de l'action (succès), false sinon.
     *
     * Effets de bord :
     * - applique le bonus ou le malus de l'action à l'état du joueur/monde
     * - appelle actionFinit() si la partie n'est pas déjà en état FINPARTI
     */
    fun lancéeDesFait(i : Int) : Boolean{
        val succes = i >= currentAction.getAction().getCible()
        if (succes){
            currentAction.getAction().getBonus().appliquerModificateur()
        }else{
            currentAction.getAction().getMalus().appliquerModificateur()
        }
        if (etatJeu!=EtatJeu.FINPARTI){
            actionFinit()
        }
        return succes
    }

    fun etatLancerDes(){
        etatJeu = EtatJeu.LANCERDES
    }


    fun declancherFindeparti() {

        etatJeu = EtatJeu.FINPARTI

        Toast.makeText(currentContext,  "FIN DE PARTI" , Toast.LENGTH_LONG).show()


        object : CountDownTimer(2000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                FinPartiDialog(currentContext).show()
            }
        }.start()



    }

     fun reset() {
        initParti()
        etatJeu = EtatJeu.MENU
        Joueur.reset()
    }


}



