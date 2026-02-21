package com.example.apocalypse.Game
/**
 * Malus appliqué au Joueur quand le lancer de dés est en dessous de la Cible.
 *
 * Un Malus est un modificateur donc on soustrait les attributsModifier
 */
class Malus(desc: String, attributsModifier: HashMap<Attribut, Int>) : Modificateur(desc, attributsModifier) {

    override fun appliquerModificateur() {
        Joueur.setPtsVie(Joueur.getPtsVie() - attributsModifier.getValue(Attribut.VIE))
        Joueur.setPtsSatiete(Joueur.getPtsSatiete() - attributsModifier.getValue(Attribut.SATIETE))
        Joueur.setPtsHydra(Joueur.getPtsHydra() - attributsModifier.getValue(Attribut.HYDRA))
    }

    override fun getAttribut(): String {
        var result = ""
        if(attributsModifier.getValue(Attribut.VIE)!=0){
            result += "-"+attributsModifier.getValue(Attribut.VIE)+" points de vie\n "
        }
        if(attributsModifier.getValue(Attribut.SATIETE)!=0){
            result += "-"+attributsModifier.getValue(Attribut.SATIETE)+" points de satiete\n"
        }
        if(attributsModifier.getValue(Attribut.HYDRA)!=0){
            result += "-"+attributsModifier.getValue(Attribut.HYDRA)+" points de hydratation"
        }
        return result
    }
}