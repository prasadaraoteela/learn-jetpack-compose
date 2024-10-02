package me.prasad.jetpack.compose.ui.state

import me.prasad.jetpack.compose.data.model.Section

data class SectionUiState(
    val sections: List<Section> = emptyList(),
    val isLoading: Boolean = false,
    val isError: Boolean = false
)
