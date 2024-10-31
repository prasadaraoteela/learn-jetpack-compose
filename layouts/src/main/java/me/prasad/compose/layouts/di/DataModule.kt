package me.prasad.compose.layouts.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import me.prasad.compose.layouts.data.repository.DefaultPhotoRepository
import me.prasad.compose.layouts.data.repository.DefaultVegetableRepository
import me.prasad.compose.layouts.data.repository.PhotoRepository
import me.prasad.compose.layouts.data.repository.VegetableRepository

@InstallIn(ViewModelComponent::class)
@Module
abstract class DataModule {

  @Binds
  abstract fun bindVegetableRepository(impl: DefaultVegetableRepository): VegetableRepository

  @Binds
  abstract fun bindPhotoRepository(impl: DefaultPhotoRepository): PhotoRepository
}