package com.thales


class ProductViewModelFactory {

//    fun createViewModel(product: Product): ProductViewModel {
//
//        val displayPrice = "$" + product.displayPrice
//        val displayOriginalPrice = "$" + product.displayOriginalPrice
//        val discount = "(-" + product.discount.toString() + "%" + ")"
//        return ProductViewModel(
//            product.id,
//            product.type,
//            product.brand,
//            product.name,
//            product.description,
//            displayOriginalPrice,
//            displayPrice,
//            product.rating,
//            product.featureImageUrl[0],
//            product.featureImageUrl,
//            discount,
//            product.reviewsCount
//        )
//    }

    fun createViewModel(): ProductViewModel {
        return ProductViewModel(
            "1",
            "Furniture",
            "Table",
            "Big office table",
            "50",
            5,
            "https://www.shutterstock.com/image-vector/wooden-special-table-isolate-on-white-2442457999",
        )
    }
}