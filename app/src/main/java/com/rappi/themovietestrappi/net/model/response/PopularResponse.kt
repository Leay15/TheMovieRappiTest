package com.rappi.themovietestrappi.net.model.response

import com.google.gson.annotations.SerializedName

data class PopularResponse(
    @SerializedName("page") val _page: Int?,
    @SerializedName("total_results") val _totalResults: Int?,
    @SerializedName("total_pages") val _totalPages: Int?,
    @SerializedName("results") val _results: List<MovieResponse>?
) {

    val page: Int
        get() = this._page ?: 0
    val totalResults: Int
        get() = this._totalResults ?: 0
    val totalPages: Int
        get() = this._totalPages ?: 0
    val results: List<MovieResponse>
        get() = this._results ?: emptyList()
}