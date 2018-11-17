package com.rappi.themovietestrappi.upcoming.presentation.interactor

import com.google.gson.Gson
import com.rappi.themovietestrappi.local.model.`object`.DatesResponseObject
import com.rappi.themovietestrappi.local.model.`object`.MovieResponseObject
import com.rappi.themovietestrappi.local.model.entity.UpcomingResponseEntity
import com.rappi.themovietestrappi.local.room.dao.UpcomingMovieDAO
import com.rappi.themovietestrappi.net.DataConfiguration
import com.rappi.themovietestrappi.net.model.response.DatesResponse
import com.rappi.themovietestrappi.net.model.response.ErrorResponse
import com.rappi.themovietestrappi.net.model.response.MovieResponse
import com.rappi.themovietestrappi.net.model.response.UpcomingResponse
import com.rappi.themovietestrappi.net.retrofit.interfaces.UpcomingMovieService
import io.reactivex.Observable
import javax.inject.Inject

class UpcomingInteractorImpl @Inject constructor(
    private val upcomingMovieService: UpcomingMovieService,
    private val upcomingMovieDAO: UpcomingMovieDAO
) :
    UpcomingInteractor {

    override fun getUpcomingMovies(page: Int): Observable<UpcomingResponse> {
        return upcomingMovieService.getUpcomingMovies(DataConfiguration.API_KEY, page)
            .flatMap {
                if (it.isSuccessful) {
                    saveUpcomingMovies(it.body()!!)
                    Observable.just(it.body())
                } else {
                    val localResponse = getUpcomingMovies(upcomingMovieDAO.getUpcomingMovie(page))
                    if (localResponse is UpcomingResponse) {
                        Observable.just(localResponse)
                    } else {
                        val error = Gson().fromJson<ErrorResponse>(it.toString(), ErrorResponse::class.java)
                        Observable.error(Throwable(error.statusMessage))
                    }
                }
            }
    }

    private fun saveUpcomingMovies(upcomingResponse: UpcomingResponse) {
        val list = mutableListOf<MovieResponseObject>()

        list.addAll(upcomingResponse.results.map {
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

        val upcomingResponseEntity = UpcomingResponseEntity(
            list,
            upcomingResponse.page,
            upcomingResponse.totalResults,
            upcomingResponse.totalPages,
            DatesResponseObject(
                upcomingResponse.dates.maximum,
                upcomingResponse.dates.minimum
            )
        )

        upcomingMovieDAO.insertUpcomingMovie(upcomingResponseEntity)
    }

    private fun getUpcomingMovies(upcomingResponseEntity: UpcomingResponseEntity?): UpcomingResponse? {
        if (upcomingResponseEntity is UpcomingResponseEntity) {
            val list = mutableListOf<MovieResponse>()

            list.addAll(upcomingResponseEntity.results.map {
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

            val upcomingResponse = UpcomingResponse(
                list,
                upcomingResponseEntity.page,
                upcomingResponseEntity.totalResults,
                upcomingResponseEntity.totalPages,
                DatesResponse(upcomingResponseEntity.dates.maximum, upcomingResponseEntity.dates.minimum)
            )

            return upcomingResponse
        } else {
            return null
        }
    }
}