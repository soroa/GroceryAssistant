package com.example.asoro.healthygroceryassistant.db;

import java.util.List;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import com.example.asoro.healthygroceryassistant.model.Ingredient;
import com.example.asoro.healthygroceryassistant.model.Recipe;

public class RecipeWithIngredients {
	@Embedded
	Recipe mRecipe;

	@Relation(parentColumn = "uri", entityColumn = "recipeUri", entity = Ingredient.class)
	List<Ingredient> mIngredients;

	public Recipe getRecipe() {
		return mRecipe;
	}

	public List<Ingredient> getIngredients() {
		return mIngredients;
	}
}
