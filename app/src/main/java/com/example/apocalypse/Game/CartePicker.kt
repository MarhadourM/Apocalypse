package com.example.apocalypse.Game

import android.util.Log
import com.example.apocalypse.R

class CartePicker {

    private lateinit var cartes  : MutableList<Carte>

    init {
        val bouteille = Carte(
            "Bouteille d'eau",
            "Vous avez un bouteille d'eau",
            HashMap<Attribut, Int>().apply {
                this[Attribut.VIE] = 0
                this[Attribut.SATIETE] = 0
                this[Attribut.HYDRA] = 5
            },
            Rarete.COMMUN,
            R.drawable.cartebouteille
        )
        val boite = Carte(
            "Boite de Conserve",
            "Vous avez un boîte de conserve",
            HashMap<Attribut, Int>().apply {
                this[Attribut.VIE] = 0
                this[Attribut.SATIETE] = 5
                this[Attribut.HYDRA] = 0
            },
            Rarete.COMMUN,
            R.drawable.carteconserve
        )
        val filtre = Carte(
            "Filtre à eau",
            "Vous avez un filtre à eau",
            HashMap<Attribut, Int>().apply {
                this[Attribut.VIE] = 0
                this[Attribut.SATIETE] = 0
                this[Attribut.HYDRA] = 10
            },
            Rarete.RARE,
            R.drawable.cartbonus
        )
        val trousse = Carte(
            "Trousse de Soins",
            "Vous avez une trousse de soins",
            HashMap<Attribut, Int>().apply {
                this[Attribut.VIE] = 5
                this[Attribut.SATIETE] = 0
                this[Attribut.HYDRA] = 0
            },
            Rarete.LEGENDAIRE,
            R.drawable.cartetroussesoins
        )
        val kit = Carte(
            "Kit de survie",
            "Vous avez un kit de survie",
            HashMap<Attribut, Int>().apply {
                this[Attribut.VIE] = 10
                this[Attribut.SATIETE] = 2
                this[Attribut.HYDRA] = 2
            },
            Rarete.LEGENDAIRE,
            R.drawable.cartekitsurvie
        )
        val ration = Carte(
            "Ration  de survie",
            "Vous avez une ration de survie",
            HashMap<Attribut, Int>().apply {
                this[Attribut.VIE] = 0
                this[Attribut.SATIETE] = 5
                this[Attribut.HYDRA] = 5
            },
            Rarete.RARE,
            R.drawable.carterationsurvie
        )
        cartes = mutableListOf<Carte>()

        cartes.add(bouteille)
        cartes.add(boite)
        cartes.add(ration)
        cartes.add(filtre)
        cartes.add(trousse)
        cartes.add(kit)


    }


    fun pickCartes():ArrayList<Carte>{
        var d  = ArrayList<Carte>()

        val cartesCommun = listOf(0, 1)
        val cartesRare = listOf(2, 3)
        val cartesLegendaire = listOf(4, 5)

        //defini la rarete des cartes
        val r1 = kotlin.random.Random.nextInt(100)
        val carte1 = when {
            r1 < 70 -> cartesCommun.random()
            r1 < 95 -> cartesRare.random()
            else -> cartesLegendaire.random()
        }
        d.add(cartes[carte1])
        Log.i("Rarete", "Carte1: index $carte1")

        val r2 = kotlin.random.Random.nextInt(100)
        val carte2 = when {
            r2 < 70 -> cartesCommun.random().takeUnless { it == carte1 } ?: cartesCommun.random()
            r2 < 95 -> cartesRare.random().takeUnless { it == carte1 } ?: cartesRare.random()
            else -> cartesLegendaire.random().takeUnless { it == carte1 } ?: cartesLegendaire.random()
        }
        d.add(cartes[carte2])
        Log.i("Rarete", "Carte2: index $carte2")

        Log.i("Rarete",d.size.toString())
        Log.i("Rarete carte 1 ", d[0].getTitre())
        Log.i("Rarete carte 2", d[1].getTitre())

        return d

    }
}