package com.example.asoro.healthygroceryassistant.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.example.asoro.healthygroceryassistant.model.Ingredient
import com.example.asoro.healthygroceryassistant.model.Recipe


@Database(entities = arrayOf(Recipe::class, Ingredient::class, FastPeriod::class), version = 6)
@TypeConverters(DateConverter::class)
abstract class MyDatabase : RoomDatabase() {
    abstract fun favoritesDao(): RecipeDAO

    abstract fun IngredientDAO(): IngredientDAO

    abstract fun IntermittentFastingDAO(): IntermittentFastingDAO

//    abstract fun recipeAndIngredientsDAO():RecipeAndIngredientsDAO

}
