package com.example.apocalypse.Game

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