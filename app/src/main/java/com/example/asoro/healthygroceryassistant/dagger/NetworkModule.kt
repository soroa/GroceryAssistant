package com.example.asoro.healthygroceryassistant.dagger

import com.example.asoro.healthygroceryassistant.RecipesAPIService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
interface NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()//
                .baseUrl(RecipesAPIService.URL)//
                .addConverterFactory(GsonConverterFactory.create())//
                .build()
    }


}