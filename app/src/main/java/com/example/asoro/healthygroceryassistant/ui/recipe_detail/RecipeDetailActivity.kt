package com.example.asoro.healthygroceryassistant.ui.recipe_detail

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import com.example.asoro.healthygroceryassistant.R
import com.example.asoro.healthygroceryassistant.loadImg
import com.example.asoro.healthygroceryassistant.model.Recipe
import kotlinx.android.synthetic.main.activity_recipe_detail.*



class RecipeDetailActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_recipe_detail)
        val recipe = intent.getSerializableExtra("recipe") as Recipe
        teaser_preview_details.loadImg(recipe.imageURL!!)

    }



}