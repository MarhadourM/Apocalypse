package com.example.apocalypse.Game

/**
 * États possibles de la partie.
 *
 * MENU       : écran principal.
 * ONSTART    : démarrage / Regles.
 * CHOIXBONUS : choix d'un bonus en début de jour.
 * ACTION     : le joueur réalise une action.
 * LANCERDES  : phase de lancer de dés.
 * FINPARTI   : écran de fin de partie.
 */
enum class EtatJeu {
    MENU,ONSTART,CHOIXBONUS,ACTION,LANCERDES,FINPARTI
}