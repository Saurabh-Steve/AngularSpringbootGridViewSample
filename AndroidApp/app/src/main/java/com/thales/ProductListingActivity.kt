package com.thales

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.thales.databinding.ActivityListingBinding


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
        supportActionBar?.title = applicationContext.getString(R.string.MainActivity)
    }

    override fun onOptionsItemSelected( item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
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
