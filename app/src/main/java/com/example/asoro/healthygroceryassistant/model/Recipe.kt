package com.example.asoro.healthygroceryassistant.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import com.example.asoro.healthygroceryassistant.ui.adapters.Teasable
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity
class  Recipe: Teasable, Serializable {

    @PrimaryKey
    @SerializedName("uri")
    var uri: String? = null

    @ColumnInfo(name = "name")
    @SerializedName("label")
    var name: String? = null

    @Ignore
    @ColumnInfo(name = "ingredients")
    @SerializedName("ingredients")
    var ingredients: List<Ingredient>? = null

    @ColumnInfo(name = "image_url")
    @SerializedName("image")
    var imageURL: String? = null

    @ColumnInfo(name = "url")
    @SerializedName("url")
    var url: String? = null


    @ColumnInfo(name = "yield")
    @SerializedName("yield")
    var servings: Float? = null

    @Ignore
    @ColumnInfo(name = "healthLabels")
    @SerializedName("healthLabels")
    var healthLabels: List<String>? = null

}
