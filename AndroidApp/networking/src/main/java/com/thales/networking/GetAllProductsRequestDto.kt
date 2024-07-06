package com.thales.networking


data class GetAllProductsRequestDto(
    val name:String?= "",
    val pageNo: Int,
    val sortType: SortType?,
    val order: SortingOrder?,
)
