package me.prasad.compose.layouts.ui.state

import me.prasad.compose.layouts.business.Vegetable

data class VegetableUiState(
  val vegetables: List<Vegetable> = emptyList(),
  val isLoading: Boolean = false,
  val errorMessages: List<String> = emptyList()
)
