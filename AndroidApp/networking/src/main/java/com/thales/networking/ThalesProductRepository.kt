package com.thales.networking

import io.reactivex.Observable

class ThalesProductRepository()  {

     fun getProducts(requestDto: GetAllProductsRequestDto, thalesService: ProductApi = APIClient.getClient().create(ProductApi::class.java)): Observable<List<Product>> {
         return thalesService.getProductsV2(
             requestDto.name ?: "",
             requestDto.pageNo,
             100,
             requestDto.sortType ?: SortType.ID,
             requestDto.order ?: SortingOrder.ASC)
            .map {
               it.content
            }
    }
}