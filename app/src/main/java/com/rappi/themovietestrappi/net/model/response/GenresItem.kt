package com.rappi.themovietestrappi.net.model.response

import com.google.gson.annotations.SerializedName

data class GenresItem(

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("id")
    val id: Int? = null
)