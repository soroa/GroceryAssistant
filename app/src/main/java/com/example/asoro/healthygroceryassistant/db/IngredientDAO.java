package com.example.asoro.healthygroceryassistant.db;

import java.util.List;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.asoro.healthygroceryassistant.model.Ingredient;

@Dao
public interface IngredientDAO {

	@Query("SELECT * FROM ingredient")
	LiveData<List<Ingredient>> getAll();

	@Query("SELECT * FROM ingredient WHERE isOnShoppingList = 1")
	LiveData<List<Ingredient>> getAllOnShoppingList();

	@Query("SELECT * FROM ingredient WHERE recipeUri = :rUri")
	LiveData<List<Ingredient>> getAllByRecipeId(String rUri);

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	void insert(Ingredient ingredient);

	@Update
	void updateIngredient(Ingredient ingredient);

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	void insertAll(List<Ingredient> ingredients);


	@Delete
	void delete(Ingredient ingredient);
}
