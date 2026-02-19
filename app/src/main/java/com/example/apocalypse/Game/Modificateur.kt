package com.example.apocalypse.Game


abstract class Modificateur {

    private val desc: String
    var attributsModifier: Map<Attribut, Int>

//    val basique  = HashMap<Attribut, Int>(3).apply {
//            this[Attribut.VIE] = 0
//            this[Attribut.SASIETE] = 0
//            this[Attribut.HYDRA] = 0
//        }

    /*
    * Class Abstract Modificateur
    * But: Permet de modifier les attributs du Joueur
    * */
    constructor(desc: String, attributsModifier: HashMap<Attribut, Int>) {
        this.desc = desc
        this.attributsModifier = attributsModifier
    }


    abstract fun appliquerModificateur()


    fun getDesc(): String {
        return desc
    }


    abstract fun getAttribut() : String








}


