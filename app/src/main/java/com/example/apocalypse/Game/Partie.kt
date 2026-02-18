package com.example.apocalypse.Game

import android.annotation.SuppressLint
import android.content.Context
import com.example.apocalypse.FinPartiDialog
import com.example.apocalypse.R
import kotlinx.coroutines.delay


@SuppressLint("StaticFieldLeak")
object Partie {

    lateinit var currentContext: Context
    private val journees: ArrayList<Jour>
    private val malusjournee: Modificateur

    var etatJeu : EtatJeu = EtatJeu.MENU
    private var currentJourIndex  : Int  = 0
     lateinit  var currentJour  : Jour
     private var currentActionIndex  : Int  = 1
    //current Action  est un Choix car il doit contenir le lancer de dés
     lateinit var currentAction  : Choix





    init{
        this.journees = ArrayList<Jour>()

        // malusjournee:  Dans le jeu la nuit est considere comme un malus
        malusjournee = Malus(
            "Malus Reveille",
            "Vous venez de vous reveillez à cause de la faim et et de la soif qui vous tiraillent le ventre",
             HashMap<Attribut, Int>().apply {
            this[Attribut.VIE] = 0
            this[Attribut.SATIETE] = 2
            this[Attribut.HYDRA] = 2
        }
            )
        initParti()
    }

    private fun initParti() {


        /* JOUR 1
        * Création des Actions 1
        * */
        val actionJC1 = Action(
            12, "Vous vous baladez dans la foret et vous tombez sur un abris de Fortune vous rentez dans la Grotte",
            Bonus("titre", "Vous avez trouvez de l'eau ",  HashMap<Attribut, Int>(3).apply {
                this[Attribut.VIE] = 0
                this[Attribut.SATIETE] = 0
                this[Attribut.HYDRA] = 10
        }),
            Malus(
                "titre",
                "Vous tombez sur un grotte toxique",
                HashMap<Attribut, Int>().apply {
                     this[Attribut.VIE] = 30
                     this[Attribut.SATIETE] = 0
                     this[Attribut.HYDRA] = 0
                 })
        )

        val actionJC2 = Action(
            6, "Dans la foret vous trouver des champignons que vous etudiez puis mangez	",
            Bonus(
                "titre",
                "Votre analyse semble bonne vous n'etes pas malade",
                 HashMap<Attribut, Int>().apply {
                     this[Attribut.VIE] = 0
                     this[Attribut.SATIETE] = 4
                     this[Attribut.HYDRA] = 0
                 }),
            Malus(
                "titre",
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
            Bonus("titre", "Creation d'un filtre ",  HashMap<Attribut, Int>().apply {
                this[Attribut.VIE] = 0
                this[Attribut.SATIETE] = 0
                this[Attribut.HYDRA] = 8
            }),
            Malus(
                "titre",
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
            Bonus("titre", "Extraction reussite",  HashMap<Attribut, Int>().apply {
                this[Attribut.VIE] = 5
                this[Attribut.SATIETE] = 0
                this[Attribut.HYDRA] = 0
            }),
            Malus(
                "titre",
                "echec de l'extraction, le spore c'est desintegrer il libere un substance toxique mais votre corps commence a s'adapter ",
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
        currentAction = currentJour.getAction(1)
    }

    fun finIntro(){
        etatJeu = EtatJeu.CHOIXBONUS
    }

    fun choixBonusFait(){
        etatJeu = EtatJeu.ACTION

    }

    //actionfinit  met à jour l'etat pour que la view sache quelle est le view suivante
    //TODO  mettre des bons commentaires

    private fun actionFinit() {
        //Si c'est la dernière Action du Jour
        if (currentActionIndex == 3){
            //si c'est le dernier Jour
            if(currentJourIndex == 3){
                declancherFindeparti()
            }else{
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

        val dialog = FinPartiDialog(currentContext)
        dialog.show()

    }

    private fun reset() {
        initParti()
        etatJeu = EtatJeu.MENU
    }


}



