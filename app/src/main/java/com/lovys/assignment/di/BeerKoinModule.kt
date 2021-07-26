package com.lovys.assignment.di

import android.content.Context
import com.lovys.assignment.data.repo.PunkRepository
import com.lovys.assignment.data.repo.PunkRepositoryImpl
import com.lovys.assignment.data.repo.service.BeerService
import com.lovys.assignment.domain.userflow.GetBeerList
import com.lovys.assignment.domain.userflow.GetBeersById
import com.lovys.assignment.domain.userflow.GetSearchBeer
import com.lovys.assignment.ui.viewmodels.PunkViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoriesModule = module {
    single { BeerService() }
    single<PunkRepository> { PunkRepositoryImpl(get()) }
}

val viewModelModule = module {
    single { PunkViewModel(get(), get(), get(), get()) }
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

    single<android.content.SharedPreferences.Editor.Editor> {
        getSharedPrefs(androidContext(), "com.lovys.assignment.PREFERENCE_FILE").edit()
    }
}

fun getSharedPrefs(context: Context, fileName: String): android.content.SharedPreferences {
    return context.getSharedPreferences(fileName, Context.MODE_PRIVATE)
}