package com.rappi.themovietestrappi.net.model.response

import com.google.gson.annotations.SerializedName

data class ProductionCountriesItem(

    @SerializedName("iso_3166_1")
    val iso31661: String? = null,

    @SerializedName("name")
    val name: String? = null
)