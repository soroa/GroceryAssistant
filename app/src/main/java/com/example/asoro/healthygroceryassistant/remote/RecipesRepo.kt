package com.example.asoro.healthygroceryassistant.remote

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.text.TextUtils
import com.example.asoro.healthygroceryassistant.model.Hits
import com.example.asoro.healthygroceryassistant.model.Recipe
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class RecipesRepo(  private var recipeAPIService: RecipesAPIService) {

    fun getRecipes(keyword: String, diet: String, healthLabel: String): LiveData<List<Recipe>> {

        var call: Call<Hits>? = null
        var result: MutableLiveData<List<Recipe>> = MutableLiveData()
        if (!TextUtils.isEmpty(diet) && !TextUtils.isEmpty(healthLabel)) {
            call = recipeAPIService.getRecipesWithDietAndHealthLiveData(RecipesAPIService.APPLICATION_ID, RecipesAPIService.APPLICATION_KEY, keyword,
                    diet, healthLabel, RECIPES_LOAD_BATCH)
        } else if (!TextUtils.isEmpty(diet) && TextUtils.isEmpty(healthLabel)) {
            call = recipeAPIService.getRecipesWithDietLiveData(RecipesAPIService.APPLICATION_ID, RecipesAPIService.APPLICATION_KEY, keyword,
                    diet, RECIPES_LOAD_BATCH)
        } else if (TextUtils.isEmpty(diet) && !TextUtils.isEmpty(healthLabel)) {
            call = recipeAPIService.getRecipesWithHealthLiveData(RecipesAPIService.APPLICATION_ID, RecipesAPIService.APPLICATION_KEY, keyword,
                    healthLabel, RECIPES_LOAD_BATCH)
        } else {
            call = recipeAPIService.getRecipesLiveData(RecipesAPIService.APPLICATION_ID, RecipesAPIService.APPLICATION_KEY, keyword, RECIPES_LOAD_BATCH)
        }
        call.enqueue(object : Callback<Hits> {
            override fun onFailure(call: Call<Hits>?, t: Throwable?) {
                result.value= null
            }

            override fun onResponse(call: Call<Hits>?, response: Response<Hits>?) {
                if (response?.isSuccessful!!) {
                    val res = response.body()
                    val recipes = ArrayList<Recipe>()
                    for (h in res?.hits!!) {
                        recipes.add(h.recipe)
                    }
                    result.value = recipes
                } else {
                    result.value =null
                }
            }
        })
        return result
    }

    companion object {
        val RECIPES_LOAD_BATCH = 50
    }
}
