package com.thales

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thales.databinding.ItemListviewBinding
import com.thales.networking.Product

class ProductListingAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var products : List<ProductViewModel> = emptyList()
    lateinit var m: (vm:ProductViewModel) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_listview, parent, false)
        val binding = ItemListviewBinding.bind(view)

        return  ProductViewHolder(view, binding)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ProductViewHolder).bindView(products[position], m)
    }

    fun setItems(products: List<ProductViewModel>) {
        this.products = products
        notifyDataSetChanged()
    }

    fun setOnEditClickListener(m: (vm:ProductViewModel) -> Unit) {
        this.m = m;
    }
}