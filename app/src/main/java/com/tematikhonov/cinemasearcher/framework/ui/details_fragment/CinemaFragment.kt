package com.tematikhonov.cinemasearcher.framework.ui.details_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import com.tematikhonov.cinemasearcher.databinding.CinemaFragmentBinding
import com.tematikhonov.cinemasearcher.framework.ui.main_fragment.MainViewModel
import com.tematikhonov.cinemasearcher.model.AppState
import com.tematikhonov.cinemasearcher.model.entites.Cinema
import com.tematikhonov.cinemasearcher.model.rest_entities.CinemaDTO
import kotlinx.android.synthetic.main.main_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CinemaFragment : Fragment() {
    private lateinit var binding: CinemaFragmentBinding
    private val viewModel: DetailsViewModel by viewModel()
    private lateinit var cinemaBundle: Cinema

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = CinemaFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val cinema = arguments?.getParcelable<Cinema>(BUNDLE_EXTRA)
        cinema?.let {
            with(binding) {
                cardTitle.text = it.title.toString()
                cardYear.text = it.release_date.toString()
                cardRank.text = it.vote_average.toString()
                cardOverview.text = it.overview.toString()
                cardBudget.text = it.budget.toString() + "$"
                cardRevenue.text = it.revenue.toString() + "$"
                val urlPoster: String = it.poster_path.toString()
                val urlBackdrop: String = it.backdrop_path.toString()
                Picasso.get().load(urlPoster).into(cardPoster)
                Picasso.get().load(urlBackdrop).into(cardBackdrop)

                viewModel.liveDataToObserve.observe(viewLifecycleOwner, { appState ->
                    when (appState) {
                        is AppState.Error -> {
                            //...
                            loadingLayoutForCard.visibility = View.GONE
                        }
                        AppState.Loading -> binding.loadingLayoutForCard.visibility = View.VISIBLE
                        is AppState.Success -> {
                            loadingLayoutForCard.visibility = View.GONE
                            cardTitle.text = appState.cinemaData[0].title
                            cardYear.text = appState.cinemaData[0].release_date
                            cardRank.text = appState.cinemaData[0].vote_average
                            cardOverview.text = appState.cinemaData[0].overview
                            cardBudget.text = appState.cinemaData[0].budget.toString() + "$"
                            cardRevenue.text = appState.cinemaData[0].revenue.toString() + "$"
                            val urlPoster: String = appState.cinemaData[0].poster_path.toString()
                            val urlBackdrop: String = appState.cinemaData[0].backdrop_path.toString()
                            Picasso.get().load(urlPoster).into(cardPoster)
                            Picasso.get().load(urlBackdrop).into(cardBackdrop)
                        }
                    }
                })
                viewModel.loadData(it.movie_id)
            }
        }
    }

//    private fun displayCinema(cinemaDTO: CinemaDTO) = with(binding) {
//        mainView.visibility = View.VISIBLE
//        cardTitle.text = cinemaDTO.fact.title
//        cardYear.text = cinemaDTO.fact.release_date
//        cardRank.text = cinemaDTO.fact.vote_average
//        cardBudget.text = cinemaDTO.fact.budget.toString()
//        cardRevenue.text = cinemaDTO.fact.revenue.toString()
//        val urlPoster: String = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/" + cinemaDTO.fact.poster_path
//        val urlBackdrop: String = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/" + cinemaDTO.fact.backdrop_path
//        Picasso.get().load(urlPoster).into(cardPoster)
//        Picasso.get().load(urlBackdrop).into(cardBackdrop)
//        cardOverview.text = cinemaDTO.fact.overview
//    }

    companion object {
        private const val api_key = "1cdc07942d44ead0f1079d449b6760a3"
        const val BUNDLE_EXTRA = "cinema"

        fun newInstance(bundle: Bundle): CinemaFragment {
            val fragment = CinemaFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}