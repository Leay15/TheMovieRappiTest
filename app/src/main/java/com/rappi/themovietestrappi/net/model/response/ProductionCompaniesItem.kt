package com.rappi.themovietestrappi.net.model.response

import com.google.gson.annotations.SerializedName

data class ProductionCompaniesItem(
    @SerializedName("logo_path") val _logoPath: String?,
    @SerializedName("name") val _name: String?,
    @SerializedName("id") val _id: Int?,
    @SerializedName("origin_country") val _originCountry: String?
) {
    val logoPath: String
        get() = this._logoPath ?: ""
    val name: String
        get() = this._name ?: ""
    val id: Int
        get() = this._id ?: -1
    val originCountry: String
        get() = this._originCountry ?: ""
}