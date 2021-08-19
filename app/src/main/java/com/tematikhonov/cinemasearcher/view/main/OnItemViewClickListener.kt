package com.tematikhonov.cinemasearcher.view.main

import com.tematikhonov.cinemasearcher.model.Cinema
import com.tematikhonov.cinemasearcher.model.CinemaDTO

interface OnItemViewClickListener {
    fun onItemViewClick(cinema: CinemaDTO)
}
