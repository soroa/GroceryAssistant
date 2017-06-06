package com.example.asoro.healthygroceryassistant.dagger

import android.arch.persistence.room.Room
import android.content.Context
import com.example.asoro.healthygroceryassistant.db.MyDatabase
import dagger.Module
import dagger.Provides

@Module(includes = arrayOf(ContextModule::class))
class DatabaseModule {
    @Provides
    fun database(context: Context): MyDatabase {
        return Room.databaseBuilder(context, MyDatabase::class.java, "favorites").build()
    }
}