package com.example.asoro.healthygroceryassistant.db

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity
data class FastPeriod( val startDate:Date, val endDate:Date){

    @PrimaryKey(autoGenerate = true) var id:Int = 0

    fun getDuration():Long{
        return   endDate.getTime() - startDate.getTime();
    }

    fun getDateString():String{
        //TODO  return day
        return startDate.toString()
    }
}
