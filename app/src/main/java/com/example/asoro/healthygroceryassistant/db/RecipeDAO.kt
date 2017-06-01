package com.example.asoro.healthygroceryassistant.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.example.asoro.healthygroceryassistant.model.Recipe

@Dao
interface RecipeDAO {

    @Query("SELECT * FROM recipe")
    fun getAll(): LiveData<List<Recipe>>

//    @Query("SELECT * FROM recipe WHERE uri IN (:recipeUris)")
//    fun loadAllByIds(recipeUris: List<String>): LiveData<List<Recipe>>
//
//    @Query("SELECT * FROM recipe WHERE uri LIKE :uri LIMIT 1")
//    fun findByUrui(uri: String): LiveData<Recipe>

    @Insert
    fun insertAll( recipes: List<Recipe>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert( recipe: Recipe)

    @Delete
    fun delete(recipe: Recipe)
}