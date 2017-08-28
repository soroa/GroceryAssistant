package com.example.asoro.healthygroceryassistant.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.example.asoro.healthygroceryassistant.ui.adapters.Teasable
import com.google.gson.annotations.SerializedName
import java.io.Serializable


@Entity
class Ingredient : Serializable, Teasable {
    @PrimaryKey
    @SerializedName("text")
    var text: String? = null
        set

    @SerializedName("weight")
    var weight: Float = 0.toFloat()

    var recipeUri: String? = null

    var isOnShoppingList: Boolean = false
}
