package com.example.apocalypse.Game

class Carte(titre: String, desc: String, attributsModifier: HashMap<Attribut, Int>,private val rarete: Rarete) : Bonus(titre, desc, attributsModifier) {

    fun getRarete(): Rarete{
        return rarete
    }
    //TODO Methode getImageID pour recuperer l'identifiant de la carte et pouvoir l'afficher
}