package com.thales

import androidx.lifecycle.*
import com.thales.networking.GetAllProductsRequestDto
import com.thales.networking.RequestFactory
import com.thales.networking.SortType
import com.thales.networking.SortingOrder
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
    var dialogProduct: ProductViewModel? = null
    var sortCriteria : MutableLiveData<Pair<String, String>> = MutableLiveData()

    var searchInput : String = ""

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        val requestDto = requestFactory.getRequestForAllProducts("")
        getAllProducts(requestDto)
    }

    fun onClickSearchBtn() {
        val requestDto = requestFactory.getRequestForAllProducts(searchInput)
        getAllProducts(requestDto)
    }

    fun onOrderBySelected(sortType: SortType, sortingOrder: SortingOrder) {
        val requestDto = requestFactory.getRequestForAllProducts(searchInput, sortType, sortingOrder)
        getAllProducts(requestDto)
    }

    private fun getAllProducts(requestDto: GetAllProductsRequestDto) {
        val subscribe = repository
            .getProducts(requestDto)
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

    fun onSave(n:String, desc:String, p:String, r:Int, url:String) {
        dialogProduct = ProductViewModel("0", n, description = desc, displayPrice = p,
            rating = r, featureImageUrl = url)

        val subscribe = repository.addProduct(requestFactory.getRequestForAddProduct(dialogProduct))
            .subscribeOn(schedulers.ioScheduler())
            .observeOn(schedulers.uiScheduler())
            .doOnSubscribe {
                loading.value = true
            }.doOnError {
                loading.value = false
            }.subscribe({
                loading.value = true
                val requestDto = requestFactory.getRequestForAllProducts(searchInput)
                getAllProducts(requestDto)
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

    fun update(vm: ProductViewModel) {
        val subscribe = repository.updateProduct(requestFactory.getRequestForUpdateProduct(vm))
            .subscribeOn(schedulers.ioScheduler())
            .observeOn(schedulers.uiScheduler())
            .doOnSubscribe {
                loading.value = true
            }.doOnError {
                loading.value = false
            }.subscribe({
                loading.value = true
                val requestDto = requestFactory.getRequestForAllProducts(searchInput)
                getAllProducts(requestDto)
            }, {
                loading.value = false
                it.printStackTrace()
            })
        compositeDisposable.add(subscribe)
    }
}