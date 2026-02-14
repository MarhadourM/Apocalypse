package com.example.apocalypse.Game


class LancerDes {
    private var des = ArrayList<De>()

    constructor(des: ArrayList<De>) {
        this.des = des
    }

    fun lancer(): Int {
        var result = 0
        for (de in des) {
            result += de.lancer()
            println()
        }
        return result
    }
}