package com.thales

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ListingViewModelFactory : ViewModelProvider.Factory {
    private var productViewModelFactory = ProductViewModelFactory()

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductListingActivityViewModel::class.java)) {
            return ProductListingActivityViewModel(productViewModelFactory) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}