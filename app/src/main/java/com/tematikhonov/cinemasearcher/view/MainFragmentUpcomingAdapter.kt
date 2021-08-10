package com.tematikhonov.cinemasearcher.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tematikhonov.cinemasearcher.databinding.MainFragmentRecyclerItemBinding
import com.tematikhonov.cinemasearcher.model.Cinema
import com.tematikhonov.cinemasearcher.model.NowPlayingDTO
import com.tematikhonov.cinemasearcher.view.details.CinemaLoader

class MainFragmentUpcomingAdapter(private var itemClickListener:
                          OnItemViewClickListener?) :
        RecyclerView.Adapter<MainFragmentUpcomingAdapter.UpcomingHolder>() {

    private var upcomingData: List<Cinema> = listOf()
    private lateinit var binding: MainFragmentRecyclerItemBinding

    fun setCinemaUpcoming(data: List<Cinema>) {
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
        fun bind(cinema: Cinema) = with(binding) {
            itemTitle.text = cinema.title
            itemReleaseDate.text = cinema.release_date
            itemRank.text = cinema.vote_average
            val urlPoster: String = cinema.poster_path.toString()
            Picasso.get().load(urlPoster).into(itemBanner)
            root.setOnClickListener { itemClickListener?.onItemViewClick(cinema) }
        }
    }
}