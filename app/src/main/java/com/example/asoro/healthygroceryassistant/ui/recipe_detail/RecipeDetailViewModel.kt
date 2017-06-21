package com.example.asoro.healthygroceryassistant.ui.recipe_detail

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.example.asoro.healthygroceryassistant.MyApp
import com.example.asoro.healthygroceryassistant.db.MyDatabase
import com.example.asoro.healthygroceryassistant.db.RecipeWithIngredients
import com.example.asoro.healthygroceryassistant.model.Recipe
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RecipeDetailViewModel : ViewModel() {


    @Inject
    lateinit var mMyDB: MyDatabase

    init {
        MyApp.sAppComponent.inject(this)
    }

    fun addToFavorite(recipe: Recipe) {
        Single.fromCallable {
            mMyDB?.favoritesDao()?.insert(recipe)
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe()
    }


    fun removeFromFavorites(recipe: Recipe) {
        Single.fromCallable {
            mMyDB?.favoritesDao()?.delete(recipe)
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe()
    }

    fun getFavorites(): LiveData<List<RecipeWithIngredients>> {
        return mMyDB.favoritesDao().getAll()
    }


}