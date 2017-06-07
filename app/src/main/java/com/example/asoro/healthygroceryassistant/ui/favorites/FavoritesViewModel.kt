package com.example.asoro.healthygroceryassistant.ui.favorites

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.example.asoro.healthygroceryassistant.MyApp
import com.example.asoro.healthygroceryassistant.db.MyDatabase
import com.example.asoro.healthygroceryassistant.model.Ingredient
import com.example.asoro.healthygroceryassistant.model.Recipe
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FavoritesViewModel:ViewModel() {

    @Inject
    lateinit var mMyDB: MyDatabase

    init {
        MyApp.sAppComponent.inject(this)
    }

    fun removeFromFavorites(recipe: Recipe) {
        Single.fromCallable {
            mMyDB?.favoritesDao()?.delete(recipe)
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe()
    }

    fun getFavorites(): LiveData<List<Recipe>> {
        return mMyDB?.favoritesDao().getAll()
    }


    fun getIngredientsByUri(uri:String):LiveData<List<Ingredient>>{
        return mMyDB?.IngredientDAO().getAllByRecipeId(uri)
    }

    fun getAllIngredients():LiveData<List<Ingredient>>{
        return mMyDB?.IngredientDAO().all
    }

}