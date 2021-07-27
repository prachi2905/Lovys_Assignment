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
        preferenceEditor?.putString(AppConstant.BEER_NAME, beer.name)
        preferenceEditor?.putString(AppConstant.BEER_TAGLINE, beer.tagline)
        preferenceEditor?.putString(AppConstant.BEER_DISCRIPTION, beer.description)
        preferenceEditor?.putFloat(AppConstant.BEER_ABV, beer.abv)
        preferenceEditor?.putFloat(AppConstant.BEER_IBU, beer.ibu)
        preferenceEditor?.putString(AppConstant.BEER_IMAGE, beer.imageURL)
        preferenceEditor?.apply()
    }
}