package com.example.asoro.healthygroceryassistant.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable



class Ingredient :Serializable{
    @SerializedName("uri")
    internal var uri: String? = null

    @SerializedName("quantity")
    internal var quantity: Float = 0.toFloat()

    @SerializedName("measure")
    internal var measure: Measure? = null

    @SerializedName("weight")
    internal var mWeight: Float = 0.toFloat()

    @SerializedName("food")
    internal var food: Food? = null


}
