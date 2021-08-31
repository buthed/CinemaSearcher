package com.tematikhonov.cinemasearcher.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import com.tematikhonov.cinemasearcher.databinding.CinemaFragmentBinding
import com.tematikhonov.cinemasearcher.model.*
import com.tematikhonov.cinemasearcher.viewmodel.AppState
import com.tematikhonov.cinemasearcher.viewmodel.CinemaViewModel
import okhttp3.*
import java.io.IOException

class CinemaFragment : Fragment(){

    companion object {
        const val BUNDLE_EXTRA = "cinema"
        fun newInstance(bundle: Bundle): CinemaFragment {
            val fragment = CinemaFragment()
            fragment.arguments = bundle
            return fragment
        }
    }


    private var _binding:CinemaFragmentBinding? = null
    private val binding:CinemaFragmentBinding
        get() :CinemaFragmentBinding{
            return _binding!!
        }

    private val viewModel: CinemaViewModel by lazy {
        ViewModelProvider(this).get(CinemaViewModel::class.java)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = CinemaFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLiveDataCinema().observe(viewLifecycleOwner, Observer {
            renderData(it)
        })
        arguments?.getParcelable<CinemaDTO>(BUNDLE_EXTRA)?.apply {
            cinemaBundle = this
        }
        cinemaBundle?.let { viewModel.getCinemaFromServer(it.id) }
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Error -> {
                binding.mainView.visibility = View.VISIBLE
                binding.loadingLayoutForCard.visibility = View.GONE
                //TODO вывести ошибку
            }
            AppState.Loading -> {
                binding.mainView.visibility = View.GONE
                binding.loadingLayoutForCard.visibility = View.VISIBLE
            }
            is AppState.Success -> {
                binding.mainView.visibility = View.VISIBLE
                binding.loadingLayoutForCard.visibility = View.GONE
                setData(appState.dataCinema[0]) //FIXME
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setData(cinema:CinemaDTO){
        binding.mainView.visibility = View.VISIBLE
        binding.loadingLayoutForCard.visibility = View.GONE

        cinemaBundle?.let{ cinemaBundle:CinemaDTO->
            binding.cardTitle.text = cinema.title
            binding.cardYear.text = cinema.release_date
            binding.cardRank.text = cinema.vote_average
            binding.cardOverview.text = cinema.overview
            binding.cardBudget.text = "${cinema.budget} $"
            binding.cardRevenue.text = "${cinema.revenue} $"
            Picasso.get().load("$TMDB_POSTER_PATH${cinema.poster_path.toString()}").into(binding.cardPoster)
            Picasso.get().load("$TMDB_BACKDROP_PATH${cinema.backdrop_path.toString()}").into(binding.cardBackdrop)
        }

    }

    var cinemaBundle:CinemaDTO? = null

    private fun getCinema(){
        //binding.mainView.visibility = View.GONE
        binding.loadingLayoutForCard.visibility = View.VISIBLE
        cinemaBundle?.let{
            val client = OkHttpClient()
            val builder: Request.Builder  = Request.Builder()
            builder.header(TMDB_API_KEY_NAME, TMDB_API_KEY_VALUE)
            builder.url("https://api.themoviedb.org/3/movie/${it.id}?api_key=1cdc07942d44ead0f1079d449b6760a3")

            val request: Request = builder.build()
            val call: Call =client.newCall(request)

            Thread {
            // action1
                val response:Response = call.execute()
                val serverResponse:String? = response.body()?.string()
                requireActivity().runOnUiThread(Runnable {
                    setData(Gson().fromJson(serverResponse,CinemaDTO::class.java))
                })
                // action2
             }.start()
        }
    }
}