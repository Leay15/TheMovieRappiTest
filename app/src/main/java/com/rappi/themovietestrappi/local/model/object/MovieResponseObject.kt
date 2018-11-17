package com.rappi.themovietestrappi.local.model.`object`

import androidx.room.TypeConverters
import com.rappi.themovietestrappi.local.typeConvertes.CustomTypeDataConverter

data class MovieResponseObject(
    val voteCount: Int,
    val id: Int,
    val video: Boolean,
    val voteAverage: Double,
    val title: String,
    val popularity: Double,
    val posterPath: String,
    val originalLanguage: String,
    val originalTitle: String,
    @TypeConverters(CustomTypeDataConverter::class)
    val genreIds: List<Int>,
    val backDropPath: String,
    val adult: Boolean,
    val overview: String,
    val releaseDate: String
)