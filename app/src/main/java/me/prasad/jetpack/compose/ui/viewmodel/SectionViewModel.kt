package me.prasad.jetpack.compose.ui.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import me.prasad.jetpack.compose.data.repository.SectionRepository
import me.prasad.jetpack.compose.ui.state.SectionUiState
import javax.inject.Inject

@HiltViewModel
class SectionViewModel @Inject constructor(
  repository: SectionRepository
) : ViewModel() {

  private val _uiState = MutableStateFlow(SectionUiState())
  val uiState: StateFlow<SectionUiState> = _uiState.asStateFlow()

  init {
    _uiState.value = SectionUiState(sections = repository.fetchSections())
  }
}
