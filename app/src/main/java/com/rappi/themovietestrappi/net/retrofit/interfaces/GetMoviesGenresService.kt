package com.rappi.themovietestrappi.net.retrofit.interfaces

import com.rappi.themovietestrappi.net.DataConfiguration
import com.rappi.themovietestrappi.net.model.response.GenresResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GetMoviesGenresService {

    @GET(DataConfiguration.GENRES)
    fun getMovieGenres(@Query("api_key") api_key: String): Observable<Response<GenresResponse>>
}