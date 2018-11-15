package com.rappi.themovietestrappi.net.model.response

import com.google.gson.annotations.SerializedName

data class SpokenLanguagesItem(

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("iso_639_1")
    val iso6391: String? = null
)