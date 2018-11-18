package com.rappi.themovietestrappi.main.presentation.interactor.genres

import com.google.gson.Gson
import com.rappi.themovietestrappi.local.model.`object`.GenresItemObject
import com.rappi.themovietestrappi.local.model.entity.GenresResponseEntity
import com.rappi.themovietestrappi.local.room.dao.GetMoviesGenresDAO
import com.rappi.themovietestrappi.net.DataConfiguration
import com.rappi.themovietestrappi.net.model.response.ErrorResponse
import com.rappi.themovietestrappi.net.model.response.GenresItem
import com.rappi.themovietestrappi.net.model.response.GenresResponse
import com.rappi.themovietestrappi.net.retrofit.interfaces.GetMoviesGenresService
import io.reactivex.Observable
import javax.inject.Inject

class GenresInteractorImpl @Inject constructor(
    private val genresService: GetMoviesGenresService,
    private val genresDAO: GetMoviesGenresDAO
) :
    GenresInteractor {

    override fun getGenres(): Observable<GenresResponse> {
        return genresService.getMovieGenres(DataConfiguration.API_KEY).flatMap {
            if (it.isSuccessful) {
                saveGenres(it.body()!!)
                Observable.just(it.body())
            } else {
                val localResponse = getGenres(genresDAO.getMoviesGenres())
                if (localResponse is GenresResponse) {
                    Observable.just(localResponse)
                } else {
                    val error = Gson().fromJson<ErrorResponse>(it.toString(), ErrorResponse::class.java)
                    Observable.error(Throwable(error.statusMessage))
                }
            }
        }
    }

    fun saveGenres(genresResponse: GenresResponse) {
        val genresList = mutableListOf<GenresItemObject>()

        genresList.addAll(genresResponse.genres.map {
            GenresItemObject(
                it.name,
                it.id
            )
        })

        val list = genresList as List<GenresItemObject>

        val genres = GenresResponseEntity(0, list)

        genresDAO.insertMoviesGenres(genres)
    }

    fun getGenres(genresResponseEntity: GenresResponseEntity?): GenresResponse? {
        if (genresResponseEntity is GenresResponseEntity) {
            val genresList = mutableListOf<GenresItem>()

            genresList.addAll(genresResponseEntity.genres.map {
                GenresItem(
                    it.name,
                    it.id
                )
            })

            return GenresResponse(genresList)
        } else {
            return null
        }
    }
}