package com.thales.networking

class UpdateProductRequestDto(
    val id: String,
    val name:String,
    val description: String,
    val price: String,
    val rating: Int,
    val image: String,
)