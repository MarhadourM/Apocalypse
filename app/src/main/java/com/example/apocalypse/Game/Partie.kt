package com.example.apocalypse.Game


object Partie {

    private val journees: ArrayList<Jour>
    private val malusjournee: Modificateur
    private var currentJour : Jour? =null
    private var isChoixFaitForCurrentJour : Boolean = false




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
     * TODO Reflechir a comment gerer la fin d'un jour (idée : plus de Choix dans Jour)
     *
     */


    fun demarrerParti() {
        val i = 1 //i commence toujours a 1 car on considere que  il y a forcement un element dans la liste
        while (Joueur.isDead() && journees.size != i) {
            currentJour = journees.get(i -1)

            //wait choix
            while (!isChoixFaitForCurrentJour){

            }

            /*
			 * wait le choix
			 * 		applique le choix
			 * boucle les  4 choix/actions
			 * 		affichage choix
			 * 		wait lancer de dés(lancerlesdes dans choix)
			 * 		applique le bonus si true
			 * 		sinon applique le malus si ca renvoie false lancer la fin de parti + break
			 *
			 * i++;
			 * */
        }


        //TODO fin de la parti dead + victoire
    }

    fun declancherFindeparti() {
        //affichage  du la fin
        Joueur.reset()
        this.reset()
    }

    private fun reset() {
        initParti()
    }

    fun getCurrentJour(): Jour = requireNotNull(currentJour) { "Jour pas initialisé !" }

    fun isChoixFaitForCurrentJour(b: Boolean){
        this.isChoixFaitForCurrentJour = b
    }
    fun getisChoixFaitForCurrentJour(): Boolean =this.isChoixFaitForCurrentJour
}


