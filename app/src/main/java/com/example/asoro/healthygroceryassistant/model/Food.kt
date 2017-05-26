package com.example.asoro.healthygroceryassistant.model

import com.google.gson.annotations.SerializedName

internal class Food {
    @SerializedName("uri")
    var uri: String? = null
    @SerializedName("label")
    var label: String? = null


}
