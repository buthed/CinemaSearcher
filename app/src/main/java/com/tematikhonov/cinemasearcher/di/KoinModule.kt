package com.tematikhonov.cinemasearcher.di

import com.tematikhonov.cinemasearcher.framework.ui.details_fragment.DetailsViewModel
import com.tematikhonov.cinemasearcher.model.repository.Repository
import com.tematikhonov.cinemasearcher.model.repository.RepositoryImpl
import com.tematikhonov.cinemasearcher.framework.ui.main_fragment.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<Repository> { RepositoryImpl() }

    //View models
    viewModel { MainViewModel(get()) }
    viewModel { DetailsViewModel(get()) }
}