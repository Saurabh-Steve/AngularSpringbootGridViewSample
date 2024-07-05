package com.thales

import androidx.lifecycle.*
import com.thales.networking.ThalesProductRepository
import io.reactivex.disposables.CompositeDisposable


class ProductListingActivityViewModel(
    private val schedulers: Schedulers,
    private val repository: ThalesProductRepository,
    private val productViewModelFactory: ProductViewModelFactory,
    private val compositeDisposable: CompositeDisposable
) : ViewModel(), LifecycleObserver {

    var repos: MutableLiveData<List<ProductViewModel>> = MutableLiveData()
    var loading: MutableLiveData<Boolean> = MutableLiveData()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        val subscribe = repository
            .getProducts()
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