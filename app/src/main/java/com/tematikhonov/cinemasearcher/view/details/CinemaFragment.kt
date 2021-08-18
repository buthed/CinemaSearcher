package com.tematikhonov.cinemasearcher.view

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.squareup.picasso.Picasso
import com.tematikhonov.cinemasearcher.databinding.CinemaFragmentBinding
import com.tematikhonov.cinemasearcher.model.CinemaDTO
import com.tematikhonov.cinemasearcher.view.details.*


const val THREADS_FRAGMENT_BROADCAST_EXTRA = "FRAGMENT BROADCAST"
const val DETAILS_ID_EXTRA = "ID"
const val DETAILS_TITLE_EXTRA = "TITLE"
const val DETAILS_RELEASE_DATE_EXTRA = "RELEASE_DATE"
const val DETAILS_VOTE_AVERAGE_EXTRA = "VOTE AVERAGE"
const val DETAILS_BUDGET_EXTRA = "BUDGET"
const val DETAILS_REVENUE_EXTRA = "REVENUE"
const val DETAILS_POSTER_EXTRA = "POSTER"
const val DETAILS_BACKDROP_EXTRA = "BACKDROP"
const val DETAILS_OVERVIEW_EXTRA = "OVERVIEW"




class CinemaFragment : Fragment() {

    companion object {
        const val BUNDLE_EXTRA = "cinema"
        fun newInstance(bundle: Bundle): CinemaFragment {
            val fragment = CinemaFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    //lateinit var viewModel: CinemaViewModel
    var _binding:CinemaFragmentBinding? = null
    private val binding:CinemaFragmentBinding
        get() :CinemaFragmentBinding{
            return _binding!!
        }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context?.let{ LocalBroadcastManager.getInstance(it).registerReceiver(loadResultsReceiver, IntentFilter(DETAILS_INTENT_FILTER))}
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


    private val loadResultsReceiver: BroadcastReceiver = object :BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {

            intent?.let{
                when(it.getStringExtra(DETAILS_LOAD_RESULT_EXTRA)) {
                    DETAILS_RESPONSE_SUCCESS_EXTRA->
                        renderData(CinemaDTO(
                                it.getIntExtra(DETAILS_ID_EXTRA, 1),
                                it.getStringExtra(DETAILS_TITLE_EXTRA)!!,
                                it.getStringExtra(DETAILS_RELEASE_DATE_EXTRA)!!,
                                it.getStringExtra(DETAILS_VOTE_AVERAGE_EXTRA)!!,
                                it.getIntExtra(DETAILS_BUDGET_EXTRA, 1),
                                it.getIntExtra(DETAILS_REVENUE_EXTRA, 1),
                                it.getStringExtra(DETAILS_POSTER_EXTRA)!!,
                                it.getStringExtra(DETAILS_BACKDROP_EXTRA)!!,
                                it.getStringExtra(DETAILS_OVERVIEW_EXTRA)!!))
                        else -> null
                }
            }
        }
    }

    fun renderData(cinemaDTO: CinemaDTO){
        binding.mainView.visibility = View.VISIBLE
        binding.loadingLayoutForCard.visibility = View.GONE

        cinemaBundle?.let{ cinemaBundle:CinemaDTO->
            binding.cardTitle.text = cinemaDTO.title.toString()
            binding.cardYear.text = cinemaDTO.release_date.toString()
            binding.cardRank.text = cinemaDTO.vote_average.toString()
            binding.cardOverview.text = cinemaDTO.overview.toString()
            binding.cardBudget.text = "${cinemaBundle.budget.toString()} $"
            binding.cardRevenue.text =  "${cinemaBundle.revenue.toString()} $"
            val urlPoster: String = cinemaBundle.poster_path.toString()
            val urlBackdrop: String = cinemaBundle.backdrop_path.toString()
            Picasso.get().load(urlPoster).into(binding.cardPoster)
            Picasso.get().load(urlBackdrop).into(binding.cardBackdrop);
        }
    }

    var cinemaBundle: CinemaDTO? = null

    private fun getCinema(){
        binding.mainView.visibility = View.GONE
        binding.loadingLayoutForCard.visibility = View.VISIBLE
        cinemaBundle?.let{
            context?.startService(Intent(context, CinemaService::class.java)).apply {  }
        }
    }

    lateinit var cinemaLocal: CinemaDTO

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getParcelable<CinemaDTO>(BUNDLE_EXTRA)?.apply {
            cinemaBundle = this
            getCinema()
        }
    }
}