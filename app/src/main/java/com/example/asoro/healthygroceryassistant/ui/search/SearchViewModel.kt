package com.example.asoro.healthygroceryassistant.ui.search

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.example.asoro.healthygroceryassistant.MyApp
import com.example.asoro.healthygroceryassistant.RecipesRepo
import com.example.asoro.healthygroceryassistant.db.FavoritesDatabase
import com.example.asoro.healthygroceryassistant.model.Ingredient
import com.example.asoro.healthygroceryassistant.model.Recipe
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchViewModel : ViewModel() {

    @Inject
    lateinit var recipeRepo: RecipesRepo

    @Inject
    lateinit var favoritesDB: FavoritesDatabase


    init {
        MyApp.sAppComponent.inject(this)
    }

    var recipes: LiveData<List<Recipe>>? = null

    fun loadData(query: String, diet: String, healthLabel: String): LiveData<List<Recipe>> {
        recipes = recipeRepo.getRecipes(query, diet, healthLabel)
        return recipes as LiveData<List<Recipe>>
    }

    fun addToFavorite(recipe: Recipe) {
        recipe.ingredients?.forEach{
            it.recipeUri=recipe.uri
        }

        Single.fromCallable {
            favoritesDB.favoritesDao().insert(recipe)
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe()
        Single.fromCallable {
            favoritesDB.IngredientDAO().insertAll(recipe.ingredients as List<Ingredient>)
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun removeFromFavorites(recipe: Recipe) {
        Single.fromCallable {
            favoritesDB.favoritesDao().delete(recipe)
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe()
    }

    fun getFavorites(): LiveData<List<Recipe>> {
        return favoritesDB.favoritesDao().getAll()
    }


}