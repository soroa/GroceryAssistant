package com.example.asoro.healthygroceryassistant.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.example.asoro.healthygroceryassistant.model.Recipe

@Dao
interface RecipeDAO {

    @Query("SELECT * FROM recipe")
    fun getAll(): LiveData<List<RecipeWithIngredients>>

    @Query("SELECT * FROM recipe, ingredient WHERE recipe.uri LIKE :recipeUri LIMIT 1")
    fun findByUrui(recipeUri: String): LiveData<RecipeWithIngredients>

    @Insert
    fun insertAll(recipes: List<Recipe>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert( recipe: Recipe)

    @Update
    fun updateRecipe( recipe: Recipe)

    @Delete
    fun delete(recipe: Recipe)
}