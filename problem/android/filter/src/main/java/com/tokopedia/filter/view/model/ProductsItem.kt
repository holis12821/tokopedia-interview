package com.tokopedia.filter.view.model

import com.google.gson.annotations.SerializedName

data class ProductsItem(
        @field: SerializedName("id")
        val id: Int? = null,

        @field: SerializedName("name")
        val name: String? = null,

        @field: SerializedName("imageUrl")
        val imageUrl: String? = null,

        @field: SerializedName("priceInt")
        val priceInt: Int? = null,

        @field: SerializedName("discountPercentage")
        val discountPercentage: Int? = null,

        @field: SerializedName("slashedPriceInt")
        val slashedPriceInt: Int? = null,

        @field: SerializedName("shop")
        val shop: Shop? = null
)
