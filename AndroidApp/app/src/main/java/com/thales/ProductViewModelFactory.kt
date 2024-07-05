package com.thales

import com.thales.networking.Product


class ProductViewModelFactory {

    fun createViewModel(product: Product): ProductViewModel {

        val price = "$" + product.price
        return ProductViewModel(
            product.id,
        product.name,
        product.description,
        product.price,
        product.rating,
        product.image,
        )
    }
}