package com.example.asoro.healthygroceryassistant.db

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Relation
import com.example.asoro.healthygroceryassistant.model.Ingredient
import com.example.asoro.healthygroceryassistant.model.Recipe


class RecipeWithIngredients {
    @Embedded
    var recipe: Recipe? = null

    @Relation(parentColumn = "recipe.uri", entityColumn = "recipeUri", entity = Ingredient::class)
    var ingredients:List<*>?=null
}