package com.tematikhonov.cinemasearcher.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.tematikhonov.cinemasearcher.R
import com.tematikhonov.cinemasearcher.databinding.MainFragmentBinding
import com.tematikhonov.cinemasearcher.model.CinemaDTO
import com.tematikhonov.cinemasearcher.model.NowPlayingDTO
import com.tematikhonov.cinemasearcher.model.UpcomingDTO
import com.tematikhonov.cinemasearcher.viewmodel.AppStateMain
import com.tematikhonov.cinemasearcher.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment(), NowPlayingLoaderListener{

    private val adapterNowPlaying: MainFragmentNowPlayingAdapter =
            MainFragmentNowPlayingAdapter(object : OnItemViewClickListener {

                override fun onItemViewClick(cinema: CinemaDTO) {
                    activity?.supportFragmentManager?.apply {
                        beginTransaction()
                                .add(R.id.container, CinemaFragment.newInstance(Bundle().apply {
                                    putParcelable(CinemaFragment.BUNDLE_EXTRA, cinema)
                                }))
                                .addToBackStack("")
                                .commitAllowingStateLoss()
                    }
                }
    })

    private val adapterUpcoming: MainFragmentUpcomingAdapter =
            MainFragmentUpcomingAdapter(object : OnItemViewClickListener {

        override fun onItemViewClick(cinema: CinemaDTO) {
            activity?.supportFragmentManager?.apply {
                beginTransaction()
                        .add(R.id.container, CinemaFragment.newInstance(Bundle().apply {
                            putParcelable(CinemaFragment.BUNDLE_EXTRA, cinema)
                        }))
                        .addToBackStack("")
                        .commitAllowingStateLoss()
            }
        }
    })

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    var _binding: MainFragmentBinding? = null
    private val binding: MainFragmentBinding
        get() :MainFragmentBinding {
            return _binding!!
        }

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
        //viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel. getLiveDataMain().observe(viewLifecycleOwner, Observer { renderData(it) })
        viewModel.getCinemaListNowPlaying()
        viewModel.getCinemaListUpcoming()
        MovieLoader(this).loadNowPlayingList()
        MovieLoader(this).loadUpcoming()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun renderData(appState: AppStateMain) {
        when (appState) {
            is AppStateMain.Error -> TODO() //show errors
            is AppStateMain.Success -> {
                with(binding) {
                    loadingLayout.visibility = View.GONE
                    recyclerViewNowPlaying.adapter = adapterNowPlaying
                    recyclerViewUpcoming.adapter = adapterUpcoming
                    adapterNowPlaying.setCinemaNowPlaying(appState.dataCinemaNowPlaying)
                    adapterUpcoming.setCinemaUpcoming(appState.dataCinemaUpcoming)
                }
            }
            AppStateMain.Loading -> {
                binding.loadingLayout.visibility = View.VISIBLE
            }
        }
    }

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onLoadedNow(nowPlayingDTO: NowPlayingDTO) {
        Log.d("mylogs","получили NowPlayingDTO")
        loadingLayout.visibility = View.GONE
        recyclerViewNowPlaying.adapter = adapterNowPlaying
        adapterNowPlaying?.setCinemaNowPlaying(nowPlayingDTO.results)
    }

    override fun onLoadedUpcoming(upcomingDTO: UpcomingDTO) {
        Log.d("mylogs","получили UpcomingDTO")
        loadingLayout.visibility = View.GONE
        recyclerViewUpcoming.adapter = adapterUpcoming
        adapterUpcoming?.setCinemaUpcoming(upcomingDTO.results)
    }

    override fun onFailed(throwable: Throwable) {
        Log.d("mylogs","получили throwable $throwable")
        Toast.makeText(context, throwable.localizedMessage, Toast.LENGTH_LONG).show()
    }
}