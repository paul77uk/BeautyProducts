package com.reachplc.interview.data.remote

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductsRepository @Inject constructor(private val productsService: ProductsService) {

    suspend fun getProducts() = productsService.getProducts()

}