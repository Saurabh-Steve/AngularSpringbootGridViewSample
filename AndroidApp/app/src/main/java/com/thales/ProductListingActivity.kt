package com.thales


import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.thales.databinding.ActivityListingBinding
import com.thales.networking.SortType
import com.thales.networking.SortingOrder


class ProductListingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListingBinding

    private lateinit var adapter: ProductListingAdapter

    private val productListingActivityViewModel: ProductListingActivityViewModel
            by lazy {
                ViewModelProviders.of(this, ListingViewModelFactory())[ProductListingActivityViewModel::class.java]
            }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_listing)
        binding.vm = productListingActivityViewModel
        binding.lifecycleOwner = this
        (binding.lifecycleOwner as ProductListingActivity).lifecycle.addObserver(
            productListingActivityViewModel
        )

        binding.repositories.layoutManager = GridLayoutManager(this, 2)
        adapter = ProductListingAdapter()
        binding.repositories.adapter = adapter


        productListingActivityViewModel.loading.observe(this) {
            when (it) {
                true -> showLoading()
                else -> hideLoading()
            }
        }
        productListingActivityViewModel.repos.observe(this, Observer {
            hideLoading()
            adapter.setItems(it)
        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }
    override fun onOptionsItemSelected( item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.sorttitle -> {
                return true
            }
            R.id.sort_criteria_id_asc -> {
                productListingActivityViewModel.onOrderBySelected(SortType.ID, SortingOrder.ASC)
                return true
            }
            R.id.sort_criteria_id_dsc -> productListingActivityViewModel.onOrderBySelected(SortType.ID, SortingOrder.DESC)
            R.id.sort_criteria_price_asc -> productListingActivityViewModel.onOrderBySelected(SortType.PRICE, SortingOrder.ASC)
            R.id.sort_criteria_price_dsc -> productListingActivityViewModel.onOrderBySelected(SortType.PRICE, SortingOrder.DESC)
            R.id.sort_criteria_rating_asc -> productListingActivityViewModel.onOrderBySelected(SortType.RATING, SortingOrder.ASC)
            R.id.sort_criteria_rating_dsc -> productListingActivityViewModel.onOrderBySelected(SortType.RATING, SortingOrder.DESC)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showLoading() {
        binding.loader.hide()
        binding.loader.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.loader.hide()
        binding.loader.visibility = View.GONE
    }
}
