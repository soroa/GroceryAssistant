package com.example.asoro.healthygroceryassistant.ui.favorites

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.example.asoro.healthygroceryassistant.MyApp
import com.example.asoro.healthygroceryassistant.db.FavoritesDatabase
import com.example.asoro.healthygroceryassistant.model.Recipe
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FavoritesViewModel:ViewModel() {

    @Inject
    lateinit var favoritesDB: FavoritesDatabase

    init {
        MyApp.sAppComponent.inject(this)
    }

    fun removeFromFavorites(recipe: Recipe) {
        Single.fromCallable {
            favoritesDB?.favoritesDao()?.delete(recipe)
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe()
    }

    fun getFavorites(): LiveData<List<Recipe>> {
        return favoritesDB?.favoritesDao().getAll()
    }


}