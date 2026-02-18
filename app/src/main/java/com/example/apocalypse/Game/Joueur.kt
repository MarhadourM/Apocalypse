package com.example.apocalypse.Game

object Joueur {
    // c'est un singleton

    private var ptsVie: Int = 30
    private var ptsHydra: Int = 20
    private var ptsSatiete: Int = 20
    private var nom: String = "Partik"



    fun isDead(): Boolean = ptsVie <= 0

    fun getPtsVie() = ptsVie
    fun setPtsVie(ptsVie: Int) {
        if (ptsVie < 0) {
            Partie.declancherFindeparti()
        }else if (ptsVie>30){
            this.ptsVie = 30
        } else {
            this.ptsVie = ptsVie
        }
    }

    fun getPtsHydra() = ptsHydra
    fun setPtsHydra(ptsHydra: Int) {
        if (ptsHydra < 0) {
            this.ptsHydra = 0
            this.setPtsVie(this.ptsVie - 2)
        }else if (ptsHydra>30){
            this.ptsHydra = 30
        }else {
            this.ptsHydra = ptsHydra
        }
    }

    fun getPtsSatiete() = ptsSatiete
    fun setPtsSatiete(ptsSatiete: Int) {
        if (ptsSatiete < 0) {
            this.ptsSatiete = 0
            this.setPtsVie(this.ptsVie - 2)
        }else if (ptsSatiete>30){
            this.ptsSatiete = 30
        } else {
            this.ptsSatiete = ptsSatiete
        }
    }


    fun getNom() = nom
    fun setNom(nom: String) {
        this.nom = nom
    }

    fun reset() {
        this.setPtsVie(30)
        this.setPtsHydra(20)
        this.setPtsSatiete(20)
    }
}
