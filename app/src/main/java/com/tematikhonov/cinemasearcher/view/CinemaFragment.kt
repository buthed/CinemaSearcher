package com.tematikhonov.cinemasearcher.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import com.tematikhonov.cinemasearcher.databinding.CinemaFragmentBinding
import com.tematikhonov.cinemasearcher.viewmodel.AppState
import com.tematikhonov.cinemasearcher.viewmodel.CinemaViewModel
import kotlinx.android.synthetic.main.cinema_fragment.*


class CinemaFragment : Fragment() {

    lateinit var viewModel: CinemaViewModel
    lateinit var binding:CinemaFragmentBinding

    companion object {
        fun newInstance() = CinemaFragment()
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = CinemaFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CinemaViewModel::class.java)
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer { renderData(it) })
        viewModel.getCinema()
    }

    private fun renderData(appState: AppState) {
        when(appState){
            is AppState.Error -> TODO() //show errors
            is AppState.Success -> {
                loadingLayoutForCard.visibility = View.GONE
                setData(appState)
            }
            AppState.Loading -> {
                binding.loadingLayoutForCard.visibility = View.VISIBLE
            }
        }
    }

    private fun setData(appState: AppState.Success) {
        cardTitle.text = appState.dataCinema.title
        cardYear.text = appState.dataCinema.release_date
        cardRank.text = appState.dataCinema.vote_average
        cardOverview.text = appState.dataCinema.overview
        cardBudget.text = appState.dataCinema.budget.toString() + "$"
        cardRevenue.text = appState.dataCinema.revenue.toString() + "$"
        val urlPoster: String = appState.dataCinema.poster_path.toString()
        val urlBackdrop: String = appState.dataCinema.backdrop_path.toString()
        Picasso.get().load(urlPoster).into(cardPoster)
        Picasso.get().load(urlBackdrop).into(cardBackdrop)
    }
}