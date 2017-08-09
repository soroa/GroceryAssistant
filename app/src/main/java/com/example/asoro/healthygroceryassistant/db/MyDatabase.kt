package com.example.asoro.healthygroceryassistant.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.example.asoro.healthygroceryassistant.model.Ingredient
import com.example.asoro.healthygroceryassistant.model.Recipe


@Database(entities = arrayOf(Recipe::class, Ingredient::class, FastPeriod::class), version = 1)
@TypeConverters(DateConverter::class)
abstract class MyDatabase : RoomDatabase() {

    abstract fun recipesDao(): RecipeDAO

    abstract fun ingredientDAO(): IngredientDAO

    abstract fun intermittentFastingDAO(): IntermittentFastingDAO

}
