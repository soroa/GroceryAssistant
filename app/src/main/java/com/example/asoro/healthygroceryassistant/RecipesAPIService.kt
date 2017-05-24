package com.example.asoro.healthygroceryassistant

import com.example.asoro.healthygroceryassistant.model.Hits

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface RecipesAPIService {


    @GET("search")
    fun getRecipes(@Query("app_id") appId: String, @Query("app_key") appKey: String,
                           @Query("q") query: String, @Query("to") number: Int): Observable<Response<Hits>>

    @GET("search")
    fun getRecipes(@Query("app_id") appId: String, @Query("app_key") appKey: String,
                   @Query("q") query: String, @Query("diet") diet: String, @Query("to") number: Int): Observable<Response<Hits>>

    @GET("search")
    fun getRecipes(@Query("app_id") appId: String, @Query("app_key") appKey: String,
                   @Query("q") query: String, @Query("diet") diet: String, @Query("health") healthLabel: String, @Query("to") number:   Int): Observable<Response<Hits>>





    @GET("search")
    fun getRecipesLiveData(@Query("app_id") appId: String, @Query("app_key") appKey: String,
                   @Query("q") query: String, @Query("to") number: Int): Call<Hits>

    @GET("search")
    fun getRecipesLiveData(@Query("app_id") appId: String, @Query("app_key") appKey: String,
                   @Query("q") query: String, @Query("diet") diet: String, @Query("to") number: Int): Call<Hits>

    @GET("search")
    fun getRecipesLiveData(@Query("app_id") appId: String, @Query("app_key") appKey: String,
                   @Query("q") query: String, @Query("diet") diet: String, @Query("health") healthLabel: String, @Query("to") number:   Int): Call<Hits>




    companion object {
        val APPLICATION_KEY = "976284a8a71cb2297532bb75787b5c4c"
        val APPLICATION_ID = "f187c826"
        val URL = "https://api.edamam.com/"
    }


}
