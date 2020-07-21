package com.rappi.themovietestrappi.main.viewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rappi.themovietestrappi.core.getApplicationComponent
import com.rappi.themovietestrappi.main.component.DaggerMainComponent
import com.rappi.themovietestrappi.main.module.MainModule
import com.rappi.themovietestrappi.main.presentation.presenter.genres.GenresPresenter
import com.rappi.themovietestrappi.net.model.response.GenresResponse
import com.rappi.themovietestrappi.net.model.response.PopularResponse
import javax.inject.Inject

interface BaseActivity {

    fun showLoading()

    fun hideLoading()

    fun onError(message: String?)

}

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val viewModelComponent by lazy {
        DaggerMainComponent.builder()
            .applicationComponent((getApplication<Application>()).getApplicationComponent())
            .mainModule(MainModule())
            .build()
    }

    val genresResponse: LiveData<GenresResponse>
        get() = _genresResponse

    val isDeleted = MutableLiveData<String>(null)

    private val _genresResponse = MutableLiveData<GenresResponse>(null)

    @Inject
    lateinit var genresPresenter: GenresPresenter

    init {
        viewModelComponent.inject(this)
    }

    fun requestGenres() {
        genresPresenter.getGenres {
            _genresResponse.value = it
        }
    }

    fun eliminar(dato: String) {
        //Hacer algo
        isDeleted.value = dato
    }

}