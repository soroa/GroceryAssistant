package com.example.asoro.healthygroceryassistant.db

import android.arch.persistence.room.Dao

@Dao
interface RecipeAndIngredientsDAO {

//    @Query("SELECT * from recipe")
//    fun loadRecipeAndIngredients(): List<RecipeWithIngredients>

//    @Query("SELECT * FROM recipe WHERE uri LIKE :uri LIMIT 1")
//    fun findByUrui(uri: String): LiveData<RecipeWithIngredients>
}
