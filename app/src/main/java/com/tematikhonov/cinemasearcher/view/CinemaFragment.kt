package com.tematikhonov.cinemasearcher.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import com.tematikhonov.cinemasearcher.databinding.CinemaFragmentBinding
import com.tematikhonov.cinemasearcher.model.Cinema
import com.tematikhonov.cinemasearcher.viewmodel.AppState
import com.tematikhonov.cinemasearcher.viewmodel.CinemaViewModel
import kotlinx.android.synthetic.main.cinema_fragment.*


class CinemaFragment : Fragment() {

    lateinit var viewModel: CinemaViewModel
    var _binding:CinemaFragmentBinding? = null
    private val binding:CinemaFragmentBinding
        get() :CinemaFragmentBinding{
            return _binding!!
        }

    companion object {
        const val BUNDLE_EXTRA = "cinema"

        fun newInstance(bundle: Bundle): CinemaFragment {
            val fragment = CinemaFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = CinemaFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        arguments?.getParcelable<Cinema>(BUNDLE_EXTRA)?.let {
            binding.cardTitle.text = it.title.toString()
            binding.cardYear.text = it.release_date.toString()
            binding.cardRank.text = it.vote_average.toString()
            binding.cardOverview.text = it.overview.toString()
            binding.cardBudget.text = it.budget.toString() + "$"
            binding.cardRevenue.text = it.revenue.toString() + "$"
            val urlPoster: String = it.poster_path.toString()
            val urlBackdrop: String = it.backdrop_path.toString()
            Picasso.get().load(urlPoster).into(binding.cardPoster)
            Picasso.get().load(urlBackdrop).into(binding.cardBackdrop);
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
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