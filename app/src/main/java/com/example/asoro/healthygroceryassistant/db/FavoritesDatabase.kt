package com.example.asoro.healthygroceryassistant.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.asoro.healthygroceryassistant.model.Recipe


@Database(entities = arrayOf(Recipe::class), version = 1)
abstract class FavoritesDatabase : RoomDatabase() {
    abstract fun favoritesDao(): RecipeDAO
}