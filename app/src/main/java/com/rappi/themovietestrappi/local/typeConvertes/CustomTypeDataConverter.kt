package com.rappi.themovietestrappi.local.typeConvertes

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rappi.themovietestrappi.local.model.`object`.*


class CustomTypeDataConverter {


    //GenresItem
    @TypeConverter
    fun fromGenresItemList(list: List<GenresItemObject>): String {
        val gson = Gson()
        val type = object : TypeToken<List<GenresItemObject>>() {
        }.type
        return gson.toJson(list, type)
    }

    @TypeConverter
    fun toGenresItemList(listString: String): List<GenresItemObject> {
        val gson = Gson()
        val type = object : TypeToken<List<GenresItemObject>>() {
        }.type
        return gson.fromJson(listString, type)
    }

    @TypeConverter
    fun fromGenresItemObject(data: GenresItemObject): String? {
        val gson = Gson()
        val type = object : TypeToken<GenresItemObject>() {
        }.type
        return gson.toJson(data, type)
    }

    @TypeConverter
    fun toGenresItemObject(dataString: String): GenresItemObject {
        val gson = Gson()
        val type = object : TypeToken<List<GenresItemObject>>() {
        }.type
        return gson.fromJson(dataString, type)
    }


    //ProductionCountriesItemObject
    @TypeConverter
    fun fromProductionCountriesList(list: List<ProductionCountriesItemObject>): String {
        val gson = Gson()
        val type = object : TypeToken<List<ProductionCountriesItemObject>>() {
        }.type
        return gson.toJson(list, type)
    }

    @TypeConverter
    fun toProductionCountriesList(listString: String): List<ProductionCountriesItemObject> {
        val gson = Gson()
        val type = object : TypeToken<List<ProductionCountriesItemObject>>() {
        }.type
        return gson.fromJson(listString, type)
    }

    @TypeConverter
    fun fromProductionCountriesItemObject(data: ProductionCountriesItemObject): String? {
        val gson = Gson()
        val type = object : TypeToken<ProductionCountriesItemObject>() {
        }.type
        return gson.toJson(data, type)
    }

    @TypeConverter
    fun toProductionCountriesItemObject(dataString: String): ProductionCountriesItemObject {
        val gson = Gson()
        val type = object : TypeToken<List<ProductionCountriesItemObject>>() {
        }.type
        return gson.fromJson(dataString, type)
    }

    //SpokenLanguagesItemObject
    @TypeConverter
    fun fromSpokenLanguagesItemObjectList(list: List<SpokenLanguagesItemObject>): String {
        val gson = Gson()
        val type = object : TypeToken<List<SpokenLanguagesItemObject>>() {
        }.type
        return gson.toJson(list, type)
    }

    @TypeConverter
    fun toSpokenLanguagesItemObjectList(listString: String): List<SpokenLanguagesItemObject> {
        val gson = Gson()
        val type = object : TypeToken<List<SpokenLanguagesItemObject>>() {
        }.type
        return gson.fromJson(listString, type)
    }

    @TypeConverter
    fun fromSpokenLanguagesItemObject(data: SpokenLanguagesItemObject): String? {
        val gson = Gson()
        val type = object : TypeToken<SpokenLanguagesItemObject>() {
        }.type
        return gson.toJson(data, type)
    }

    @TypeConverter
    fun toSpokenLanguagesItemObject(dataString: String): SpokenLanguagesItemObject {
        val gson = Gson()
        val type = object : TypeToken<List<SpokenLanguagesItemObject>>() {
        }.type
        return gson.fromJson(dataString, type)
    }

    //ProductionCompaniesItemObject
    @TypeConverter
    fun fromProductionCompaniesItemObjectList(list: List<ProductionCompaniesItemObject>): String {
        val gson = Gson()
        val type = object : TypeToken<List<ProductionCompaniesItemObject>>() {
        }.type
        return gson.toJson(list, type)
    }

    @TypeConverter
    fun toProductionCompaniesItemObjectList(listString: String): List<ProductionCompaniesItemObject> {
        val gson = Gson()
        val type = object : TypeToken<List<ProductionCompaniesItemObject>>() {
        }.type
        return gson.fromJson(listString, type)
    }

    @TypeConverter
    fun fromProductionCompaniesItemObject(data: ProductionCompaniesItemObject): String? {
        val gson = Gson()
        val type = object : TypeToken<ProductionCompaniesItemObject>() {
        }.type
        return gson.toJson(data, type)
    }

    @TypeConverter
    fun toProductionCompaniesItemObject(dataString: String): ProductionCompaniesItemObject {
        val gson = Gson()
        val type = object : TypeToken<List<ProductionCompaniesItemObject>>() {
        }.type
        return gson.fromJson(dataString, type)
    }

    //MovieResponseObject
    @TypeConverter
    fun fromMovieResponseObjectList(list: List<MovieResponseObject>): String {
        val gson = Gson()
        val type = object : TypeToken<List<MovieResponseObject>>() {
        }.type
        return gson.toJson(list, type)
    }

    @TypeConverter
    fun toMovieResponseObjectList(listString: String): List<MovieResponseObject> {
        val gson = Gson()
        val type = object : TypeToken<List<MovieResponseObject>>() {
        }.type
        return gson.fromJson(listString, type)
    }

    @TypeConverter
    fun fromMovieResponseObject(data: MovieResponseObject): String? {
        val gson = Gson()
        val type = object : TypeToken<MovieResponseObject>() {
        }.type
        return gson.toJson(data, type)
    }

    @TypeConverter
    fun toMovieResponseObject(dataString: String): MovieResponseObject {
        val gson = Gson()
        val type = object : TypeToken<List<MovieResponseObject>>() {
        }.type
        return gson.fromJson(dataString, type)
    }

    //DatesResponseObject
    @TypeConverter
    fun fromDatesResponseObject(data: DatesResponseObject): String? {
        val gson = Gson()
        val type = object : TypeToken<DatesResponseObject>() {
        }.type
        return gson.toJson(data, type)
    }

    @TypeConverter
    fun toDatesResponseObject(dataString: String): DatesResponseObject {
        val gson = Gson()
        val type = object : TypeToken<List<DatesResponseObject>>() {
        }.type
        return gson.fromJson(dataString, type)
    }

    //Any
    @TypeConverter
    fun fromAny(data: Any): String? {
        val gson = Gson()
        val type = object : TypeToken<Any>() {
        }.type
        return gson.toJson(data, type)
    }

    @TypeConverter
    fun toAny(dataString: String): Any {
        val gson = Gson()
        val type = object : TypeToken<List<Any>>() {
        }.type
        return gson.fromJson(dataString, type)
    }

}