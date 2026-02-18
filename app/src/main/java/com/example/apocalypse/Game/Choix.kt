package com.example.apocalypse.Game

class Choix {

    /* Class Choix:
    * But:  Pour un implementation futur le joueur aura à choisir entre deux actions
    * */
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