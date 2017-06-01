package com.example.asoro.healthygroceryassistant.db.Converter

import android.arch.persistence.room.TypeConverter
import com.example.asoro.healthygroceryassistant.model.Ingredient

class Converters {
    @TypeConverter
    fun ingredientToString(ingredients: List<Ingredient>?): String? {
        if(ingredients!=null && !ingredients.isEmpty()){
            var ingredientsStr =""
            for(i in ingredients){
                ingredientsStr = ingredientsStr + i.text + ","
            }
            return ingredientsStr
        }
        return null
    }

//    @TypeConverter
//    fun listToString(list: List<String>?): String? {
//        if(list!=null && !list.isEmpty()){
//            var listStr =""
//            for(i in list){
//                listStr = listStr + i + ","
//            }
//            return listStr
//        }
//        return null
//    }
    }