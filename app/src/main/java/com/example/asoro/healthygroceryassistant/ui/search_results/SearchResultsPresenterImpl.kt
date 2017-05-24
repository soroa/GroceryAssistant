package com.example.asoro.healthygroceryassistant.ui.search_results

import com.example.asoro.healthygroceryassistant.RecipesRepo
import com.example.asoro.healthygroceryassistant.model.Recipe
import com.hannesdorfmann.mosby3.mvp.MvpNullObjectBasePresenter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers


class SearchResultsPresenterImpl(val mRecipesRepo: RecipesRepo) : MvpNullObjectBasePresenter<SearchResultsView>(), SearchResultsPresenter {
    //todo  inject recipes repo

    override fun detachView(retainInstance: Boolean) {
        super.detachView(retainInstance)
    }

    override fun attachView(view: SearchResultsView) {
        super.attachView(view)
    }

    override fun loadNextSearchResultPage(nextPageUrl: String) {
    }

    override fun loadRecipes(keyword: String, diet: String, healthLabel:String) {
        val recipeObservable: Observable<List<Recipe>> = mRecipesRepo.getRecipesRx(keyword, diet,healthLabel)
        recipeObservable.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(Consumer { recipes ->
            view.showRecipes(recipes)
        })
    }


}
