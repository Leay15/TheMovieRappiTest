package com.rappi.themovietestrappi.popular.presentation.interactor

import com.google.gson.Gson
import com.rappi.themovietestrappi.local.model.`object`.MovieResponseObject
import com.rappi.themovietestrappi.local.model.entity.PopularResponseEntity
import com.rappi.themovietestrappi.local.room.dao.PopularMoviesDAO
import com.rappi.themovietestrappi.net.DataConfiguration
import com.rappi.themovietestrappi.net.model.response.ErrorResponse
import com.rappi.themovietestrappi.net.model.response.MovieResponse
import com.rappi.themovietestrappi.net.model.response.PopularResponse
import com.rappi.themovietestrappi.net.retrofit.interfaces.PopularMoviesService
import io.reactivex.Observable
import javax.inject.Inject


class PopularMoviesInteractorImpl @Inject constructor(
    private val popularMoviesService: PopularMoviesService,
    private val popularMoviesDAO: PopularMoviesDAO
) :
    PopularMoviesInteractor {

    override fun getPopularMovies(page: Int): Observable<PopularResponse> {
        return popularMoviesService.getPopularMovies(DataConfiguration.API_KEY, page)
            .flatMap {
                if (it.isSuccessful) {
                    savePopularMoviesResponse(it.body()!!)
                    Observable.just(it.body())
                } else {
                    val localResponseEntity = getPopularResponse(popularMoviesDAO.getPopularMovies(page))
                    if (localResponseEntity is PopularResponse) {
                        Observable.just(localResponseEntity)
                    } else {
                        val gson = Gson()
                        val error = gson.fromJson<ErrorResponse>(it.toString(), ErrorResponse::class.java)
                        Observable.error(Throwable(error.statusMessage))
                    }
                }
            }
    }


    fun savePopularMoviesResponse(popularResponse: PopularResponse) {
        val list = mutableListOf<MovieResponseObject>()

        list.addAll(popularResponse.results.map {
            MovieResponseObject(
                it.voteCount,
                it.id,
                it.video,
                it.voteAverage,
                it.title,
                it.popularity,
                it.posterPath,
                it.originalLanguage,
                it.originalTitle,
                it.genreIds,
                it.backdropPath,
                it.adult,
                it.overview,
                it.releaseDate
            )
        })

        val popularResponseEntity = PopularResponseEntity(
            popularResponse.page,
            popularResponse.totalResults,
            popularResponse.totalPages,
            list
        )

        popularMoviesDAO.insertPopularMovies(popularResponseEntity)
    }

    fun getPopularResponse(popularResponseEntity: PopularResponseEntity?): PopularResponse? {
        if (popularResponseEntity is PopularResponseEntity) {
            val list = mutableListOf<MovieResponse>()

            list.addAll(popularResponseEntity.results.map {
                MovieResponse(
                    it.voteCount,
                    it.id,
                    it.video,
                    it.voteAverage,
                    it.title,
                    it.popularity,
                    it.posterPath,
                    it.originalLanguage,
                    it.originalTitle,
                    it.genreIds,
                    it.backDropPath,
                    it.adult,
                    it.overview,
                    it.releaseDate
                )
            })

            val popularResponse = PopularResponse(
                popularResponseEntity.page,
                popularResponseEntity.totalResults,
                popularResponseEntity.totalPages,
                list
            )

            return popularResponse
        } else {
            return null
        }

    }
}