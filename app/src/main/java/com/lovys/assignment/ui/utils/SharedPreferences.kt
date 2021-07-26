package com.lovys.assignment.ui.utils

import android.content.Context
import android.content.SharedPreferences
import com.lovys.assignment.domain.localdb.Beer

class SharedPreferences(private val context: Context) {

    private val sharedPrefFile = "com.lovys.assignment.PREFERENCE_FILE"
    private var preferences: SharedPreferences? = null

    fun saveCurrentBeerData(beer: Beer) {
        preferences = context.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        val preferenceEditor = preferences?.edit()
        preferenceEditor?.putString("beerName", beer.name)
        preferenceEditor?.putString("beerTagline", beer.tagline)
        preferenceEditor?.putString("beerDescription", beer.description)
        preferenceEditor?.putFloat("beerABV", beer.abv)
        preferenceEditor?.putFloat("beerIBU", beer.ibu)
        preferenceEditor?.putString("beerImage", beer.imageURL)
        preferenceEditor?.apply()
    }
}