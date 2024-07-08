package com.thales.networking

data class AddProductRequestDto (
    val name:String,
    val description: String,
    val price: String,
    val rating: Int,
    val image: String,
)