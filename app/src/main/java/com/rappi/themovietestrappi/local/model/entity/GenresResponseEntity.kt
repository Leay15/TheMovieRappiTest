package com.rappi.themovietestrappi.local.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.rappi.themovietestrappi.local.model.`object`.GenresItemObject
import com.rappi.themovietestrappi.local.typeConvertes.CustomTypeDataConverter

@Entity(tableName = "genres_response")

data class GenresResponseEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "genres")
    @TypeConverters(CustomTypeDataConverter::class) val genres: List<GenresItemObject>
) {
}