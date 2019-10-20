package com.alexeykatsuro.piggybank.ui

import android.app.Application
import com.alexeykatsuro.piggybank.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PiggyBankApp : Application(){

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@PiggyBankApp)
            modules(appModule)
        }
    }
}