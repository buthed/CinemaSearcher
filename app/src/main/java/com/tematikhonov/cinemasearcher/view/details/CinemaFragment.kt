package com.tematikhonov.cinemasearcher.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import com.tematikhonov.cinemasearcher.R
import com.tematikhonov.cinemasearcher.databinding.CinemaFragmentBinding
import com.tematikhonov.cinemasearcher.model.Cinema
import com.tematikhonov.cinemasearcher.model.CinemaDTO
import com.tematikhonov.cinemasearcher.view.details.CinemaLoader
import com.tematikhonov.cinemasearcher.view.details.CinemaLoaderListener
import com.tematikhonov.cinemasearcher.viewmodel.AppState
import com.tematikhonov.cinemasearcher.viewmodel.AppStateMain
import com.tematikhonov.cinemasearcher.viewmodel.CinemaViewModel
import com.tematikhonov.cinemasearcher.viewmodel.MainViewModel
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
            cardBudget.text = cinemaDTO.budget.toString() + "$"
            cardRevenue.text = cinemaDTO.revenue.toString() + "$"
            val urlPoster: String = cinemaDTO.poster_path.toString()
            val urlBackdrop: String = cinemaDTO.backdrop_path.toString()
            Picasso.get().load(urlPoster).into(cardPoster)
            Picasso.get().load(urlBackdrop).into(cardBackdrop)
        }
    }

    override fun onFailed(throwable: Throwable) {
        Toast.makeText(context, throwable.localizedMessage, Toast.LENGTH_LONG).show()
    }

    lateinit var cinemaLocal: Cinema

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        arguments?.getParcelable<Cinema>(BUNDLE_EXTRA)?.apply {
            cinemaLocal = this
            CinemaLoader(this@CinemaFragment, this.movie_id).loadCinema()
        }
    }





    }
//        viewModel = ViewModelProvider(this).get(CinemaViewModel::class.java)
//        val cinema = arguments?.getParcelable<Cinema>(BUNDLE_EXTRA)
//        cinema?.let {
//            with(binding) {
//                cardTitle.text = it.title.toString()
//                cardYear.text = it.release_date.toString()
//                cardRank.text = it.vote_average.toString()
//                cardOverview.text = it.overview.toString()
//                cardBudget.text = it.budget.toString() + "$"
//                cardRevenue.text = it.revenue.toString() + "$"
//                val urlPoster: String = it.poster_path.toString()
//                val urlBackdrop: String = it.backdrop_path.toString()
//                Picasso.get().load(urlPoster).into(cardPoster)
//                Picasso.get().load(urlBackdrop).into(cardBackdrop)
//                viewModel.liveDataObserverCinema.observe(viewLifecycleOwner, { appState ->
//                    when (appState) {
//                        is AppState.Error -> {
//                            //...
//                            loadingLayoutForCard.visibility = View.GONE
//                        }
//                        AppState.Loading -> binding.loadingLayoutForCard.visibility = View.VISIBLE
//                        is AppState.Success -> {
//                            loadingLayoutForCard.visibility = View.GONE
//                            cardTitle.text = it.title
//                            cardYear.text = it.release_date
//                            cardRank.text = it.vote_average
//                            cardOverview.text = it.overview
//                            cardBudget.text = it.budget.toString() + "$"
//                            cardRevenue.text = it.revenue.toString() + "$"
//                            val urlPoster: String = it.poster_path.toString()
//                            val urlBackdrop: String = it.backdrop_path.toString()
//                            Picasso.get().load(urlPoster).into(cardPoster)
//                            Picasso.get().load(urlBackdrop).into(cardBackdrop)
//                        }
//                    }
//                })
//                viewModel.getCinemaFromServer(it.movie_id)
//            }
//        }
//    }

//    private fun renderData(appState: AppState) {
//        when(appState){
//            is AppState.Error -> TODO() //show errors
//            is AppState.Success -> {
//                loadingLayout.visibility = View.GONE
//                adapterNowPlaying = MainFragmentNowPlayingAdapter(object : OnItemViewClickListener {
//                    override fun onItemViewClick(cinema: Cinema) {
//                        Log.d("TEST1", cinema.movie_id.toString())
//                        val manager = activity?.supportFragmentManager
//                        manager?.let { manager ->
//                            val bundle = Bundle().apply {
//                                putParcelable(CinemaFragment.BUNDLE_EXTRA, cinema)
//                            }
//                            manager.beginTransaction()
//                                    .add(R.id.container, CinemaFragment.newInstance(bundle))
//                                    .addToBackStack("")
//                                    .commitAllowingStateLoss()
//                        }
//                    }
//                }
//                ).apply {
//                    setCinemaNowPlaying(appState.dataCinema)
//                }
//    }


//    private fun displayCinema(cinemaDTO: CinemaDTO) = with(binding) {
//        arguments?.getParcelable<Cinema>(BUNDLE_EXTRA)?.let {
//            binding.cardTitle.text = it.title.toString()
//            binding.cardYear.text = it.release_date.toString()
//            binding.cardRank.text = it.vote_average.toString()
//            binding.cardOverview.text = it.overview.toString()
//            binding.cardBudget.text = "${it.budget.toString()} $"
//            binding.cardRevenue.text = "${it.revenue.toString()} $"
//            val urlPoster: String = it.poster_path.toString()
//            val urlBackdrop: String = it.backdrop_path.toString()
//            Picasso.get().load(urlPoster).into(binding.cardPoster)
//            Picasso.get().load(urlBackdrop).into(binding.cardBackdrop);
//        }

//    private fun setData(appState: AppState.Success) {
//        cardTitle.text = appState.dataCinema.title
//        cardYear.text = appState.dataCinema.release_date
//        cardRank.text = appState.dataCinema.vote_average
//        cardOverview.text = appState.dataCinema.overview
//        cardBudget.text = "${appState.dataCinema.budget.toString()} $"
//        cardRevenue.text = "${appState.dataCinema.budget.toString()} $"
//        val urlPoster: String = appState.dataCinema.poster_path.toString()
//        val urlBackdrop: String = appState.dataCinema.backdrop_path.toString()
//        Picasso.get().load(urlPoster).into(cardPoster)
//        Picasso.get().load(urlBackdrop).into(cardBackdrop)
//    }