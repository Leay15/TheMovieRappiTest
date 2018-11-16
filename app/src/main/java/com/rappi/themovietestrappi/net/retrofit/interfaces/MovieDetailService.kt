package com.rappi.themovietestrappi.net.retrofit.interfaces

import com.rappi.themovietestrappi.net.DataConfiguration
import com.rappi.themovietestrappi.net.model.response.MovieDetailResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDetailService {

    @GET(DataConfiguration.MOVIE_DETAIL)
    fun getMovieDetail(@Path("movie_id") movie_id: Int, @Query("api_key") api_key: String): Observable<Response<MovieDetailResponse>>

}