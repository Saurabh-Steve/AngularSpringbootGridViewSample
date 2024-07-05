package com.thales

import androidx.lifecycle.*

class ProductListingActivityViewModel(
    private val productViewModelFactory: ProductViewModelFactory,
) : ViewModel(), LifecycleObserver {
    var repos: MutableLiveData<List<ProductViewModel>> = MutableLiveData()
    var loading: MutableLiveData<Boolean> = MutableLiveData()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        repos.value = listOf(productViewModelFactory.createViewModel(), productViewModelFactory.createViewModel())
    }

    override fun onCleared() {
        super.onCleared()
    }
}