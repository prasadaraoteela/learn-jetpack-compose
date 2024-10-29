package me.prasad.compose.layouts.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import me.prasad.compose.layouts.data.repository.VegetableRepository
import me.prasad.compose.layouts.ui.state.VegetableUiState
import javax.inject.Inject

@HiltViewModel
class VegetableViewModel @Inject constructor(
  private val vegetableRepository: VegetableRepository
) : ViewModel() {

  private var _uiState = MutableStateFlow(VegetableUiState())
  val uiState: StateFlow<VegetableUiState> = _uiState.asStateFlow()

  init {
    viewModelScope.launch {
      _uiState.value = VegetableUiState(
        vegetables = vegetableRepository.getVegetables()
      )
    }
  }
}