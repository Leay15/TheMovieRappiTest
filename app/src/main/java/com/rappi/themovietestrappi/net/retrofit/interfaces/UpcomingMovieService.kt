package com.rappi.themovietestrappi.net.retrofit.interfaces

import com.rappi.themovietestrappi.net.DataConfiguration
import com.rappi.themovietestrappi.net.model.response.UpcomingResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UpcomingMovieService {

    @GET(DataConfiguration.UPCOMING)
    fun getUpcomingMovies(@Query("api_key") api_key: String, @Query("page") page: Int): Observable<Response<UpcomingResponse>>

}