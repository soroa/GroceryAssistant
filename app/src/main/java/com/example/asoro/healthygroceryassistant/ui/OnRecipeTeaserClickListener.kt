package com.example.asoro.healthygroceryassistant.ui

import com.example.asoro.healthygroceryassistant.model.Recipe

interface OnRecipeTeaserClickListener{

    /**
     * Callback for clicks on a campaign teaser

     * @param recipe
     * * 		the recipe that was clicked
     * *
     * */
    fun showRecipeDetail(recipe: Recipe)


}