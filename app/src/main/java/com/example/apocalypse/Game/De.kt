package com.example.apocalypse.Game
/**
 * Représente un dé générique avec une valeur minimale et maximale.
 *
 * Peut servir pour des dés spéciaux (ex. dé à 3 faces via une carte bonus).
 *
 * @property max Valeur maximale possible.
 * @property min Valeur minimale possible.
 */
open class De {

    private val max: Int
    private val min: Int


    constructor(max: Int, min: Int) {
        this.max = max
        this.min = min
    }
    fun lancer(): Int =
            (min + (kotlin.random.Random.nextDouble() * (max - min + 1))).toInt()

    }


