package com.rappi.themovietestrappi.net.model.response

import com.google.gson.annotations.SerializedName

data class ProductionCompaniesItem(

    @SerializedName("logo_path")
    val logoPath: String? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("origin_country")
    val originCountry: String? = null
)