package com.tematikhonov.cinemasearcher.view

import com.tematikhonov.cinemasearcher.model.Cinema
import com.tematikhonov.cinemasearcher.model.CinemaDTO

interface OnItemViewClickListener {
    fun onItemViewClick(cinema: CinemaDTO)
}
