package com.reachplc.interview.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Product(
    @SerializedName("id")
    val id: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("image")
    val image: String = "",
    @SerializedName("description")
    val description: String = "",
    @SerializedName("price")
    val price: Double = 0.0,
): Serializable
