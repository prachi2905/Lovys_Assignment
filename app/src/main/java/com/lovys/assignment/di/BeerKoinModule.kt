package com.lovys.assignment.di

import android.content.Context
import android.content.SharedPreferences
import com.lovys.assignment.data.repo.BeerRepository
import com.lovys.assignment.data.repo.BeerRepositoryImpl
import com.lovys.assignment.data.repo.service.BeerService
import com.lovys.assignment.domain.userflow.GetBeerList
import com.lovys.assignment.domain.userflow.GetBeersById
import com.lovys.assignment.domain.userflow.GetSearchBeer
import com.lovys.assignment.ui.viewmodels.BeerViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoriesModule = module {
    single { BeerService() }
    single<BeerRepository> { BeerRepositoryImpl(get()) }
}

val viewModelModule = module {
    single { BeerViewModel(get(), get(), get(), get()) }
}

val useCasesModule = module {
    single { GetBeersById(get()) }
    single { GetBeerList(get()) }
    single { GetSearchBeer(get()) }
}

val sharedPreferences = module {

    single { com.lovys.assignment.ui.utils.SharedPreferences(androidContext()) }

    single {
        getSharedPrefs(androidContext(), "com.lovys.assignment.PREFERENCE_FILE")
    }

    single<SharedPreferences.Editor> {
        getSharedPrefs(androidContext(), "com.lovys.assignment.PREFERENCE_FILE").edit()
    }
}

fun getSharedPrefs(context: Context, fileName: String): android.content.SharedPreferences {
    return context.getSharedPreferences(fileName, Context.MODE_PRIVATE)
}