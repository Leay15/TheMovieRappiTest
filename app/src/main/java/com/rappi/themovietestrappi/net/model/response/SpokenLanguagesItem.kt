package com.rappi.themovietestrappi.net.model.response

import com.google.gson.annotations.SerializedName

data class SpokenLanguagesItem(
    @SerializedName("name") val _name: String?,
    @SerializedName("iso_639_1") val _iso6391: String?
) {
    val name: String
        get() = this._name ?: ""
    val iso6391: String
        get() = this._iso6391 ?: ""
}