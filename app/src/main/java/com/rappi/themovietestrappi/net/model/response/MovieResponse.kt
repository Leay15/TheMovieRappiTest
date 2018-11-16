package com.rappi.themovietestrappi.net.model.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("vote_count") val _voteCount: Int?,
    @SerializedName("id") val _id: Int?,
    @SerializedName("video") val _video: Boolean?,
    @SerializedName("vote_average") val _voteAverage: Double?,
    @SerializedName("title") val _title: String?,
    @SerializedName("popularity") val _popularity: Double?,
    @SerializedName("poster_path") val _posterPath: String?,
    @SerializedName("original_language") val _originalLanguage: String?,
    @SerializedName("original_title") val _originalTitle: String?,
    @SerializedName("genre_ids") val _genreIds: List<Int>?,
    @SerializedName("backdrop_path") val _backDropPath: String?,
    @SerializedName("adult") val _adult: Boolean?,
    @SerializedName("overview") val _overview: String?,
    @SerializedName("release_date") val _releaseDate: String?
) {

    val voteCount: Int
        get() = this._voteCount ?: -1
    val id: Int
        get() = this._id ?: -1
    val video: Boolean
        get() = this._video ?: false
    val voteAverage: Double
        get() = this._voteAverage ?: -1.0
    val title: String
        get() = this._title ?: ""
    val popularity: Double
        get() = this._popularity ?: -1.0
    val posterPath: String
        get() = this._posterPath ?: ""
    val originalLanguage: String
        get() = this._originalLanguage ?: ""
    val originalTitle: String
        get() = this._originalTitle ?: ""
    val genreIds: List<Int>
        get() = this._genreIds ?: emptyList()
    val backdropPath: String
        get() = this._backDropPath ?: ""
    val adult: Boolean
        get() = this._adult ?: false
    val overview: String
        get() = this._overview ?: ""
    val releaseDate: String
        get() = this._releaseDate ?: ""
}