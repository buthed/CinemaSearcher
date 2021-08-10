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
import com.tematikhonov.cinemasearcher.model.Cinema
import com.tematikhonov.cinemasearcher.model.CinemaDTO
import com.tematikhonov.cinemasearcher.model.NowPlayingDTO
import com.tematikhonov.cinemasearcher.model.UpcomingDTO
import com.tematikhonov.cinemasearcher.viewmodel.AppStateMain
import com.tematikhonov.cinemasearcher.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment(), NowPlayingLoaderListener{
    lateinit var viewModel: MainViewModel

    var _binding: MainFragmentBinding? = null
    private val binding: MainFragmentBinding
        get() :MainFragmentBinding {
            return _binding!!
        }

    private var adapterNowPlaying: MainFragmentNowPlayingAdapter? = null
    private var adapterUpcoming: MainFragmentUpcomingAdapter? = null

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
        viewModel. getLiveDataMain().observe(viewLifecycleOwner, Observer { renderData(it) })
        //viewModel.getCinemaListNowPlaying()
        //viewModel.getCinemaListUpcoming()
        adapterNowPlaying = MainFragmentNowPlayingAdapter(object : OnItemViewClickListener {
            override fun onItemViewClick(cinema: Cinema) {
                Log.d("TEST1", cinema.movie_id.toString())
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
        })

        adapterNowPlaying = MainFragmentNowPlayingAdapter(object : OnItemViewClickListener {
            override fun onItemViewClick(cinema: Cinema) {
                Log.d("TEST1", cinema.movie_id.toString())
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
        })

        MovieLoader(this).loadNowPlayingList()
        MovieLoader(this).loadUpcoming()
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
                adapterNowPlaying = MainFragmentNowPlayingAdapter(object : OnItemViewClickListener {
                    override fun onItemViewClick(cinema: Cinema) {
                        Log.d("TEST1", cinema.movie_id.toString())
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
                    //setCinemaNowPlaying(appState.dataCinemaNowPlaying)
                    // setCinemaNowPlaying(appState.(NowPlayingLoader(this,).loadNowPlayingList())) TODO: Нужно вывести список с Лоадера
                }
                adapterUpcoming = MainFragmentUpcomingAdapter(object : OnItemViewClickListener {
                    override fun onItemViewClick(cinema: Cinema) {
                        Log.d("TEST1", cinema.movie_id.toString())
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
                    setCinemaUpcoming(appState.dataCinemaUpcoming)
                }
                recyclerViewNowPlaying.adapter = adapterNowPlaying
                recyclerViewUpcoming.adapter = adapterUpcoming
            }
            is AppStateMain.Loading -> {
                loadingLayout.visibility = View.VISIBLE
            }
            is AppStateMain.Error -> {
                loadingLayout.visibility = View.GONE
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
        Log.d("mylogs","получили NowPlayingDTO")
        //adapterUpcoming.setCinemaUpcoming(upcomingDTO.results)
    }

    override fun onFailed(throwable: Throwable) {
        Log.d("mylogs","получили throwable $throwable")
        Toast.makeText(context, throwable.localizedMessage, Toast.LENGTH_LONG).show()
    }
}