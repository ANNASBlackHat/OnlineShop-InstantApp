package com.annasblackhat.onlineshop

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by annasblackhat on 25/03/18.
 */

data class Product (
        @field:SerializedName("sold")
        val sold: Int? = null,

        @field:SerializedName("product")
        val product: String? = null,

        @field:SerializedName("images")
        val images: List<String?>? = null,

        @field:SerializedName("price")
        val price: Int? = null,

        @field:SerializedName("rating")
        val rating: Double? = null,

        @field:SerializedName("description")
        val description: String? = null,

        @field:SerializedName("id")
        val id: Int? = null,

        @field:SerializedName("likes")
        val likes: Int? = null
): Serializable