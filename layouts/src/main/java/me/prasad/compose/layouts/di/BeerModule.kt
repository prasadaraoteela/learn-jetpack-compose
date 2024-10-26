package me.prasad.compose.layouts.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import me.prasad.compose.layouts.data.network.BeerService
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
object BeerModule {

  fun provideBeerRepository(): BeerRepository {

  }

  fun provideBeerService(): BeerService {

  }

  fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
      .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
      .baseUrl("https://api.sampleapis.com/")
      .build()
  }
}