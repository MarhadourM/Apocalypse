package com.example.apocalypse.Game
/**
 * Représente une Action que le Joueur va effectuer
 */
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