package com.example.apocalypse.Game

import android.content.Context
import android.util.Log
import androidx.preference.Preference
import androidx.preference.PreferenceManager

object Sauvgarde {



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