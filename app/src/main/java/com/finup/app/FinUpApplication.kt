package com.finup.app

import android.app.Application
import com.finup.app.di.AppContainer

class FinUpApplication : Application() {

    lateinit var container: AppContainer
        private set

    override fun onCreate() {
        super.onCreate()

        container = AppContainer(this)
    }
}