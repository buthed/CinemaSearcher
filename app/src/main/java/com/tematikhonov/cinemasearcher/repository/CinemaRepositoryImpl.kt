package com.tematikhonov.cinemasearcher.repository

import com.tematikhonov.cinemasearcher.model.CinemaDTO
import retrofit2.Callback

class CinemaRepositoryImpl(private val remoteDataSource: RemoteDataSource): CinemaRepository {

    override fun getCinemaFromServer(id: Int, callback: Callback<CinemaDTO>) {
            remoteDataSource.getCinemaDetails(id, callback)
        }
}