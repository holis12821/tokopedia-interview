package com.tokopedia.maps.model

import com.google.gson.annotations.SerializedName

data class Translations(
        @field:SerializedName("de")
        val de: String? = null,

        @field:SerializedName("ja")
        val ja: String? = null,

        @field:SerializedName("it")
        val it: String? = null,

        @field:SerializedName("fr")
        val fr: String? = null,

        @field:SerializedName("es")
        val es: String? = null
)
