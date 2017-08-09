package com.example.asoro.healthygroceryassistant.dagger

import com.example.asoro.healthygroceryassistant.managers.FavoritesManager
import com.example.asoro.healthygroceryassistant.managers.ShoppingListManager
import dagger.Module
import dagger.Provides


@Module(includes = arrayOf(DatabaseModule::class))
class ManagersModule {
    @Provides
    fun favoritesManager(): FavoritesManager {
        return FavoritesManager()
    }

    @Provides
    fun shoppingListManager(): ShoppingListManager {
        return ShoppingListManager()
    }


}