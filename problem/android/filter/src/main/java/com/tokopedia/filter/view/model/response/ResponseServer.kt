package com.tokopedia.filter.view.model.response

import com.google.gson.annotations.SerializedName
import com.tokopedia.filter.view.model.Data

data class ResponseServer(
        @field: SerializedName("status_code")
        val statusCode: Int? = null,

        @field: SerializedName("message")
        val message: String? = null,

        @field: SerializedName("data")
        val data: Data? = null
)
