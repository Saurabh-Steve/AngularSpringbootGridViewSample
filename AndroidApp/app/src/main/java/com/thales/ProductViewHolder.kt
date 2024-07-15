package com.thales

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.thales.databinding.ItemListviewBinding

class ProductViewHolder(private val view: View, private  val binding: ItemListviewBinding) : RecyclerView.ViewHolder(view) {


    fun bindView(viewModel: ProductViewModel, m: (viewModel: ProductViewModel) -> Unit) {
        binding.vm = viewModel

        binding.editBtn.setOnClickListener {
            onClickAdd(viewModel, binding.editBtn.context, m)
        }
    }

    private fun onClickAdd(
        viewModel: ProductViewModel,
        context: Context,
        m: (viewModel: ProductViewModel) -> Unit
    ) {
        val dialog = Dialog(context)
        val view = LayoutInflater.from(context).inflate(R.layout.customdialoglayout, null)
        dialog.setContentView(view)
        (view.findViewById<EditText>(R.id.d_name) as EditText).setText(viewModel.productName)
        (view.findViewById<EditText>(R.id.d_price) as EditText).setText(viewModel.displayPrice)
        val desc = (view.findViewById<EditText>(R.id.d_desc) as EditText)
        desc.setText(viewModel.description)
        val r = (view.findViewById<EditText>(R.id.d_rating) as EditText)
        r.setText(viewModel.rating.toString())
        val url = (view.findViewById<EditText>(R.id.d_image) as EditText)
        url.setText(viewModel.featureImageUrl)

        dialog.show()


        view.findViewById<View>(R.id.save).setOnClickListener {
            var p1 = (view.findViewById<EditText>(R.id.d_name) as EditText).text.toString()
            var dp1 = (view.findViewById<EditText>(R.id.d_price) as EditText).text.toString()
            var desc = (view.findViewById<EditText>(R.id.d_desc) as EditText).text.toString()
            var rating = Integer.parseInt((view.findViewById<EditText>(R.id.d_rating) as EditText).text.toString())
            var featureImageUrl = (view.findViewById<EditText>(R.id.d_image) as EditText).text.toString()
            dialog.dismiss()
            m.invoke(ProductViewModel(viewModel.id, p1, desc,dp1, rating, featureImageUrl))
        }
        view.findViewById<View>(R.id.cancel_button).setOnClickListener {
            dialog.dismiss()
        }

    }

    companion object {
        const val PRODUCT = "product"
    }
}