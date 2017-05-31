package com.example.asoro.healthygroceryassistant.model

import com.example.asoro.healthygroceryassistant.ui.adapters.Teasable
import com.google.gson.annotations.SerializedName
import java.io.Serializable



class Ingredient :Serializable, Teasable{
    @SerializedName("uri")
    internal var uri: String? = null

    @SerializedName("quantity")
    internal var quantity: Float = 0.toFloat()

    @SerializedName("measure")
    internal var measure: Measure? = null


    @SerializedName("ingredienteCheckedView")
     var text: String? = null
        internal set

    @SerializedName("weight")
    internal var weight: Float = 0.toFloat()

    @SerializedName("food")
    internal var food: Food? = null


}
