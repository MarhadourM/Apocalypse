package com.example.apocalypse.Game

/**
 * Représente un jour de jeu avec plusieurs choix d'action et des cartes associées.
 *
 * Un jour contient 4 choix possibles et une liste de 2 cartes à appliquer
 * selon le choix du joueur.
 */
class Jour {
    private val choix1: Choix
    private val choix2: Choix
    private val choix3: Choix
    private val choix4: Choix
    private val cartes: ArrayList<Carte>


    constructor (
        choix1: Choix,
        choix2: Choix,
        choix3: Choix,
        choix4: Choix,
        cartes: ArrayList<Carte>
    ) {
        this.choix1 = choix1
        this.choix2 = choix2
        this.choix3 = choix3
        this.choix4 = choix4
        this.cartes = cartes
    }

    /**
     * Applique le modificateur de la carte choisie.
     */
    fun appliquerChoix(carteChoisi: Carte) {
        if(carteChoisi == cartes[0]){
            cartes[0].appliquerModificateur()
        }else{
            cartes[1].appliquerModificateur()
        }

    }

    fun getCartes(): ArrayList<Carte> {
        return cartes
    }

    /**
     * Retourne le choix correspondant à l'index courant (1 à 4).
     */
    fun getAction(currentActionIndex: Int): Choix {
        when (currentActionIndex){
            1 -> return choix1
            2 -> return choix2
            3 -> return choix3
            else ->  return choix4
        }

    }
}