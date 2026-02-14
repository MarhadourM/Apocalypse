package com.example.apocalypse.Game

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


