package com.example.asoro.healthygroceryassistant.model

import com.example.asoro.healthygroceryassistant.ui.adapters.Teasable
import com.google.gson.annotations.SerializedName
import java.io.Serializable


class  Recipe: Teasable, Serializable {

    @SerializedName("label")
    var name: String? = null
        internal set

    @SerializedName("uri")
    var uri: String? = null
        internal set

    @SerializedName("ingredients")
    var ingredientList: List<Ingredient>? = null
        internal set

    @SerializedName("image")
    var imageURL: String? = null
        internal set

    @SerializedName("url")
    var url: String? = null
        internal set

    @SerializedName("yield")
    var servings: Float? = null
        internal set

    @SerializedName("healthLabels")
    var healthLabels: List<String>? = null
        internal set


}
