package com.example.apocalypse.Game


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

    fun appliquerChoix(carteChoisi: Carte) {
        if(carteChoisi == cartes[0]){
            cartes[0].appliquerModificateur()
            Partie.isChoixFaitForCurrentJour(true)
        }else{
            cartes[1].appliquerModificateur()
            Partie.isChoixFaitForCurrentJour(true)
        }

    }

    fun getCartes(): ArrayList<Carte> {
        return cartes
    }
}