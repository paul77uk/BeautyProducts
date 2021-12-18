package com.reachplc.interview.data.remote

data class ProductsResponse(
    var products: List<Product> = emptyList()) {

    data class Product(
        val id: String = "",
        val name: String = "",
        val image: String = "",
        val description: String = "",
        val price: Double = 0.0,
    )


}
