package com.example.apocalypse.Game

import android.content.Context
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
}