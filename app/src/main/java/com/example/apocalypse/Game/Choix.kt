package com.example.apocalypse.Game

class Choix {


    private val action1: Action
    private val d: LancerDes


    constructor(action1: Action , des: LancerDes) {
        this.action1 = action1
        this.d = des
    }


    fun lancerlesDés(): Boolean {
        val total = d.lancer()
        return if (total >= action1.getCible()) {
            true
        } else {
            false
        }
    }


    fun getAction1(): Action {
        return action1
    }
}