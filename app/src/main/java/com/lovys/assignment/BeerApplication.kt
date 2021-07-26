package com.lovys.assignment

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import com.lovys.assignment.di.repositoriesModule
import com.lovys.assignment.di.sharedPreferences
import com.lovys.assignment.di.useCasesModule
import com.lovys.assignment.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BeerApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Fresco.initialize(this)

        startKoin {
            androidContext(this@BeerApplication)
            modules(listOf(repositoriesModule, viewModelModule, useCasesModule, sharedPreferences))
        }
    }
}