package com.example.asoro.healthygroceryassistant

import android.app.Application
import com.example.asoro.healthygroceryassistant.dagger.AppComponent
import com.example.asoro.healthygroceryassistant.dagger.ContextModule
import com.example.asoro.healthygroceryassistant.dagger.DaggerAppComponent
import com.facebook.stetho.Stetho


class MyApp : Application() {


    companion object {
        lateinit var sAppComponent: AppComponent

    }

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        sAppComponent = DaggerAppComponent.builder().contextModule(ContextModule(this)).build()
    }
}