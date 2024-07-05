package com.thales

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.thales.databinding.ItemListviewBinding

class ProductViewHolder(private val view: View, private  val binding: ItemListviewBinding) : RecyclerView.ViewHolder(view) {

    fun bindView(viewModel: ProductViewModel) {
        binding.vm = viewModel

        binding.itemView.setOnClickListener {
        }
    }

    companion object {
        const val PRODUCT = "product"
    }
}