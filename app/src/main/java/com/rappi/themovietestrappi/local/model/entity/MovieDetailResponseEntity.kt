package com.rappi.themovietestrappi.local.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.rappi.themovietestrappi.local.model.`object`.GenresItemObject
import com.rappi.themovietestrappi.local.model.`object`.ProductionCompaniesItemObject
import com.rappi.themovietestrappi.local.model.`object`.ProductionCountriesItemObject
import com.rappi.themovietestrappi.local.model.`object`.SpokenLanguagesItemObject
import com.rappi.themovietestrappi.local.typeConvertes.CustomTypeDataConverter

@Entity(tableName = "movie_detail")
data class MovieDetailResponseEntity(
    @ColumnInfo(name = "original_language") val originalLanguage: String,
    @ColumnInfo(name = "imdb_id") val imdbId: String,
    @ColumnInfo(name = "video") val video: Boolean,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "backdrop_path") val backdropPath: String,
    @ColumnInfo(name = "revenue") val revenue: Int,
    @ColumnInfo(name = "genres")
    @TypeConverters(CustomTypeDataConverter::class) val genres: List<GenresItemObject>,
    @ColumnInfo(name = "popularity") val popularity: Double,
    @TypeConverters(CustomTypeDataConverter::class)
    @ColumnInfo(name = "production_countries") val productionCountries: List<ProductionCountriesItemObject>,
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "vote_count") val voteCount: Int,
    @ColumnInfo(name = "budget") val budget: Int,
    @ColumnInfo(name = "overview") val overview: String,
    @ColumnInfo(name = "original_title") val originalTitle: String,
    @ColumnInfo(name = "runtime") val runtime: Int,
    @ColumnInfo(name = "poster_path") val posterPath: String,
    @TypeConverters(CustomTypeDataConverter::class)
    @ColumnInfo(name = "spoken_languages") val spokenLanguages: List<SpokenLanguagesItemObject>,
    @TypeConverters(CustomTypeDataConverter::class)
    @ColumnInfo(name = "production_companies") val productionCompanies: List<ProductionCompaniesItemObject>,
    @ColumnInfo(name = "release_date") val releaseDate: String,
    @ColumnInfo(name = "vote_average") val voteAverage: Double,
    @ColumnInfo(name = "belongs_to_collection") val belongsToCollection: Any,
    @ColumnInfo(name = "tagline") val tagline: String,
    @ColumnInfo(name = "adult") val adult: Boolean,
    @ColumnInfo(name = "homepage") val homepage: String,
    @ColumnInfo(name = "status") val status: String
)