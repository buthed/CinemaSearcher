package com.tematikhonov.cinemasearcher.framework.ui.details_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import com.tematikhonov.cinemasearcher.databinding.CinemaFragmentBinding
import com.tematikhonov.cinemasearcher.model.entites.Cinema

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
            Picasso.get().load(urlBackdrop).into(cardBackdrop);
            }
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