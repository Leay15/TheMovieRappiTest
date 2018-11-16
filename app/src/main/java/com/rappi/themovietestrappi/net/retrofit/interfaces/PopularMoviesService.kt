package com.rappi.themovietestrappi.net.retrofit.interfaces

import com.rappi.themovietestrappi.net.DataConfiguration
import com.rappi.themovietestrappi.net.model.response.PopularResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PopularMoviesService {

    @GET(DataConfiguration.POPULAR)
    fun getPopularMovies(@Query("api_key") api_key: String, @Query("page") page: Int): Observable<Response<PopularResponse>>

}