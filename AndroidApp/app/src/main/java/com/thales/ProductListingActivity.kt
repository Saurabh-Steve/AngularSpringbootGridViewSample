package com.thales



import android.app.Dialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
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
    private lateinit var dialog: Dialog

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

        binding.repositories.layoutManager = GridLayoutManager(this, 1)
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
            adapter.setOnEditClickListener { vm: ProductViewModel -> productListingActivityViewModel.update(vm) }
        })

        binding.addPdtIcon.setOnClickListener {
            onClickAdd();
        }
    }


    private fun onClickAdd() {
        val dialog = Dialog(this)
        val view = this.layoutInflater.inflate(R.layout.customdialoglayout, null)
        dialog.setContentView(view)
        dialog.show()

        view.findViewById<View>(R.id.save).setOnClickListener {
            val name = (view.findViewById<EditText>(R.id.d_name) as EditText).text.toString()
            val price = (view.findViewById<EditText>(R.id.d_price) as EditText).text.toString()
            val desc = (view.findViewById<EditText>(R.id.d_desc) as EditText).text.toString()
            val r = (view.findViewById<EditText>(R.id.d_rating) as EditText).text.toString()
            val url = (view.findViewById<EditText>(R.id.d_image) as EditText).text.toString()
            dialog.dismiss()
            productListingActivityViewModel.onSave(name, desc, price, Integer.parseInt(r), url)
        }
        view.findViewById<View>(R.id.cancel_button).setOnClickListener {
            dialog.dismiss()
        }

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
