package me.prasad.compose.layouts.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import me.prasad.compose.layouts.data.repository.DefaultVegetableRepository
import me.prasad.compose.layouts.data.repository.VegetableRepository

@InstallIn(ViewModelComponent::class)
@Module
abstract class VegetableModule {

  @Binds
  abstract fun bindVegetableRepository(impl: DefaultVegetableRepository): VegetableRepository
}