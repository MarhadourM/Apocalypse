package com.example.apocalypse.Game

open class De {

    private val max: Int
    private val min: Int


    /* Class DE:
    * But:  Pour un implementation futur, l'ajout d'une carte bonus donnant un dé de 3
    * */
    constructor(max: Int, min: Int) {
        this.max = max
        this.min = min
    }

    fun lancer(): Int =
            (min + (kotlin.random.Random.nextDouble() * (max - min + 1))).toInt()

    }


