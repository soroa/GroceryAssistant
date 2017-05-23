package com.example.asoro.healthygroceryassistant.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by asoro on 08.05.17.
 */

public class Ingredient {
	@SerializedName("uri")
	String uri;

	@SerializedName("quantity")
	float quantity;

	@SerializedName("measure")
	Measure measure;

	@SerializedName("weight")
	float mWeight;

	@SerializedName("food")
	Food food;


}
