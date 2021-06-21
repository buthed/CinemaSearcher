package com.tematikhonov.cinemasearcher.framework.ui.adapters



import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.tematikhonov.cinemasearcher.R
import com.tematikhonov.cinemasearcher.databinding.MainFragmentRecyclerItemBinding
import com.tematikhonov.cinemasearcher.model.entites.Cinema

class MainFragmentAdapter :
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
            root.setOnClickListener {
                Toast.makeText(
                        itemView.context,
                        cinema.title,
                        Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}