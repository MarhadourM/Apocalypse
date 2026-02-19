package com.example.apocalypse.Game

class Carte(private val titre: String, desc: String, attributsModifier: HashMap<Attribut, Int>,private val rarete: Rarete,private val idRessource: Int) : Bonus( desc, attributsModifier) {

    fun getRarete(): Rarete{
        return rarete
    }
    fun getImageID():Int {
        return idRessource
    }

    fun getTitre(): String {return titre }


}