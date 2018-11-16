package com.rappi.themovietestrappi.net.model.response

import com.google.gson.annotations.SerializedName

data class MovieDetailResponse(
    @SerializedName("original_language") val _originalLanguage: String?,
    @SerializedName("imdb_id") val _imdbId: String?,
    @SerializedName("video") val _video: Boolean?,
    @SerializedName("title") val _title: String?,
    @SerializedName("backdrop_path") val _backdropPath: String?,
    @SerializedName("revenue") val _revenue: Int?,
    @SerializedName("genres") val _genres: List<GenresItem>?,
    @SerializedName("popularity") val _popularity: Double?,
    @SerializedName("production_countries") val _productionCountries: List<ProductionCountriesItem>?,
    @SerializedName("id") val _id: Int?,
    @SerializedName("vote_count") val _voteCount: Int?,
    @SerializedName("budget") val _budget: Int?,
    @SerializedName("overview") val _overview: String?,
    @SerializedName("original_title") val _originalTitle: String?,
    @SerializedName("runtime") val _runtime: Int?,
    @SerializedName("poster_path") val _posterPath: String?,
    @SerializedName("spoken_languages") val _spokenLanguages: List<SpokenLanguagesItem>?,
    @SerializedName("production_companies") val _productionCompanies: List<ProductionCompaniesItem>?,
    @SerializedName("release_date") val _releaseDate: String?,
    @SerializedName("vote_average") val _voteAverage: Double?,
    @SerializedName("belongs_to_collection") val _belongsToCollection: Any?,
    @SerializedName("tagline") val _tagline: String?,
    @SerializedName("adult") val _adult: Boolean?,
    @SerializedName("homepage") val _homepage: String?,
    @SerializedName("status") val _status: String?
) {
    val originalLanguage: String
        get() = this._originalLanguage ?: ""
    val imdbId: String
        get() = this._imdbId ?: ""
    val video: Boolean
        get() = this._video ?: false
    val title: String
        get() = this._title ?: ""
    val backdropPath: String
        get() = this._backdropPath ?: ""
    val revenue: Int
        get() = this._revenue ?: -1
    val genres: List<GenresItem>
        get() = this._genres ?: emptyList()
    val popularity: Double
        get() = this._popularity ?: -1.0
    val productionCountries: List<ProductionCountriesItem>
        get() = this._productionCountries ?: emptyList()
    val id: Int
        get() = this._id ?: -1
    val voteCount: Int
        get() = this._voteCount ?: -1
    val budget: Int
        get() = this._budget ?: -1
    val overview: String
        get() = this._overview ?: ""
    val originalTitle: String
        get() = this._originalTitle ?: ""
    val runtime: Int
        get() = this._runtime ?: -1
    val posterPath: String
        get() = this._posterPath ?: ""
    val spokenLanguages: List<SpokenLanguagesItem>
        get() = this._spokenLanguages ?: emptyList()
    val productionCompanies: List<ProductionCompaniesItem>
        get() = this._productionCompanies ?: emptyList()
    val releaseDate: String
        get() = this._releaseDate ?: ""
    val voteAverage: Double
        get() = this._voteAverage ?: -1.0
    val belongsToCollection: Any?
        get() = this._belongsToCollection
    val tagline: String
        get() = this._tagline ?: ""
    val adult: Boolean
        get() = this._adult ?: false
    val homepage: String
        get() = this._homepage ?: ""
    val status: String
        get() = this._homepage ?: ""
}