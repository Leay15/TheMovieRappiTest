package com.rappi.themovietestrappi.topRated.view.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rappi.themovietestrappi.R
import com.rappi.themovietestrappi.core.getApplicationComponent
import com.rappi.themovietestrappi.net.model.response.TopRatedResponse
import com.rappi.themovietestrappi.topRated.component.DaggerTopRatedComponent
import com.rappi.themovietestrappi.topRated.module.TopRatedModule
import com.rappi.themovietestrappi.topRated.presentation.presenter.TopRatedPresenter
import com.rappi.themovietestrappi.topRated.viewModel.TopRatedViewModel
import javax.inject.Inject

class TopRatedFragment : Fragment(), TopRatedViewModel {

    @Inject
    lateinit var topRatedPresenter: TopRatedPresenter

    private val TopRatedComponent by lazy {
        DaggerTopRatedComponent.builder()
            .applicationComponent(context!!.getApplicationComponent())
            .topRatedModule(TopRatedModule())
            .build()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_top_rated, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        TopRatedComponent.inject(this)

    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onGetTopRatedMovies(topRatedResponse: TopRatedResponse) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onError(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
