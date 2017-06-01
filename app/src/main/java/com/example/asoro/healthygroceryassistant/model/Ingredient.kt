package com.example.asoro.healthygroceryassistant.model

import com.example.asoro.healthygroceryassistant.ui.adapters.Teasable
import com.google.gson.annotations.SerializedName
import java.io.Serializable



class Ingredient :Serializable, Teasable{
    @SerializedName("uri")
    var uri: String? = null

    @SerializedName("text")
     var text: String? = null
        set

    @SerializedName("weight")
    var weight: Float = 0.toFloat()


}
