package com.tematikhonov.cinemasearcher.view.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.tematikhonov.cinemasearcher.R
import com.tematikhonov.cinemasearcher.model.Cinema
import kotlinx.android.synthetic.main.history_fragment.view.*
import kotlinx.android.synthetic.main.history_fragment_recycler_item.view.*

class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.RecyclerItemViewHolder>() {

    private var data: List<Cinema> = arrayListOf()
    fun setData(data: List<Cinema>) {
        this.data = data
        notifyDataSetChanged()
    }

    private lateinit var listener: OnClickAdapterItem;

    public fun setListener(listener:OnClickAdapterItem){
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
        return RecyclerItemViewHolder(
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.history_fragment_recycler_item, parent, false) as View
        )
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class RecyclerItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(data: Cinema) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                itemView.textViewHistory.text =
                        String.format("%s %d %s", data.title)
                itemView.setOnClickListener {
                    Toast.makeText(
                            itemView.context,
                            "on click: ${data.title}",
                            Toast.LENGTH_SHORT
                    )
                            .show()
                }
            }
        }
    }
}