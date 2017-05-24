package com.example.asoro.healthygroceryassistant.ui.search_results

import com.hannesdorfmann.mosby3.mvp.MvpPresenter




interface SearchResultsPresenter : MvpPresenter<SearchResultsView> {

    /**
     * Load the next search result page for the given nextPageUrl.

     * @param nextPageUrl
     * * 		the url of the next page
     */
    fun loadNextSearchResultPage(nextPageUrl: String)

    /**
     * Load the next search result page for the given nextPageUrl.

     */
    fun loadRecipes(keyword: String, diet:String, healthLabel:String)

}
