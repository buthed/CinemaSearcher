package com.tematikhonov.cinemasearcher.view.history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.tematikhonov.cinemasearcher.databinding.HistoryFragmentBinding
import com.tematikhonov.cinemasearcher.viewmodel.AppState
import com.tematikhonov.cinemasearcher.viewmodel.HistoryViewModel

class HistoryFragment : Fragment(), OnClickAdapterItem {


    private var _binding: HistoryFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HistoryViewModel by lazy { ViewModelProvider(this).get(HistoryViewModel::class.java) }
    private val adapter: HistoryAdapter by lazy { HistoryAdapter() }


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = HistoryFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getLiveData().observe(viewLifecycleOwner, Observer { renderData(it) })
        viewModel.getAllHistory()
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                binding.historyRecyclerView.visibility = View.VISIBLE
                //binding.includedLoadingLayout.loadingLayout.visibility = View.GONE
                binding.loadingLayout.visibility = View.GONE
                adapter.setListener(this)
                binding.historyRecyclerView.adapter = adapter
                val vis = binding.historyRecyclerView.visibility
                adapter.setData(appState.dataCinema)
            }
            is AppState.Loading -> {
                binding.historyRecyclerView.visibility = View.GONE
                binding.loadingLayout.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                binding.historyRecyclerView.visibility = View.VISIBLE
                binding.loadingLayout.visibility = View.GONE
                /*binding.historyFragmentRecyclerview.showSnackBar(
                    getString(R.string.error),
                    getString(R.string.reload),
                    {
                        viewModel.getAllHistory()
                    })*/
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() =
                HistoryFragment()
    }

    override fun onItemClick(name:String, posiotion: Int) {
        viewModel.deleteByName(name)
        viewModel.getAllHistory()
    }
}