package com.reachplc.interview.data.remote


import com.google.gson.annotations.SerializedName
import com.reachplc.interview.model.Product
import java.io.Serializable

data class ProductsResponse(
    @SerializedName("products")
    var products: List<Product> = emptyList()
) : Serializable




