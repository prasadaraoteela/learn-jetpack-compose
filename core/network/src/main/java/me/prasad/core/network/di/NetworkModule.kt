package me.prasad.core.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.prasad.core.network.service.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

  @Singleton
  @Provides
  fun provideRetrofit(): Retrofit = Retrofit.Builder()
    .baseUrl("http://localhost:3000/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

  @Singleton
  @Provides
  fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
}