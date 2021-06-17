package com.tematikhonov.cinemasearcher.model.repository

import com.tematikhonov.cinemasearcher.model.entites.Cinema

class RepossitoryImpl : Repository {
    override fun getCinemaFromServer(): Cinema {
        return Cinema()
    }

    override fun getCinemaFromLocalStorage(): Cinema {
        return Cinema()
    }
}