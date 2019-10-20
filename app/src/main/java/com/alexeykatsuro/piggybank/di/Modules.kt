package com.alexeykatsuro.piggybank.di

import androidx.room.Room
import com.alexeykatsuro.piggybank.data.PiggyBankDatabase
import com.alexeykatsuro.piggybank.data.preferences.PreferenceStorage
import com.alexeykatsuro.piggybank.data.preferences.SharedPreferenceStorage
import com.alexeykatsuro.piggybank.data.repository.MealRepository
import com.alexeykatsuro.piggybank.data.repository.MealRepositoryImpl
import com.alexeykatsuro.piggybank.ui.feeder.FeederViewModel
import com.alexeykatsuro.piggybank.ui.history.HistoryViewModel
import com.alexeykatsuro.piggybank.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<PreferenceStorage> { SharedPreferenceStorage(get()) }

    single { get<PiggyBankDatabase>().mealDao() }
    single<MealRepository> { MealRepositoryImpl(get()) }

    viewModel { HomeViewModel(get(), get()) }
    viewModel { HistoryViewModel(get()) }
    viewModel { FeederViewModel(get()) }

    single {
        Room.databaseBuilder(
            get(),
            PiggyBankDatabase::class.java,
            "PiggyBankDatabase"
        ).build()
    }
}