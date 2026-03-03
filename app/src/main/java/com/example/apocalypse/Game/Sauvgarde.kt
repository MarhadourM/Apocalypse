package com.example.apocalypse.Game

import android.content.Context
import android.util.Log
import androidx.preference.Preference
import androidx.preference.PreferenceManager

object Sauvgarde {


    fun setSauvJoueurNom(context: Context,nom: String){
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        val editor  =  preferences.edit()
        editor.putString("name", nom)
        editor.apply()
    }
    fun getSauvJoueurNom(context: Context) : String {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        val name = preferences.getString("name", "patrik") ?: "patrik"
        return name
    }

    fun isSauvJoueurNomCreated(context: Context): Boolean {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        return preferences.contains("name")
    }

    fun addSauvNbParties(context: Context){
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        val editor  =  preferences.edit()
        if(!preferences.contains("nbParties")){
            editor.putInt("nbParties", 1)
        }else{
            editor.putInt("nbParties", preferences.getInt("nbParties",1)+1)
        }
        editor.apply()
    }

    fun getSauvNbPartie(context: Context):Int{
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        val nb = preferences.getInt("nbParties", 0)
        return nb
    }
}