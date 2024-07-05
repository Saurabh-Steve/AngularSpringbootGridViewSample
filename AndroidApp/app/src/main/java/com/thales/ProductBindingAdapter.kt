package com.thales

import android.graphics.drawable.Drawable
import android.text.Html
import android.text.Spannable
import android.text.Spanned
import android.text.style.StrikethroughSpan
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

object ProductBindingAdapter {

    @BindingAdapter("text")
    @JvmStatic
    fun setText(myTextView: TextView, value: String) {
        myTextView.setText(value, TextView.BufferType.SPANNABLE)
        val STRIKE_THROUGH_SPAN = StrikethroughSpan()
        val spannable: Spannable = myTextView.text as Spannable
        spannable.setSpan(
            STRIKE_THROUGH_SPAN,
            0,
            myTextView.length(),
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        myTextView.text = spannable
    }

    @BindingAdapter("textHtml")
    @JvmStatic
    fun setHtmlText(myTextView: TextView, value: String?) {
        myTextView.text = Html.fromHtml(value);

    }

    @BindingAdapter("imageUrl")
    @JvmStatic
    fun setImageUrl(imageView: ImageView, imageUrl: String?) {
        if (imageUrl != null)
            Glide
                .with(imageView.context)
                .load(imageUrl)
                .placeholder(R.drawable.error_state)
                .addListener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        imageView.setImageDrawable(imageView.context.getDrawable(R.drawable.placeholder))
                        e?.printStackTrace()
                        return true
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        imageView.setImageDrawable(resource)
                        imageView.scaleType = ImageView.ScaleType.FIT_CENTER
                        return true
                    }
                })
                .into(imageView)
    }
}
