package com.example.asoro.healthygroceryassistant.managers

import android.arch.lifecycle.LiveData
import com.example.asoro.healthygroceryassistant.MyApp
import com.example.asoro.healthygroceryassistant.db.MyDatabase
import com.example.asoro.healthygroceryassistant.db.RecipeWithIngredients
import com.example.asoro.healthygroceryassistant.model.Recipe
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Manager to handle favorites
 */
class FavoritesManager {

    @Inject
    lateinit var db: MyDatabase

    init {
        MyApp.sAppComponent.inject(this)
    }

    /**
     * Add a recipe to the favorites
     */
    fun addToFavorite(recipe: Recipe) {
        recipe.ingredients?.forEach {
            it.recipeUri = recipe.uri
        }
        recipe.isFavorite = true
        Single.fromCallable {
            db.recipesDao().insert(recipe)
            db.ingredientDAO().insertAll(recipe.ingredients)
        }//
                .subscribeOn(Schedulers.io())//
                .observeOn(AndroidSchedulers.mainThread())//
                .subscribe()
    }

    fun removeFromFavorites(recipe: Recipe) {
        Single.fromCallable {
            recipe.isFavorite = false
            if (recipe.isOnShoppingList) {
                db.recipesDao().updateRecipe(recipe)
            } else {
                db.recipesDao().delete(recipe)
            }
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe()
    }

    fun getFavorites(): LiveData<List<RecipeWithIngredients>> {
        return db.recipesDao().getAll()
    }

}