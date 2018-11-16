package com.rappi.themovietestrappi.net.model.response

import com.google.gson.annotations.SerializedName

data class ProductionCountriesItem(
    @SerializedName("iso_3166_1") val _iso31661: String?,
    @SerializedName("name") val _name: String?
) {
    val iso31661: String
        get() = this._iso31661 ?: ""
    val name: String
        get() = this._name ?: ""
}