package com.tematikhonov.cinemasearcher.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import com.tematikhonov.cinemasearcher.databinding.CinemaFragmentBinding
import com.tematikhonov.cinemasearcher.model.Cinema
import com.tematikhonov.cinemasearcher.model.CinemaDTO
import com.tematikhonov.cinemasearcher.view.details.CinemaLoader
import com.tematikhonov.cinemasearcher.view.details.CinemaLoaderListener
import com.tematikhonov.cinemasearcher.viewmodel.CinemaViewModel
import kotlinx.android.synthetic.main.main_fragment.*

class CinemaFragment : Fragment(), CinemaLoaderListener {

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
    ): View {
        _binding = CinemaFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onLoaded(cinemaDTO: CinemaDTO) {
        with(binding) {
            cardTitle.text = cinemaDTO.title
            cardYear.text = cinemaDTO.release_date
            cardRank.text = cinemaDTO.vote_average
            cardOverview.text = cinemaDTO.overview
            cardBudget.text = "${cinemaDTO.budget.toString()} $"
            cardRevenue.text =  "${cinemaDTO.revenue.toString()} $"
            val urlPoster: String = "${"https://www.themoviedb.org/t/p/w600_and_h900_bestv2/"}" + cinemaDTO.poster_path
            val urlBackdrop: String = "${"https://www.themoviedb.org/t/p/original/"}" +  cinemaDTO.backdrop_path
            Picasso.get().load(urlPoster).into(cardPoster)
            Picasso.get().load(urlBackdrop).into(cardBackdrop)
        }
    }

    override fun onFailed(throwable: Throwable) {
        Toast.makeText(context, throwable.localizedMessage, Toast.LENGTH_LONG).show()
    }

    lateinit var cinemaLocal: CinemaDTO

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        arguments?.getParcelable<CinemaDTO>(BUNDLE_EXTRA)?.apply {
            cinemaLocal = this
            CinemaLoader(this@CinemaFragment, this.movie_id).loadCinema()
        }
    }
}