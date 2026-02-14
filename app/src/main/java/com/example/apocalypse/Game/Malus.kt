package com.example.apocalypse.Game

class Malus(titre: String, desc: String, attributsModifier: HashMap<Attribut, Int>) : Modificateur(titre, desc, attributsModifier) {

    override fun appliquerModificateur() {
        Joueur.setPtsVie(Joueur.getPtsVie() - attributsModifier.getValue(Attribut.VIE))
        Joueur.setPtsSatiete(Joueur.getPtsSatiete() - attributsModifier.getValue(Attribut.SASIETE))
        Joueur.setPtsHydra(Joueur.getPtsHydra() - attributsModifier.getValue(Attribut.HYDRA))
    }
}