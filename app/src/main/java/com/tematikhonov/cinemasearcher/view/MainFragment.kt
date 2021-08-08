package com.tematikhonov.cinemasearcher.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.tematikhonov.cinemasearcher.R
import com.tematikhonov.cinemasearcher.databinding.MainFragmentBinding
import com.tematikhonov.cinemasearcher.model.Cinema
import com.tematikhonov.cinemasearcher.viewmodel.AppStateMain
import com.tematikhonov.cinemasearcher.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {
    lateinit var viewModel: MainViewModel

    var _binding: MainFragmentBinding? = null
    private val binding: MainFragmentBinding
        get() :MainFragmentBinding {
            return _binding!!
        }

    private var adapter: MainFragmentAdapter? = null


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getLiveDataMain().observe(viewLifecycleOwner, Observer { renderData(it) })
        viewModel.getCinemaListNowPlaying()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun renderData(appState: AppStateMain) {
        when(appState){
            is AppStateMain.Error -> TODO() //show errors
            is AppStateMain.Success -> {
                loadingLayout.visibility = View.GONE
                adapter = MainFragmentAdapter(object : OnItemViewClickListener {
                    override fun onItemViewClick(cinema: Cinema) {
                        val manager = activity?.supportFragmentManager
                        manager?.let { manager ->
                            val bundle = Bundle().apply {
                                putParcelable(CinemaFragment.BUNDLE_EXTRA, cinema)
                            }
                            manager.beginTransaction()
                                    .add(R.id.container, CinemaFragment.newInstance(bundle))
                                    .addToBackStack("")
                                    .commitAllowingStateLoss()
                        }
                    }
                }
                ).apply {
                    setCinema(appState.dataCinemaNowPlaying)
                }
                recyclerViewNowPlaying.adapter = adapter
            }
            is AppStateMain.Loading -> {
                loadingLayout.visibility = View.VISIBLE
            }
            is AppStateMain.Error -> {
                loadingLayout.visibility = View.GONE
            }
        }
    }
//
//    private fun setData(appState: AppState.Success) {
//        cardTitle.text = appState.dataCinema.title
//        cardYear.text = appState.dataCinema.release_date
//        cardRank.text = appState.dataCinema.vote_average
//        cardOverview.text = appState.dataCinema.overview
//        cardBudget.text = appState.dataCinema.budget.toString() + "$"
//        cardRevenue.text = appState.dataCinema.revenue.toString() + "$"
//        val urlPoster: String = appState.dataCinema.poster_path.toString()
//        val urlBackdrop: String = appState.dataCinema.backdrop_path.toString()
//        Picasso.get().load(urlPoster).into(cardPoster)
//        Picasso.get().load(urlBackdrop).into(cardBackdrop)
//    }

    companion object {
        fun newInstance() = MainFragment()
    }
}