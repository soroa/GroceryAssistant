package com.example.asoro.healthygroceryassistant

import android.app.Application
import android.arch.persistence.room.Room
import com.example.asoro.healthygroceryassistant.dagger.SearchComponent
import com.example.asoro.healthygroceryassistant.db.FavoritesDatabase

class MyApp:Application() {

    companion object {
        var favoritesDB: FavoritesDatabase? = null
        lateinit var searchComponent: SearchComponent
    }


    override fun onCreate() {
        super.onCreate()
        MyApp.favoritesDB =  Room.databaseBuilder(this, FavoritesDatabase::class.java, "favorites").build()


    }
}