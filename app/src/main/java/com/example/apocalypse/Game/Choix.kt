package com.example.apocalypse.Game

/**
 * Permet au joueur de choisir une action associée à un lancer de dés.
 *
 * @property action Action liée à ce choix.
 * @property d Lancer de dés utilisé pour résoudre l'action.
 */
class Choix {

    private val action: Action
    private val d: LancerDes


    constructor(action1: Action , des: LancerDes) {
        this.action = action1
        this.d = des
    }

    fun lancerlesDés(): Int {
        return d.lancer()
    }

    fun getAction(): Action {
        return action
    }
}