package com.example.asoro.healthygroceryassistant.dagger

import com.example.asoro.healthygroceryassistant.remote.RecipesAPIService
import com.example.asoro.healthygroceryassistant.remote.RecipesRepo
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module(includes = arrayOf(NetworkModule::class))
class RecipesRepoModule {

    @ApplicationScope
    @Provides
    fun recipesAPIService(retrofit: Retrofit): RecipesAPIService {
        return retrofit.create<RecipesAPIService>(RecipesAPIService::class.java)
    }

    @ApplicationScope
    @Provides
    fun recipesRepo(recipesAPIService: RecipesAPIService): RecipesRepo {
        return RecipesRepo(recipesAPIService)
    }

}