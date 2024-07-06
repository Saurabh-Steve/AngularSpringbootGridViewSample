package com.thales.networking

class RequestFactory {
    fun getRequestForAllProducts(searchInput: String?): GetAllProductsRequestDto {
       return  GetAllProductsRequestDto(searchInput,
           0,
           null,
           null)
    }

}