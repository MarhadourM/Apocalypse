package com.example.apocalypse.Game


object Partie {

    private val journees: ArrayList<Jour>
    private val malusjournee: Modificateur

    private var etatJeu : EtatJeu = EtatJeu.MENU
    private var currentJourIndex  : Int  = 0
     lateinit var currentJour  : Jour
     private var currentActionIndex  : Int  = 1
    //current Action  est un Choix car il doit contenir le lancer de dés
     lateinit var currentAction  : Choix





    init{
        this.journees = ArrayList<Jour>()
        malusjournee = Malus(
            "Malus Reveille",
            "Vous venez de vous reveillez à cause de la faim et et de la soif qui vous tiraillent le ventre",
             HashMap<Attribut, Int>().apply {
            this[Attribut.VIE] = 0
            this[Attribut.SASIETE] = 0
            this[Attribut.HYDRA] = 0
        }
            )
        initParti()
    }

    private fun initParti() {


        //Action pour la journée Champignion
        val actionJC1 = Action(
            6, "Renter dans la Grotte",
            Bonus("titre", "Vous avez trouvez de l'eau ",  HashMap<Attribut, Int>(3).apply {
                this[Attribut.VIE] = 0
                this[Attribut.SASIETE] = 0
                this[Attribut.HYDRA] = 0
        }),
            Malus(
                "titre",
                "Vous tombez sur un grotte toxique",
                HashMap<Attribut, Int>().apply {
                     this[Attribut.VIE] = 0
                     this[Attribut.SASIETE] = 0
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
                     this[Attribut.SASIETE] = 0
                     this[Attribut.HYDRA] = 0
                 }),
            Malus(
                "titre",
                "l'espece du champignion vous a échapper, vous avez une intoxication",
                 HashMap<Attribut, Int>().apply {
                     this[Attribut.VIE] = 0
                     this[Attribut.SASIETE] = 0
                     this[Attribut.HYDRA] = 0
                 })
        )

        val actionJC3 = Action(
            6,
            "Vous avez trouvez un champignon vous decidez de l'etudier,le champignion  semble avoir un proprieter filtrante",
            Bonus("titre", "Creation d'un filtre ",  HashMap<Attribut, Int>().apply {
                this[Attribut.VIE] = 0
                this[Attribut.SASIETE] = 0
                this[Attribut.HYDRA] = 0
            }),
            Malus(
                "titre",
                "Creation d'un filtre mais vous buvez avec un troupeau d'elephants rose",
                 HashMap<Attribut, Int>().apply {
                     this[Attribut.VIE] = 0
                     this[Attribut.SASIETE] = 0
                     this[Attribut.HYDRA] = 0
                 })
        )
        val actionJC4 = Action(
            6,
            "Grace au fiole que vous avez sur vous vous recolter un spore  pour l'etudier si vous trouvez une ville",
            Bonus("titre", "Extraction reussite",  HashMap<Attribut, Int>().apply {
                this[Attribut.VIE] = 0
                this[Attribut.SASIETE] = 0
                this[Attribut.HYDRA] = 0
            }),
            Malus(
                "titre",
                "echec de l'extraction, le spore c'est desintegrer il libere un substance toxique mais votre corps commence a s'adapter ",
                 HashMap<Attribut, Int>().apply {
                     this[Attribut.VIE] = 0
                     this[Attribut.SASIETE] = 0
                     this[Attribut.HYDRA] = 0
                 })
        )
        val a = De6()
        val b = De6()
        val d = ArrayList<De>()
        d.add(a)
        d.add(b)
        val lancer = LancerDes(d)

        val choixJC1 = Choix(actionJC1,lancer)
        val choixJC2 = Choix(actionJC2,lancer)
        val choixJC3 = Choix(actionJC3,lancer)
        val choixJC4 = Choix(actionJC4,lancer)

        val carteJC1 = Carte(
            "Hydratation",
            "Vous avez un bouteille d'eau",
             HashMap<Attribut, Int>().apply {
                 this[Attribut.VIE] = 0
                 this[Attribut.SASIETE] = 0
                 this[Attribut.HYDRA] = 0
             },
            Rarete.COMMUN
        )
        val carteJC2 = Carte(
            "Nouritture",
            "Vous avez un boîte de conserve",
             HashMap<Attribut, Int>().apply {
                 this[Attribut.VIE] = 0
                 this[Attribut.SASIETE] = 0
                 this[Attribut.HYDRA] = 0
             },
            Rarete.COMMUN
        )

        val cartes = ArrayList<Carte>()

        cartes.add(carteJC1)
        cartes.add(carteJC2)

        val jour1 = Jour(choixJC1, choixJC2, choixJC3, choixJC4, cartes)

        journees.add(jour1)
    }

    /**
     * Mauvaise idée
     * Donc creation ClASS ENUM ETAT_PARTI (MENU,CHOIXBONUS,ACTION,LANCERDES,FINDEPARTIE)
     * ce sont les activites qui vont faire changer le curseur  pour que le jeu ne se bloque pas
     * TODO Remettre en ordre les methodes
     *
     */


    fun demarrerParti() {
        etatJeu = EtatJeu.ONSTART
        currentJourIndex = 0
        currentJour = journees[0]
        currentActionIndex = 1
        currentAction = currentJour.getAction(currentActionIndex)
    }

    fun finIntro(){
        etatJeu = EtatJeu.CHOIXBONUS
    }

    fun choixBonusFait(){
        etatJeu = EtatJeu.ACTION

    }

    //actionfinit  met à jour l'etat pour que la view sache quelle est le view suivante

    private fun actionFinit() {
        if (currentActionIndex == 4){
            if(currentJourIndex == 3){
                declancherFindeparti()
            }else{
                currentJourIndex ++
                currentJour = journees[currentJourIndex]
                currentActionIndex = 1
                currentAction = currentJour.getAction(currentActionIndex)
                //Jour suivant  = Carte bonus
                etatJeu = EtatJeu.CHOIXBONUS
            }
        }else{
            currentActionIndex++
            currentAction = currentJour.getAction(currentActionIndex)
            etatJeu = EtatJeu.ACTION
        }
    }






    // GetCurrentAction permet au lancerDesSensor de lancer les des  la logique metiers reste dans lancéeDesFait ()
    fun getCurrentAction(): Choix {
        return currentAction
    }

    fun lancéeDesFait(i : Int){
        if (i >= currentAction.getAction().getCible()){
            currentAction.getAction().getBonus().appliquerModificateur()
        }else{
            currentAction.getAction().getMalus().appliquerModificateur()
        }
        if (etatJeu!=EtatJeu.FINPARTI){

        }

        actionFinit()

    }

    fun etatLancerDes(){
        etatJeu = EtatJeu.LANCERDES
    }


    fun declancherFindeparti() {
        etatJeu = EtatJeu.FINPARTI
        Joueur.reset()
        this.reset()
    }

    private fun reset() {
        initParti()
        etatJeu = EtatJeu.MENU
    }

}



