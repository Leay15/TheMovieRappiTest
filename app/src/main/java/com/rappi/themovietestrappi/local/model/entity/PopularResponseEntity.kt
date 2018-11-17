package com.rappi.themovietestrappi.local.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.rappi.themovietestrappi.local.model.`object`.MovieResponseObject
import com.rappi.themovietestrappi.local.typeConvertes.CustomTypeDataConverter

@Entity(tableName = "popular_response")
data class PopularResponseEntity(
    @PrimaryKey
    @ColumnInfo(name = "page") val page: Int,
    @ColumnInfo(name = "total_results") val totalResults: Int,
    @ColumnInfo(name = "total_pages") val totalPages: Int,
    @TypeConverters(CustomTypeDataConverter::class)
    @ColumnInfo(name = "results") val results: List<MovieResponseObject>
)