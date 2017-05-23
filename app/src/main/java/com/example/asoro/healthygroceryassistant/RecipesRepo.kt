package com.example.asoro.healthygroceryassistant

import com.example.asoro.healthygroceryassistant.model.Recipe
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*


class RecipesRepo {
    private var recipeAPIService:RecipesAPIService

    init{
        val retrofit = Retrofit.Builder()//
                .baseUrl(RecipesAPIService.URL)//
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//
                .addConverterFactory(GsonConverterFactory.create())//
                .build()
        recipeAPIService = retrofit.create<RecipesAPIService>(RecipesAPIService::class.java)
    }


    fun getRecipes(keyword: String, diet: String): Observable<List<Recipe>> {
        return recipeAPIService.getRecipes(RecipesAPIService.APPLICATION_ID, RecipesAPIService.APPLICATION_KEY, keyword,
                diet, RECIPES_LOAD_BATCH).map { hitsResponse ->
            if (hitsResponse.isSuccessful) {
                val res = hitsResponse.body()
                val recipes = ArrayList<Recipe>()
                for (h in res.hits) {
                    recipes.add(h.recipe)
                }
                recipes
            } else {
                ArrayList()
            }
        }
    }

    companion object {
        val RECIPES_LOAD_BATCH = 50
    }
}
