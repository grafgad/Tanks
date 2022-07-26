package com.example.tanks

import android.app.Application
import com.example.tanks.di.ApplicationComponent
import com.example.tanks.di.DaggerApplicationComponent
import com.github.terrakok.cicerone.Cicerone

class App : Application() {

    private val cicerone = Cicerone.create()
    val router get() = cicerone.router
    val navigatorHolder get() = cicerone.getNavigatorHolder()

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        appComponent = DaggerApplicationComponent.builder().build()
    }

    companion object {
        var appComponent: ApplicationComponent? = null
        internal lateinit var INSTANCE: App
            private set
    }
}