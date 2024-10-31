package me.prasad.compose.layouts.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import me.prasad.compose.layouts.data.repository.PhotoRepository
import me.prasad.compose.layouts.ui.state.PhotoUiState
import javax.inject.Inject

@HiltViewModel
class PhotoViewModel @Inject constructor(
  private val photoRepository: PhotoRepository
): ViewModel() {

  private val _uiState = MutableStateFlow(PhotoUiState(loading = true))
  val uiState: StateFlow<PhotoUiState> = _uiState.asStateFlow()

  init {
    viewModelScope.launch {
      _uiState.value = PhotoUiState(
        photos = photoRepository.getPhotos(),
        loading = false
      )
    }
  }
}