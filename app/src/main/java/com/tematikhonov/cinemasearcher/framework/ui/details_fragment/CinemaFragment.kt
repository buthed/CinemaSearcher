package com.tematikhonov.cinemasearcher.framework.ui.details_fragment

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.tematikhonov.cinemasearcher.R
import com.tematikhonov.cinemasearcher.databinding.CinemaFragmentBinding
import com.tematikhonov.cinemasearcher.model.entites.Cinema
import kotlinx.android.synthetic.*

class CinemaFragment : Fragment() {
    private lateinit var binding: CinemaFragmentBinding
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
        arguments?.getParcelable<Cinema>(BUNDLE_EXTRA)?.let {
            binding.cardTitle.text = it.title.toString()
            binding.cardYear.text = it.release_date.toString()
            binding.cardRank.text = it.vote_average.toString()
            binding.cardOverview.text = it.overview.toString()
            binding.cardBudget.text = it.budget.toString() + "$"
            binding.cardRevenue.text = it.revenue.toString() + "$"
        }
    }

    companion object {
        const val BUNDLE_EXTRA = "cinema"

        fun newInstance(bundle: Bundle): CinemaFragment {
            val fragment = CinemaFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}