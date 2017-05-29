package com.example.asoro.healthygroceryassistant

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.text.TextUtils
import com.example.asoro.healthygroceryassistant.model.Hits
import com.example.asoro.healthygroceryassistant.model.Recipe
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*


class RecipesRepo {
    private var recipeAPIService: RecipesAPIService

    init {
        val retrofit = Retrofit.Builder()//
                .baseUrl(RecipesAPIService.URL)//
                .addConverterFactory(GsonConverterFactory.create())//
                .build()
        recipeAPIService = retrofit.create<RecipesAPIService>(RecipesAPIService::class.java)
    }


    fun getRecipesRx(keyword: String, diet: String, healthLabel: String): Observable<List<Recipe>> {
        val observable: Observable<Response<Hits>>
        if (!TextUtils.isEmpty(diet) && !TextUtils.isEmpty(healthLabel)) {
            observable = recipeAPIService.getRecipes(RecipesAPIService.APPLICATION_ID, RecipesAPIService.APPLICATION_KEY, keyword,
                    diet, healthLabel, RECIPES_LOAD_BATCH)
        } else if (!TextUtils.isEmpty(diet) && TextUtils.isEmpty(healthLabel)) {
            observable = recipeAPIService.getRecipes(RecipesAPIService.APPLICATION_ID, RecipesAPIService.APPLICATION_KEY, keyword,
                    diet, RECIPES_LOAD_BATCH)
        } else {
            observable = recipeAPIService.getRecipes(RecipesAPIService.APPLICATION_ID, RecipesAPIService.APPLICATION_KEY, keyword, RECIPES_LOAD_BATCH)
        }
        return observable.map { hitsResponse ->
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
                    for (h in res.hits) {
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
