package com.example.asoro.healthygroceryassistant.model;

import com.google.gson.annotations.SerializedName;


public class Hit {

	@SerializedName("recipe")
	Recipe mRecipe;

	public Recipe getRecipe() {
		return mRecipe;
	}
}
