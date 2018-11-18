package com.rappi.themovietestrappi.topRated.presentation.interactor

import com.google.gson.Gson
import com.rappi.themovietestrappi.local.model.`object`.MovieResponseObject
import com.rappi.themovietestrappi.local.model.entity.TopRatedResponseEntity
import com.rappi.themovietestrappi.local.room.dao.TopRatedMovieDAO
import com.rappi.themovietestrappi.net.DataConfiguration
import com.rappi.themovietestrappi.net.model.response.ErrorResponse
import com.rappi.themovietestrappi.net.model.response.MovieResponse
import com.rappi.themovietestrappi.net.model.response.TopRatedResponse
import com.rappi.themovietestrappi.net.retrofit.interfaces.TopRatedMovieService
import io.reactivex.Observable
import javax.inject.Inject

class TopRatedInteractorImpl @Inject constructor(
    private val topRatedMovieService: TopRatedMovieService,
    private val topRatedMovieDAO: TopRatedMovieDAO
) :
    TopRatedInteractor {

    override fun getTopRatedMoview(page: Int): Observable<TopRatedResponse> {
        return topRatedMovieService.getTopRatedMovies(DataConfiguration.API_KEY, page)
            .flatMap {
                if (it.isSuccessful) {
                    saveTopRatedResponse(it.body()!!)
                    Observable.just(it.body())
                } else {
                    val localResponse = getTopRatedResponse(topRatedMovieDAO.getTopRated(page))
                    if (localResponse is TopRatedResponse) {
                        Observable.just(it.body())
                    } else {
                        val error = Gson().fromJson<ErrorResponse>(it.toString(), ErrorResponse::class.java)
                        Observable.error(Throwable(error.statusMessage))
                    }
                }
            }
    }

    fun saveTopRatedResponse(topRatedResponse: TopRatedResponse) {
        val list = mutableListOf<MovieResponseObject>()

        list.addAll(topRatedResponse.results.map {
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

        val topRatedResponseEntity = TopRatedResponseEntity(
            topRatedResponse.page,
            topRatedResponse.totalResults,
            topRatedResponse.totalPages,
            list
        )

        topRatedMovieDAO.insertTopRated(topRatedResponseEntity)
    }

    fun getTopRatedResponse(topRatedResponseEntity: TopRatedResponseEntity?): TopRatedResponse? {
        if (topRatedResponseEntity is TopRatedResponseEntity) {
            val list = mutableListOf<MovieResponse>()

            list.addAll(topRatedResponseEntity.results.map {
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

            val topRatedResponse = TopRatedResponse(
                topRatedResponseEntity.page,
                topRatedResponseEntity.totalResults,
                topRatedResponseEntity.totalPages,
                list
            )

            return topRatedResponse
        } else {
            return null
        }

    }
}