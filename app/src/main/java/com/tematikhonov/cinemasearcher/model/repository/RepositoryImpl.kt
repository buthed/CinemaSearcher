package com.tematikhonov.cinemasearcher.model.repository

import com.tematikhonov.cinemasearcher.model.entites.Cinema
import com.tematikhonov.cinemasearcher.model.entites.getCinemasList

class RepositoryImpl : Repository {
    override fun getCinemaFromServer() = Cinema()
    override fun getCinemaFromLocalStorage() = getCinemasList()
}