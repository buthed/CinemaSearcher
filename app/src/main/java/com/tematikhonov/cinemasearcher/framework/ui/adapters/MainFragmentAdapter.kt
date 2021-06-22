package com.tematikhonov.cinemasearcher.framework.ui.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tematikhonov.cinemasearcher.databinding.MainFragmentRecyclerItemBinding
import com.tematikhonov.cinemasearcher.framework.ui.main_fragment.MainFragment
import com.tematikhonov.cinemasearcher.model.entites.Cinema

class MainFragmentAdapter(private var itemClickListener:
                        MainFragment.OnItemViewClickListener?) :
                        RecyclerView.Adapter<MainFragmentAdapter.MainViewHolder>() {
    private var cinemaData: List<Cinema> = listOf()
    private lateinit var binding: MainFragmentRecyclerItemBinding

    fun setCinema(data: List<Cinema>) {
        cinemaData = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): MainViewHolder {
        binding = MainFragmentRecyclerItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
        )
        return MainViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(cinemaData[position])
    }

    override fun getItemCount(): Int {
        return cinemaData.size
    }

    inner class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {
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