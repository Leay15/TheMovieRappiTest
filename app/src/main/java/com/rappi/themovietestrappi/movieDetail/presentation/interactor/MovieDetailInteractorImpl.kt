package com.rappi.themovietestrappi.movieDetail.presentation.interactor

import com.google.gson.Gson
import com.rappi.themovietestrappi.local.model.`object`.GenresItemObject
import com.rappi.themovietestrappi.local.model.`object`.ProductionCompaniesItemObject
import com.rappi.themovietestrappi.local.model.`object`.ProductionCountriesItemObject
import com.rappi.themovietestrappi.local.model.`object`.SpokenLanguagesItemObject
import com.rappi.themovietestrappi.local.model.entity.MovieDetailResponseEntity
import com.rappi.themovietestrappi.local.room.dao.MovieDetailDAO
import com.rappi.themovietestrappi.net.DataConfiguration
import com.rappi.themovietestrappi.net.model.response.*
import com.rappi.themovietestrappi.net.retrofit.interfaces.MovieDetailService
import io.reactivex.Observable
import javax.inject.Inject

class MovieDetailInteractorImpl @Inject constructor(
    private val movieDetailService: MovieDetailService,
    private val movieDetailDAO: MovieDetailDAO
) :
    MovieDetailInteractor {

    override fun getMovieDetail(movieId: Int): Observable<MovieDetailResponse> {
        return movieDetailService.getMovieDetail(movieId, DataConfiguration.API_KEY)
            .flatMap {
                if (it.isSuccessful) {
                    Observable.just(it.body())
                } else {
                    val error = Gson().fromJson<ErrorResponse>(it.toString(), ErrorResponse::class.java)
                    Observable.error(Throwable(error.statusMessage))
                }
            }
    }

    fun saveMovieDetail(movieDetailResponse: MovieDetailResponse) {
        val genres = mutableListOf<GenresItemObject>()

        genres.addAll(movieDetailResponse.genres.map {
            GenresItemObject(
                it.name,
                it.id
            )
        }
        )

        val productionCountries = mutableListOf<ProductionCountriesItemObject>()

        productionCountries.addAll(movieDetailResponse.productionCountries.map {
            ProductionCountriesItemObject(
                it.iso31661,
                it.name
            )
        })


        val productionCompanies = mutableListOf<ProductionCompaniesItemObject>()

        productionCompanies.addAll(movieDetailResponse.productionCompanies.map {
            ProductionCompaniesItemObject(
                it.logoPath,
                it.name,
                it.id,
                it.originCountry
            )
        })

        val spokenLanguages = mutableListOf<SpokenLanguagesItemObject>()

        spokenLanguages.addAll(movieDetailResponse.spokenLanguages.map {
            SpokenLanguagesItemObject(
                it.name,
                it.iso6391
            )
        })

        val movieDetailResponseEntity = MovieDetailResponseEntity(
            movieDetailResponse.originalLanguage,
            movieDetailResponse.imdbId,
            movieDetailResponse.video,
            movieDetailResponse.title,
            movieDetailResponse.backdropPath,
            movieDetailResponse.revenue,
            genres,
            movieDetailResponse.popularity,
            productionCountries,
            movieDetailResponse.id,
            movieDetailResponse.voteCount,
            movieDetailResponse.budget,
            movieDetailResponse.overview,
            movieDetailResponse.originalTitle,
            movieDetailResponse.runtime,
            movieDetailResponse.posterPath,
            spokenLanguages,
            productionCompanies,
            movieDetailResponse.releaseDate,
            movieDetailResponse.voteAverage,
            movieDetailResponse.belongsToCollection!!,
            movieDetailResponse.tagline,
            movieDetailResponse.adult,
            movieDetailResponse.homepage,
            movieDetailResponse.status
        )

        movieDetailDAO.insertMovieDetail(movieDetailResponseEntity)
    }

    fun getMovieDetail(movieDetailResponseEntity: MovieDetailResponseEntity?): MovieDetailResponse? {
        if (movieDetailResponseEntity is MovieDetailResponseEntity) {
            val genres = mutableListOf<GenresItem>()

            genres.addAll(movieDetailResponseEntity.genres.map {
                GenresItem(
                    it.name,
                    it.id
                )
            }
            )

            val productionCountries = mutableListOf<ProductionCountriesItem>()

            productionCountries.addAll(movieDetailResponseEntity.productionCountries.map {
                ProductionCountriesItem(
                    it.iso31661,
                    it.name
                )
            })


            val productionCompanies = mutableListOf<ProductionCompaniesItem>()

            productionCompanies.addAll(movieDetailResponseEntity.productionCompanies.map {
                ProductionCompaniesItem(
                    it.logoPath,
                    it.name,
                    it.id,
                    it.originCountry
                )
            })

            val spokenLanguages = mutableListOf<SpokenLanguagesItem>()

            spokenLanguages.addAll(movieDetailResponseEntity.spokenLanguages.map {
                SpokenLanguagesItem(
                    it.name,
                    it.iso6391
                )
            })

            val movieDetailResponse = MovieDetailResponse(
                movieDetailResponseEntity.originalLanguage,
                movieDetailResponseEntity.imdbId,
                movieDetailResponseEntity.video,
                movieDetailResponseEntity.title,
                movieDetailResponseEntity.backdropPath,
                movieDetailResponseEntity.revenue,
                genres,
                movieDetailResponseEntity.popularity,
                productionCountries,
                movieDetailResponseEntity.id,
                movieDetailResponseEntity.voteCount,
                movieDetailResponseEntity.budget,
                movieDetailResponseEntity.overview,
                movieDetailResponseEntity.originalTitle,
                movieDetailResponseEntity.runtime,
                movieDetailResponseEntity.posterPath,
                spokenLanguages,
                productionCompanies,
                movieDetailResponseEntity.releaseDate,
                movieDetailResponseEntity.voteAverage,
                movieDetailResponseEntity.belongsToCollection!!,
                movieDetailResponseEntity.tagline,
                movieDetailResponseEntity.adult,
                movieDetailResponseEntity.homepage,
                movieDetailResponseEntity.status
            )

            return movieDetailResponse
        } else {
            return null
        }
    }
}