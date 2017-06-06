package com.example.asoro.healthygroceryassistant.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*

@Dao
interface IntermittentFastingDAO {

    @Query("SELECT * FROM fastperiod")
    fun getAll(): LiveData<List<FastPeriod>>

    @Insert
    fun insertAll(fastPeriods: List<FastPeriod>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(fastPeriod: FastPeriod)

    @Delete
    fun delete(fastPeriod: FastPeriod)
}