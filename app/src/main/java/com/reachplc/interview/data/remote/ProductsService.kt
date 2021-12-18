package com.reachplc.interview.data.remote

import retrofit2.http.GET

interface ProductsService {

    @GET("android/products.json")
    suspend fun getProducts(): ProductsResponse
}
