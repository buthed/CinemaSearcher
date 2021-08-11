package com.tematikhonov.cinemasearcher.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tematikhonov.cinemasearcher.databinding.MainFragmentRecyclerItemBinding
import com.tematikhonov.cinemasearcher.model.CinemaDTO

class MainFragmentUpcomingAdapter(private var itemClickListener:
                          OnItemViewClickListener?) :
        RecyclerView.Adapter<MainFragmentUpcomingAdapter.UpcomingHolder>() {

    private var upcomingData: List<CinemaDTO> = listOf()
    private lateinit var binding: MainFragmentRecyclerItemBinding

    fun setCinemaUpcoming(data: List<CinemaDTO>) {
        upcomingData = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): UpcomingHolder {
        binding = MainFragmentRecyclerItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
        )
        return UpcomingHolder(binding.root)
    }

    override fun onBindViewHolder(holder: UpcomingHolder, position: Int) {
        holder.bind(upcomingData[position])
    }

    override fun getItemCount(): Int {
        return upcomingData.size
    }

    inner class UpcomingHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(cinema: CinemaDTO) = with(binding) {
            itemTitle.text = cinema.title
            itemReleaseDate.text = cinema.release_date
            itemRank.text = cinema.vote_average
            val urlPoster: String = "${"https://www.themoviedb.org/t/p/w600_and_h900_bestv2/"}" + cinema.poster_path
            Picasso.get().load(urlPoster).into(itemBanner)
            root.setOnClickListener { itemClickListener?.onItemViewClick(cinema) }
        }
    }
}