package com.example.apocalypse.Game

/**
 * Modificateur abstrait appliqué au joueur (bonus ou malus).
 *
 * Un modificateur possède :
 * - une description lisible pour le joueur
 * - une liste d'attributs à modifier (vie, satiété, hydratation) avec leur valeur de changement.
 *
 * Les sous-classes (Bonus, Malus, Carte) définissent la manière exacte
 * d'appliquer ces modifications au Joueur.
 */
abstract class Modificateur {

    private val desc: String
    var attributsModifier: Map<Attribut, Int>

/**    val basique  = HashMap<Attribut, Int>(3).apply {
*            this[Attribut.VIE] = 0
*            this[Attribut.SASIETE] = 0
*            this[Attribut.HYDRA] = 0
*           }
*/

    constructor(desc: String, attributsModifier: HashMap<Attribut, Int>) {
        this.desc = desc
        this.attributsModifier = attributsModifier
    }


    abstract fun appliquerModificateur()

    fun getDesc(): String {
        return desc
    }

    /**
     * Retourne les modifications apportées aux Attributs du Joueur
     */
    abstract fun getAttribut() : String








}


