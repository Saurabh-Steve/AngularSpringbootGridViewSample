package com.thales

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class ProductViewModel(
    val id: String,
    var productName:String,
    val description: String,
    val displayPrice: String,
    val rating: Int,
    val featureImageUrl: String,
) : Parcelable
