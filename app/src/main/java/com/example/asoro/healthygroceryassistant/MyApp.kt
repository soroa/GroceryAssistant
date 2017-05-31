package com.example.asoro.healthygroceryassistant

import android.app.Application
import com.example.asoro.healthygroceryassistant.dagger.DaggerRepositoryComponent
import com.example.asoro.healthygroceryassistant.dagger.RepositoryComponent
import com.example.asoro.healthygroceryassistant.db.FavoritesDatabase


class MyApp:Application() {


    companion object {
        var favoritesDB: FavoritesDatabase? = null
        lateinit var repositoryComponent: RepositoryComponent

    }

    override fun onCreate() {
        super.onCreate()
//        MyApp.favoritesDB = Room.databaseBuilder(this, FavoritesDatabase::class.java, "favorites").build()
        repositoryComponent = DaggerRepositoryComponent.builder().build()
    }
}