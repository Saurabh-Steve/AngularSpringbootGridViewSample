package com.thales.networking

import io.reactivex.Observable
import retrofit2.http.GET

interface ProductApi {
    @GET("products?pageNo=0&size=30&sortType=ID&order=ASC")
    fun getProducts():Observable<ProductResponse>
}