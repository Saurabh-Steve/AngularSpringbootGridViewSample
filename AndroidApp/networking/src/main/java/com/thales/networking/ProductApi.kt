package com.thales.networking

import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ProductApi {
    @GET("products?pageNo=0&size=30&sortType=ID&order=ASC")
    fun getProducts():Observable<ProductResponse>

    @GET("products?")
    fun getProductsV2(@Query("name") name: String,
                      @Query("pageNo") pageNo: Int,
                      @Query("size") size: Int,
                      @Query("sortType") sortType: SortType,
                      @Query("order") order: SortingOrder):Observable<ProductResponse>

    @POST("product/update")
    fun updateProduct(@Body requestDto: UpdateProductRequestDto): Observable<Product>

    @POST("product/add")
    fun addProduct(@Body requestDto:AddProductRequestDto): Observable<Product>
}