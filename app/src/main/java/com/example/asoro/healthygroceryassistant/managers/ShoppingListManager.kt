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
            it.isOnShoppingList = true
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
    fun removeRecipeFromShoppingList(recipe: Recipe) {
        recipe.isOnShoppingList = false
        if (recipe.isFavorite) {
            Single.fromCallable {
                db.recipesDao().updateRecipe(recipe)
            }.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe()
            Single.fromCallable {
                for(ingredient in recipe.ingredients!!){
                    ingredient.isOnShoppingList = false
                    db.ingredientDAO().updateIngredient(ingredient)
                }
            }.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe()

        } else {
            Single.fromCallable {
                db.recipesDao().delete(recipe)
            }.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe()

        }
    }

    fun removeIngredientFromShoppingList(ingredient: Ingredient) {
        ingredient.isOnShoppingList = false
        Single.fromCallable {
            db.ingredientDAO().updateIngredient(ingredient)
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe()
    }

    fun getAllShoppingListItems(): LiveData<List<Ingredient>> {
        return db.ingredientDAO().allOnShoppingList
    }

}
