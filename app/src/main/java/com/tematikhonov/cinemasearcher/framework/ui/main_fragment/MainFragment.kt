package com.tematikhonov.cinemasearcher.framework.ui.main_fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.tematikhonov.cinemasearcher.R
import com.tematikhonov.cinemasearcher.databinding.MainFragmentBinding
import com.tematikhonov.cinemasearcher.framework.ui.adapters.MainFragmentAdapter
import com.tematikhonov.cinemasearcher.framework.ui.details_fragment.CinemaFragment
import com.tematikhonov.cinemasearcher.model.AppState
import com.tematikhonov.cinemasearcher.model.entites.Cinema
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainFragment : Fragment() {
    private lateinit var binding: MainFragmentBinding
    private val viewModel: MainViewModel by viewModel()

    private var adapter: MainFragmentAdapter? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerViewNowPlaying.adapter = adapter
        viewModel.getLiveData().observe(viewLifecycleOwner, { renderData(it) })
        viewModel.getCinemaList()

    }

    private fun renderData(appState: AppState) = with(binding){
        when (appState) {
            is AppState.Success -> {
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
                    setCinema(appState.cinemaData)
                }
                recyclerViewNowPlaying.adapter = adapter
            }
            is AppState.Loading -> {
                loadingLayout.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                loadingLayout.visibility = View.GONE
                Snackbar
                        .make(binding.recyclerViewNowPlaying, "Error", Snackbar.LENGTH_INDEFINITE)
                        .setAction("Reload") { viewModel.getCinemaList() }
                        .show()
            }
        }
    }

    interface OnItemViewClickListener {
        fun onItemViewClick(cinema: Cinema)
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}