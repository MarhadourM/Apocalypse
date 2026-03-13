package com.example.apocalypse.Game

import android.annotation.SuppressLint
import android.content.Context
import android.os.CountDownTimer
import android.util.Log
import android.widget.Toast
import com.example.apocalypse.ActionView
import com.example.apocalypse.FinPartiDialog


//Permet d'avoir un attribut static. Ici Context
@SuppressLint("StaticFieldLeak")
/**
 * Gère l'état global de la partie (jour courant, action en cours, fin de partie, etc.).
 * Singleton accessible depuis toutes les Activities.
 */
object Partie {

    lateinit var currentContext: Context
    private var journees: ArrayList<Jour>
    private val malusjournee: Modificateur

    var etatJeu : EtatJeu = EtatJeu.MENU
    private var currentJourIndex  : Int  = 0
    lateinit  var currentJour  : Jour
    var currentActionIndex  : Int  = 1

    val cartePicker  = CartePicker()

    lateinit var currentAction  : Choix
    var isFirstPartie : Boolean = true





    /**
     * A la création de l'application
     *
     */

    init{
        this.journees = ArrayList<Jour>()
        // malusjournee:  Dans le jeu la nuit est considere comme un malus
        malusjournee = Malus(
            "Vous venez de vous reveiller à cause de la faim et et de la soif qui vous tiraillent le ventre",
             HashMap<Attribut, Int>().apply {
            this[Attribut.VIE] = 0
            this[Attribut.SATIETE] = 5
            this[Attribut.HYDRA] = 5
        }
            )
        initParti()

    }

    /**
     * Création des Jours, Actions, Cartes Bonus,
     */
    private fun initParti() {
        this.journees = ArrayList<Jour>()



        // ==========================================
        // JOURNEE CHAMPGNION
        // ==========================================
        val actionJC1 = Action(
            8, "Vous vous baladez dans la fôret et vous tombez sur un abris de fortune. Vous rentez dans la Grotte",
            Bonus( "Vous avez trouvez de l'eau ",  HashMap<Attribut, Int>(3).apply {
                this[Attribut.VIE] = 0
                this[Attribut.SATIETE] = 0
                this[Attribut.HYDRA] = 5
        }),
            Malus(
                "Vous tombez sur un grotte toxique",
                HashMap<Attribut, Int>().apply {
                     this[Attribut.VIE] = 8
                     this[Attribut.SATIETE] = 0
                     this[Attribut.HYDRA] = 0
                 })
        )

        val actionJC2 = Action(
            6, "Dans la fôret, vous trouvez des champignons que vous étudiez puis mangez",
            Bonus(
                "Votre analyse semble bonne vous n'êtes pas malade",
                 HashMap<Attribut, Int>().apply {
                     this[Attribut.VIE] = 0
                     this[Attribut.SATIETE] = 4
                     this[Attribut.HYDRA] = 0
                 }),
            Malus(
                "l'espèce du champignon vous a échappé, vous avez une intoxication",
                 HashMap<Attribut, Int>().apply {
                     this[Attribut.VIE] = 4
                     this[Attribut.SATIETE] = 2
                     this[Attribut.HYDRA] = 0
                 })
        )

        val actionJC3 = Action(
            6,
            "Vous avez trouvé un champignon vous décidez de l'étudier. Le champignon semble avoir une propriété filtrante",
            Bonus( "Création d'un filtre ",  HashMap<Attribut, Int>().apply {
                this[Attribut.VIE] = 0
                this[Attribut.SATIETE] = 0
                this[Attribut.HYDRA] = 8
            }),
            Malus(
                "Création d'un filtre mais vous buvez avec un troupeau d'éléphants roses",
                 HashMap<Attribut, Int>().apply {
                     this[Attribut.VIE] = 3
                     this[Attribut.SATIETE] = 0
                     this[Attribut.HYDRA] = 5
                 })
        )
        val actionJC4 = Action(
            6,
            "Grâce aux fioles que vous avez sur vous, vous récoltez une spore pour l'étudier si vous trouvez une ville",
            Bonus( "Extraction reussite",  HashMap<Attribut, Int>().apply {
                this[Attribut.VIE] = 5
                this[Attribut.SATIETE] = 0
                this[Attribut.HYDRA] = 0
            }),
            Malus(
                "Echec de l'extraction, il est toxique mais votre corps s'y habitue ",
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

        val jour1 = Jour(choixJC1, choixJC2, choixJC3, choixJC4,cartePicker.pickCartes())
        journees.add(jour1)

        // ==========================================
        // VILLE ABANDONNÉE - ARRIVÉE (VA)
        // ==========================================

        val actionVA1 = Action(
            6,
            "Vous arrivez aux abords d'une ville abandonnée. Vous décidez d'y entrer.",
            Bonus(
                "Vous trouvez une source d'eau et explorez la ville.",
                HashMap<Attribut, Int>().apply {
                    this[Attribut.VIE] = 0
                    this[Attribut.SATIETE] = 0
                    this[Attribut.HYDRA] = 5
                }
            ),
            Malus(
                "L'entrée est difficile, vous vous blessez sur des débris.",
                HashMap<Attribut, Int>().apply {
                    this[Attribut.VIE] = 8
                    this[Attribut.SATIETE] = 0
                    this[Attribut.HYDRA] = 0
                }
            )
        )

        val actionVA2 = Action(
            8,
            "Vous repérez un immeuble qui semble intact et commencez à le fouiller.",
            Bonus(
                "Vous trouvez des objets  qui vont vous être utiles et un sachet lyophilisé ",
                HashMap<Attribut, Int>().apply {
                    this[Attribut.VIE] = 0
                    this[Attribut.SATIETE] = 3
                    this[Attribut.HYDRA] = 0
                }
            ),
            Malus(
                "L'immeuble est vide et la fouille vous a ouvert l'appétit pour rien.",
                HashMap<Attribut, Int>().apply {
                    this[Attribut.VIE] = 0
                    this[Attribut.SATIETE] = 5
                    this[Attribut.HYDRA] = 0
                }
            )
        )

        val actionVA3 = Action(
            12,
            "Vous trouvez un vieil émetteur radio. Vous tentez de le réparer.",
            Bonus(
                "Réparation réussie. Il va falloir être prudent avec la recherche de signal ",
                HashMap<Attribut, Int>().apply {
                    this[Attribut.VIE] = 0
                    this[Attribut.SATIETE] = 0
                    this[Attribut.HYDRA] = 0
                }
            ),
            Malus(
                "Vous avez passé des heures à transpirer sur les circuits, en récoltant au passage quelques coup de jus.",
                HashMap<Attribut, Int>().apply {
                    this[Attribut.VIE] = 5
                    this[Attribut.SATIETE] = 5
                    this[Attribut.HYDRA] = 5
                }
            )
        )

        val actionVA4 = Action(
            10,
            "La nuit tombe. Vous devez décider de restez caché",
            Bonus(
                "Vous trouvez une bonne planque. Fin de la journée en sécurité.",
                HashMap<Attribut, Int>().apply {
                    this[Attribut.VIE] = 0
                    this[Attribut.SATIETE] = 2
                    this[Attribut.HYDRA] = 2
                }
            ),
            Malus(
                "Vous tombez nez à nez avec un animal sauvage affamé. C'était la tanière d'un chien toxique",
                HashMap<Attribut, Int>().apply {
                    this[Attribut.VIE] = 10
                    this[Attribut.SATIETE] = 0
                    this[Attribut.HYDRA] = 0
                }
            )
        )

        // Création des choix associés au lancer de dés existant
        val choixVA1 = Choix(actionVA1, lancer)
        val choixVA2 = Choix(actionVA2, lancer)
        val choixVA3 = Choix(actionVA3, lancer)
        val choixVA4 = Choix(actionVA4, lancer)

        // Création du Jour et ajout à la liste
        val jourVA = Jour(choixVA1, choixVA2, choixVA3, choixVA4, cartePicker.pickCartes())
        journees.add(jourVA)


// ==========================================
// VILLE ABANDONNÉE - EXPLORATION AVANCÉE (VASuite)
// ==========================================

        val actionVASuite1 = Action(
            10,
            "Vous vous enfoncez plus profondément dans la ville pour continuer à fouiller les maisons.",
            Bonus(
                "Vous trouvez des réserves d'eau. La ville est explorée.",
                HashMap<Attribut, Int>().apply {
                    this[Attribut.VIE] = 0
                    this[Attribut.SATIETE] = 0
                    this[Attribut.HYDRA] = 10
                }
            ),
            Malus(
                "La zone est instable, un plancher s'effondre sous votre poids.",
                HashMap<Attribut, Int>().apply {
                    this[Attribut.VIE] = 10
                    this[Attribut.SATIETE] = 0
                    this[Attribut.HYDRA] = 0
                }
            )
        )

        val actionVASuite2 = Action(
            9,
            "Un grand immeuble attire votre attention. La fouille s'annonce complexe.",
            Bonus(
                "De la nourriture en abondance",
                HashMap<Attribut, Int>().apply {
                    this[Attribut.VIE] = 0
                    this[Attribut.SATIETE] = 7
                    this[Attribut.HYDRA] = 0
                }
            ),
            Malus(
                "Vous fouillez chaque étage mais ne trouvez rien. L'effort vous a affamé.",
                HashMap<Attribut, Int>().apply {
                    this[Attribut.VIE] = 0
                    this[Attribut.SATIETE] = 10
                    this[Attribut.HYDRA] = 0
                }
            )
        )

        val actionVASuite3 =  Action(
            8,
        "Vous repérez une porte blindée entrouverte dans le sous-sol. Vous tentez de l'ouvrir.",
        Bonus(
            "Succès ! C'est une ancienne cache de survie. Vous trouvez une gourde militaire et des vivres.",
            HashMap<Attribut, Int>().apply {
                this[Attribut.VIE] = 0
                this[Attribut.SATIETE] = 10
                this[Attribut.HYDRA] = 15
            }
        ),
        Malus(
            "Une détonation assourdissante vous blesse et ruine vos tympans.",
            HashMap<Attribut, Int>().apply {
                this[Attribut.VIE] = 15
                this[Attribut.SATIETE] = 0
                this[Attribut.HYDRA] = 0
            }
        )
        )


        val actionVASuite4 =  Action(
            10,
            "Le ciel vire au noir. Un violent orage éclate, vous vous êtes trop éloignés du centre de la ville ",
            Bonus(
                "Vous trouvez un abri en hauteur. Vous profitez de l'orage pour remplir vos réserves d'eau avant de dormir.",
                HashMap<Attribut, Int>().apply {
                    this[Attribut.VIE] = 0
                    this[Attribut.SATIETE] = 0
                    this[Attribut.HYDRA] = 5
                }
            ),
            Malus(
                "L'orage transforme les rues en torrents glacés. Vous passez une nuit affreuse, trempé jusqu'aux os.",
                HashMap<Attribut, Int>().apply {
                    this[Attribut.VIE] = 5
                    this[Attribut.SATIETE] = 5
                    this[Attribut.HYDRA] = 0
                }
            )
        )


            // Création des choix associés au lancer de dés existant
        val choixVASuite1 = Choix(actionVASuite1, lancer)
        val choixVASuite2 = Choix(actionVASuite2, lancer)
        val choixVASuite3 = Choix(actionVASuite3, lancer)
        val choixVASuite4 = Choix(actionVASuite4, lancer)

        // Création du Jour et ajout à la liste
        val jourVASuite = Jour(choixVASuite1, choixVASuite2, choixVASuite3, choixVASuite4, cartePicker.pickCartes())
        journees.add(jourVASuite)

        // ==========================================
        // HÔPITAL MILITAIRE (HM)
        // ==========================================

        val actionHM1 = Action(
            8,
            "Vous arrivez devant un grand complexe. Vous tentez de franchir le grillage sécurisé.",
            Bonus(
                "Vous passez le grillage sans encombres. L'hôpital est à vous.",
                HashMap<Attribut, Int>().apply {
                    this[Attribut.VIE] = 0
                    this[Attribut.SATIETE] = 0
                    this[Attribut.HYDRA] = 0
                }
            ),
            Malus(
                "Vous vous coupez profondément sur les barbelés en passant.",
                HashMap<Attribut, Int>().apply {
                    this[Attribut.VIE] = 8
                    this[Attribut.SATIETE] = 0
                    this[Attribut.HYDRA] = 0
                }
            )
        )

        val actionHM2 = Action(
            12,
            "Vous forcez la porte de la salle des médicaments à la recherche de soins.",
            Bonus(
                "Vous trouvez du matériel médical intact ",
                HashMap<Attribut, Int>().apply {
                    this[Attribut.VIE] = 30
                    this[Attribut.SATIETE] = 0
                    this[Attribut.HYDRA] = 0
                }
            ),
            Malus(
                "Toute à déja été emporter avant la tempête",
                HashMap<Attribut, Int>().apply {
                    this[Attribut.VIE] = 10
                    this[Attribut.SATIETE] = 0
                    this[Attribut.HYDRA] = 0
                }
            )
        )

        val actionHM3 = Action(
            6,
            "Vous fouillez les vestiaires pour trouver des vêtements de rechange propres.",
            Bonus(
                "Vous trouvez une tenue militaire robuste qui vous protège mieux.",
                HashMap<Attribut, Int>().apply {
                    this[Attribut.VIE] = 1
                    this[Attribut.SATIETE] = 0
                    this[Attribut.HYDRA] = 0
                }
            ),
            Malus(
                "Les casiers sont vides ou condamnés, vous perdez du temps pour rien.",
                HashMap<Attribut, Int>().apply {
                    this[Attribut.VIE] = 0
                    this[Attribut.SATIETE] = 0
                    this[Attribut.HYDRA] = 0
                }
            )
        )

        val actionHM4 = Action(
            10,
            "Il est temps de procéder à l'évacuation de la zone.",
            Bonus(
                "Évacuation réussie, vous quittez les lieux discrètement.",
                HashMap<Attribut, Int>().apply {
                    this[Attribut.VIE] = 0
                    this[Attribut.SATIETE] = 0
                    this[Attribut.HYDRA] = 0
                }
            ),
            Malus(
                " Vous avez déclenché l'alarme de l'hôpital militaire en sortant.",
                HashMap<Attribut, Int>().apply {
                    this[Attribut.VIE] = 10
                    this[Attribut.SATIETE] = 0
                    this[Attribut.HYDRA] = 0
                }
            )
        )

// Création des choix associés au lancer de dés existant
        val choixHM1 = Choix(actionHM1, lancer)
        val choixHM2 = Choix(actionHM2, lancer)
        val choixHM3 = Choix(actionHM3, lancer)
        val choixHM4 = Choix(actionHM4, lancer)

// Création du Jour et ajout à la liste
        val jourHM = Jour(choixHM1, choixHM2, choixHM3, choixHM4, cartePicker.pickCartes())
        journees.add(jourHM)




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
            if(currentJourIndex == 3){
                declancherFindeparti()
            }else{
                Log.i("partie", currentJourIndex.toString())

                currentJourIndex ++
                currentJour = journees[currentJourIndex]
                currentActionIndex = 1
                currentAction = currentJour.getAction(currentActionIndex)
                //Application du Malus de la nuit
                object : CountDownTimer(800, 10) {
                    override fun onTick(millisUntilFinished: Long) {}
                    override fun onFinish() {
                        malusjournee.appliquerModificateur()
                        Toast.makeText(currentContext,  "Fin de journée" , Toast.LENGTH_LONG).show()
                    }
                }.start()
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
        Sauvgarde.addSauvNbParties(currentContext)


        Toast.makeText(currentContext,  "FIN DE PARTI" , Toast.LENGTH_LONG).show()

        //Attend 2 secondes avant d'afficher l'ecran de fin
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
    fun updateIsFirstPartie(){
        if (Sauvgarde.getSauvNbPartie(currentContext) >=1){
            isFirstPartie = false
        }else{
            isFirstPartie = true
        }
    }


}



