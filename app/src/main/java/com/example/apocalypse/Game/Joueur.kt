package com.example.apocalypse.Game

import android.content.Context

/**
 * Représente le joueur et ses ressources principales.
 *
 * Gère les points de vie, d'hydratation et de satiété, ainsi que le nom.
 * Applique automatiquement les règles de jeu (mort, pénalité si ressources à 0).
 */
object Joueur {

    private var ptsVie: Int = 30
    private var ptsHydra: Int = 20
    private var ptsSatiete: Int = 20




    /** Indique si le joueur est mort (plus de points de vie). */
    fun isDead(): Boolean = ptsVie <= 0

    fun getPtsVie() = ptsVie
    fun setPtsVie(ptsVie: Int) {
        if (ptsVie <= 0) {
            this.ptsVie = 0
            // La mort du joueur déclenche la fin de partie
            Partie.declancherFindeparti()
        }else if (ptsVie>30){
            this.ptsVie = 30
        } else {
            this.ptsVie = ptsVie
        }
    }

    fun getPtsHydra() = ptsHydra
    fun setPtsHydra(ptsHydra: Int) {
        if (ptsHydra < 0) {
            this.ptsHydra = 0
            // Si hydratation tombe à 0, le joueur perd 2 PV
            this.setPtsVie(this.ptsVie - 2)
        }else if (ptsHydra>30){
            this.ptsHydra = 30
        }else {
            this.ptsHydra = ptsHydra
        }
    }

    fun getPtsSatiete() = ptsSatiete
    fun setPtsSatiete(ptsSatiete: Int) {
        if (ptsSatiete < 0) {
            this.ptsSatiete = 0
            // Si Satiete tombe à 0, le joueur perd 2 PV
            this.setPtsVie(this.ptsVie - 2)
        }else if (ptsSatiete>30){
            this.ptsSatiete = 30
        } else {
            this.ptsSatiete = ptsSatiete
        }
    }


    fun getNom(context: Context) = Sauvgarde.getSauvJoueurNom(context)
    fun setNom(context: Context, nom: String) {
        Sauvgarde.setSauvJoueurNom(context,nom)
    }

    /** Réinitialise le joueur pour une nouvelle partie. */
    fun reset() {
        this.setPtsVie(30)
        this.setPtsHydra(20)
        this.setPtsSatiete(20)
    }
}
