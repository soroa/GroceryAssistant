package com.example.asoro.healthygroceryassistant.ui.recipe_detail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.asoro.healthygroceryassistant.R
import com.example.asoro.healthygroceryassistant.loadImg
import com.example.asoro.healthygroceryassistant.model.Recipe
import kotlinx.android.synthetic.main.activity_recipe_detail.*



class RecipeDetailActivity: AppCompatActivity() {
    private var recipe:Recipe? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_detail)
        getSupportActionBar()?.hide();
        recipe = intent.getSerializableExtra("recipe") as Recipe
        teaser_preview_details.loadImg(recipe?.imageURL!!)
        recipe_title.setText(recipe?.name)
        var ingredients = ""
        for(i in recipe?.ingredientList!!){
            ingredients = ingredients + i.text + " \n"
        }

        recipe_ingredients.setText(ingredients)
        recipe_url.setText(recipe?.url)

    }




}