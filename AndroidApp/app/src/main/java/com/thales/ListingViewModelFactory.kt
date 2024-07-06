package com.thales

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.thales.networking.RequestFactory
import com.thales.networking.ThalesProductRepository
import io.reactivex.disposables.CompositeDisposable

class ListingViewModelFactory : ViewModelProvider.Factory {
    private var schedulers = Schedulers()
    private var domainRepository = ThalesProductRepository()
    private var compositeDisposable = CompositeDisposable()
    private var productViewModelFactory = ProductViewModelFactory()
    private var requestFactory = RequestFactory()

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductListingActivityViewModel::class.java)) {
            return ProductListingActivityViewModel(schedulers, domainRepository, productViewModelFactory, compositeDisposable, requestFactory) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}