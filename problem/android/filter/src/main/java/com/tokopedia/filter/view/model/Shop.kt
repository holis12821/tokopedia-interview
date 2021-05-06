package com.tokopedia.filter.view.model

import com.google.gson.annotations.SerializedName

data class Shop(
        @field: SerializedName("id")
        val id: Int? = null,

        @field: SerializedName("name")
        val name: String? = null,

        @field: SerializedName("city")
        val city: String? = null
)
