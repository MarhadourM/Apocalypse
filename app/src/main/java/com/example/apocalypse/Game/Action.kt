package com.example.apocalypse.Game

class Action {
    private  val cible : Int
    private val desc: String

    private val bonus: Modificateur
    private val malus: Modificateur


    constructor(cible: Int, desc: String, bonus: Modificateur, malus: Modificateur) {
        this.cible = cible
        this.desc = desc
        this.bonus = bonus
        this.malus = malus
    }


    private fun appliquerBonus() {
        bonus.appliquerModificateur()
    }


    private fun appliquerMalus() {
        malus.appliquerModificateur()
    }


    fun afficherDescription() {
        //TODO  Affiche la description de l'action + la description du bonus et malus
    }

    fun getCible(): Int {
        return cible
    }


    fun getDesc(): String {
        return desc
    }


    fun getBonus(): Modificateur {
        return bonus
    }


    fun getMalus(): Modificateur {
        return malus
    }
}