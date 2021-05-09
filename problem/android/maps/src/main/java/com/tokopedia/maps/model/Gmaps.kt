package com.tokopedia.maps.model

import com.google.gson.annotations.SerializedName

data class Gmaps(
	@field:SerializedName("Gmaps")
	val gmaps: List<GmapsItem?>? = null
)

