package com.tokopedia.filter.view.model

import com.google.gson.annotations.SerializedName

data class Data(
        @field:SerializedName("products")
        val products: List<ProductsItem?>? = null
)
