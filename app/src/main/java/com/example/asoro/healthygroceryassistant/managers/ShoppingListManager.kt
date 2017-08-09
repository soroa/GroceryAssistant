package com.example.asoro.healthygroceryassistant.managers

import android.arch.lifecycle.LiveData
import com.example.asoro.healthygroceryassistant.MyApp
import com.example.asoro.healthygroceryassistant.db.MyDatabase
import com.example.asoro.healthygroceryassistant.model.Ingredient
import com.example.asoro.healthygroceryassistant.model.Recipe
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ShoppingListManager {

    @Inject
    lateinit var db: MyDatabase

    init {
        MyApp.sAppComponent.inject(this)
    }

    /**
     * Add a recipe to the shopping list
     */
    fun addRecipeToShoppingList(recipe: Recipe) {
        recipe.ingredients?.forEach {
            it.recipeUri = recipe.uri
            it.isOnShoppingList=true
        }
        recipe.isOnShoppingList = true

        Single.fromCallable {
            db.recipesDao().insert(recipe)
            db.ingredientDAO().insertAll(recipe.ingredients!!)
        }//
                .subscribeOn(Schedulers.io())//
                .observeOn(AndroidSchedulers.mainThread())//
                .subscribe()
    }

    /**
     *
     */
    fun removeRecipeFromShoppingList(recipe:Recipe){
        recipe.isOnShoppingList = false
        if(recipe.isFavorite){
            db.recipesDao().updateRecipe(recipe)
        }else{
            db.recipesDao().delete(recipe)
        }
        Single.fromCallable {
            db.recipesDao().insert(recipe)
            db.ingredientDAO().insertAll(recipe.ingredients!!)
        }//
                .subscribeOn(Schedulers.io())//
                .observeOn(AndroidSchedulers.mainThread())//
                .subscribe()

    }
    fun removeFromFavorites(ingredient: Ingredient) {
        Single.fromCallable {
            db.ingredientDAO().delete(ingredient)
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe()
    }

    fun getFavorites(): LiveData<List<Ingredient>> {
        return db.ingredientDAO().getAll()
    }

}
