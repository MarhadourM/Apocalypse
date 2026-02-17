package com.example.apocalypse.Game

class Carte(titre: String, desc: String, attributsModifier: HashMap<Attribut, Int>,private val rarete: Rarete,private val idRessource: Int) : Bonus(titre, desc, attributsModifier) {

    fun getRarete(): Rarete{
        return rarete
    }
    fun getImageID():Int {
        return idRessource
    }

}