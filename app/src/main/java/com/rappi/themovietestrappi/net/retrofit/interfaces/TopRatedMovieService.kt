package com.rappi.themovietestrappi.net.retrofit.interfaces

import com.rappi.themovietestrappi.net.DataConfiguration
import com.rappi.themovietestrappi.net.model.response.TopRatedResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TopRatedMovieService {

    @GET(DataConfiguration.TOP_RATED)
    fun getTopRatedMovies(@Query("api_key") api_key: String, @Query("page") page: Int): Observable<Response<TopRatedResponse>>

}