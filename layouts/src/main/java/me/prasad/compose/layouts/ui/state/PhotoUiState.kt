package me.prasad.compose.layouts.ui.state

import me.prasad.compose.layouts.business.Photo

data class PhotoUiState(
  val photos: List<Photo> = emptyList(),
  val loading: Boolean = false,
  val error: String? = null
)
