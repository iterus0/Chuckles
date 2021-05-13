package xyz.iterus.chuckles.jokes.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import xyz.iterus.chuckles.jokes.data.network.ICNDb
import xyz.iterus.chuckles.jokes.data.network.api.ICNDbApi
import xyz.iterus.chuckles.jokes.data.repo.JokesRepositoryImpl
import xyz.iterus.chuckles.jokes.domain.interactor.JokesInteractor
import xyz.iterus.chuckles.jokes.domain.repo.JokesRepository
import xyz.iterus.chuckles.jokes.ui.JokesAdapter
import xyz.iterus.chuckles.jokes.ui.JokesViewModel

object JokesModule {

    private fun provideJsonConverter(): Converter.Factory {
        return MoshiConverterFactory.create()
    }

    private fun provideRetrofit(jsonConverter: Converter.Factory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ICNDb.BASE_URL)
            .addConverterFactory(jsonConverter)
            .build()
    }

    private fun provideICNDbApi(retrofit: Retrofit): ICNDbApi {
        return retrofit.create(ICNDbApi::class.java)
    }

    private fun provideJokesAdapter(): JokesAdapter = JokesAdapter(emptyList())


    val module = module {
        single { provideJsonConverter() }
        single { provideRetrofit(get()) }
        single { provideICNDbApi(get()) }

        factory<JokesRepository> { JokesRepositoryImpl(get()) }
        factory { JokesInteractor(get()) }

        viewModel { JokesViewModel(get()) }
        factory { provideJokesAdapter() }
    }
}
