package com.lovys.assignment.ui.activities

import android.content.SharedPreferences
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

import com.lovys.assignment.databinding.ActivityBeerDetailsBinding
import com.lovys.assignment.ui.utils.AppConstant
import com.lovys.assignment.ui.utils.AppConstant.Companion.BEER_ABV
import com.lovys.assignment.ui.utils.AppConstant.Companion.BEER_DISCRIPTION
import com.lovys.assignment.ui.utils.AppConstant.Companion.BEER_IBU
import com.lovys.assignment.ui.utils.AppConstant.Companion.BEER_IMAGE
import com.lovys.assignment.ui.utils.AppConstant.Companion.BEER_NAME
import com.lovys.assignment.ui.utils.AppConstant.Companion.BEER_TAGLINE
import org.koin.android.ext.android.inject

class BeerDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBeerDetailsBinding

    private val preferences: SharedPreferences by inject()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBeerDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        showDataOnDetailsView()
    }

    private fun showDataOnDetailsView() {
        binding.favouriteButton.startLoading()
        val willBeFevButton = !binding.favouriteButton.isActive
        //todo fev the item with the server end api call.


        val name = preferences.getString(BEER_NAME, "").toString()
        binding.titleBeer.text = name
        val tagline = preferences.getString(BEER_TAGLINE, "").toString()
        binding.beerTagline.text = tagline

        val url = preferences.getString(BEER_IMAGE, "").toString()
        //binding.beerImage.setImageURI(url)
        Glide.with(this).load(url).centerCrop().into(binding.beerImage)

        val description = preferences.getString(BEER_DISCRIPTION, "").toString()
        binding.beerDescription.text = description

        val abv = preferences.getFloat(BEER_ABV, 0f).toString()
        binding.beerAbv.text = abv

        val ibu = preferences.getFloat(BEER_IBU, 0f).toString()
        binding.beerIbu.text = ibu

        binding.backArrow.setOnClickListener {
            onBackPressed()
        }
    }


}