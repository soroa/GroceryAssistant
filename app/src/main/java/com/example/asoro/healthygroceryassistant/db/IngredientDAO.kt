package com.example.asoro.healthygroceryassistant.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.example.asoro.healthygroceryassistant.model.Ingredient

@Dao
interface IngredientDAO {

    @Query("SELECT * FROM recipe")
    fun getAll(): LiveData<List<Ingredient>>

    @Insert
    fun insertAll( recipes: List<Ingredient>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert( recipe: Ingredient)

    @Delete
    fun delete(recipe: Ingredient)

}