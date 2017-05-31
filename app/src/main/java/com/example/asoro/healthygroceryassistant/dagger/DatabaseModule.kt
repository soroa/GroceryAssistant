package com.example.asoro.healthygroceryassistant.dagger

import android.arch.persistence.room.Room
import android.content.Context
import com.example.asoro.healthygroceryassistant.db.FavoritesDatabase
import dagger.Module
import dagger.Provides

@Module(includes = arrayOf(ContextModule::class))
class DatabaseModule {
    @Provides
    fun database(context: Context):FavoritesDatabase{
        return Room.databaseBuilder(context, FavoritesDatabase::class.java, "favorites").build()
    }
}