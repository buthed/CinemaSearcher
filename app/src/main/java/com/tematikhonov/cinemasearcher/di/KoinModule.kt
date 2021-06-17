package com.tematikhonov.cinemasearcher.di

import com.tematikhonov.cinemasearcher.model.repository.Repository
import com.tematikhonov.cinemasearcher.model.repository.RepossitoryImpl
import com.tematikhonov.cinemasearcher.ui.main.MainViewModel

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // single instance of HelloRepository
    single<Repository> { RepossitoryImpl() }

    // MyViewModel ViewModel
    viewModel { MainViewModel(get()) }
}