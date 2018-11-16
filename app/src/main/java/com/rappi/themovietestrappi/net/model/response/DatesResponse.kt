package com.rappi.themovietestrappi.net.model.response

import com.google.gson.annotations.SerializedName

data class DatesResponse(
    @SerializedName("maximum") val _maximum: String?,
    @SerializedName("minimium") val _minimum: String?
) {
    val maximum: String
        get() = this._maximum ?: ""
    val minimum: String
        get() = this._minimum ?: ""
}