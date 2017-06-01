package com.example.asoro.healthygroceryassistant.ui.recipe_detail

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.asoro.healthygroceryassistant.R
import com.example.asoro.healthygroceryassistant.loadImg
import com.example.asoro.healthygroceryassistant.model.Recipe
import kotlinx.android.synthetic.main.activity_recipe_detail.*



class RecipeDetailActivity: AppCompatActivity() {
    private var recipe:Recipe? =null
    private var viewModel: RecipeDetailViewModel? = null
    private var isFavorite:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_detail)
        getSupportActionBar()?.hide()
        viewModel = ViewModelProviders.of(this).get(RecipeDetailViewModel::class.java)

        recipe = intent.getSerializableExtra("recipe") as Recipe
        teaser_preview_details.loadImg(recipe?.imageURL!!)
        recipe_title.setText(recipe?.name)
        var ingredients = ""
        for(i in recipe?.ingredientList!!){
            ingredients = ingredients + i.text + " \n"
        }
        recipe_ingredients.setText(ingredients)
        recipe_url.setText(recipe?.url)

        favorite_btn.setOnClickListener{ v ->
            isFavorite = !isFavorite
            favorite_btn.setColorFilter(if (isFavorite) resources.getColor(R.color.darkRed) else resources.getColor(R.color.primary_dark_material_dark))
            if(isFavorite) viewModel?.addToFavorite(recipe as Recipe) else viewModel?.removeFromFavorites(recipe as Recipe)

        }
    }



}