package com.rappi.themovietestrappi.net.model.response

import com.google.gson.annotations.SerializedName

data class GenresItem(
    @SerializedName("name") val _name: String?,
    @SerializedName("id") val _id: Int?
) {
    val name: String
        get() = this._name ?: ""
    val id: Int
        get() = this._id ?: -1
}