package com.example.asoro.healthygroceryassistant.dagger

import com.example.asoro.healthygroceryassistant.remote.RecipesAPIService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
class NetworkModule {

    @ApplicationScope
    @Provides
    fun provideOkHttp(): OkHttpClient {
        return OkHttpClient().newBuilder().build()
    }

    @ApplicationScope
    @Provides
    fun retrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()//
                .baseUrl(RecipesAPIService.URL)//
                .client(okHttpClient)//
                .addConverterFactory(GsonConverterFactory.create())//
                .build()

    }
}