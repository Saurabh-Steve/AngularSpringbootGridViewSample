package com.thales.networking

class RequestFactory {
    fun getRequestForAllProducts(searchInput: String?, sortType: SortType? = SortType.ID, order: SortingOrder? = SortingOrder.ASC): GetAllProductsRequestDto {
       return  GetAllProductsRequestDto(searchInput,
           0,
           sortType,
           order)
    }

}