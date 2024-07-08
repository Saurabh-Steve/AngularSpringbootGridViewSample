package com.thales.networking

import com.thales.ProductViewModel

class RequestFactory {
    fun getRequestForAllProducts(searchInput: String?, sortType: SortType? = SortType.ID, order: SortingOrder? = SortingOrder.ASC): GetAllProductsRequestDto {
       return  GetAllProductsRequestDto(searchInput,
           0,
           sortType,
           order)
    }

    fun getRequestForAddProduct(value: ProductViewModel?): AddProductRequestDto {
        return  AddProductRequestDto(
            name = value!!.productName,
            description = value.description,
            price = value.displayPrice,
            rating = value.rating,
            image = value.featureImageUrl
        )
    }


}