package com.thales.networking

import io.reactivex.Observable

class ThalesProductRepository()  {

     fun getProducts(thalesService: ProductApi = APIClient.getClient().create(ProductApi::class.java)): Observable<List<Product>> {
        return thalesService.getProducts()
            .map {
               it.content
            }
    }
}