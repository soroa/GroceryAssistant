package com.example.asoro.healthygroceryassistant.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.example.asoro.healthygroceryassistant.model.Recipe

@Dao
interface RecipeDAO {

    @Query("SELECT * FROM recipe")
    fun getAll(): LiveData<List<Recipe>>

    @Query("SELECT * FROM recipe WHERE uri IN (:recipeUris)")
    fun loadAllByIds(recipeUris: List<String>): LiveData<List<Recipe>>

    @Query("SELECT * FROM recipe WHERE uri LIKE :uri LIMIT 1")
    fun findByUrui(uri: String): LiveData<Recipe>

    @Insert
    fun insertAll( recipes: List<Recipe>)

    @Insert
    fun insert( recipe: Recipe)


    @Delete
    fun delete(recipe: Recipe)
}