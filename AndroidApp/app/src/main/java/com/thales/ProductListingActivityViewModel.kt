package com.thales

import androidx.lifecycle.*
import com.thales.networking.RequestFactory
import com.thales.networking.ThalesProductRepository
import io.reactivex.disposables.CompositeDisposable


class ProductListingActivityViewModel(
    private val schedulers: Schedulers,
    private val repository: ThalesProductRepository,
    private val productViewModelFactory: ProductViewModelFactory,
    private val compositeDisposable: CompositeDisposable,
    private val requestFactory: RequestFactory
) : ViewModel(), LifecycleObserver {

    var repos: MutableLiveData<List<ProductViewModel>> = MutableLiveData()
    var loading: MutableLiveData<Boolean> = MutableLiveData()
    var searchInput : String = ""

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        getAllProducts("")
    }

    fun onClickSearchBtn() {
        getAllProducts(searchInput)
    }

    private fun getAllProducts(searchInput: String) {
        val subscribe = repository
            .getProducts(requestFactory.getRequestForAllProducts(searchInput))
            .subscribeOn(schedulers.ioScheduler())
            .observeOn(schedulers.uiScheduler())
            .doOnSubscribe {
                loading.value = true
            }
            .map { it ->
                it.map {
                    productViewModelFactory.createViewModel(it)
                }
            }.doOnError {
                loading.value = false
            }.subscribe({
                loading.value = false
                repos.value = it
            }, {
                loading.value = false
                it.printStackTrace()
            })
        compositeDisposable.add(subscribe)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}