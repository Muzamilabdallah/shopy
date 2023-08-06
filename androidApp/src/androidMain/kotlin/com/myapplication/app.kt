package com.myapplication

import android.app.Application
import com.muzamil.dictionarykmm.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.KoinComponent

class App : Application(), KoinComponent {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}