package com.thales

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thales.databinding.ItemListviewBinding

class ProductListingAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var products : List<ProductViewModel> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_listview, parent, false)
        val binding = ItemListviewBinding.bind(view)

        return  ProductViewHolder(view, binding)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ProductViewHolder).bindView(products[position])
    }

    fun setItems(products: List<ProductViewModel>) {
        this.products = products
        notifyDataSetChanged()
    }
}