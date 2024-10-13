package me.prasad.jetpack.compose.data.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.ui.graphics.vector.ImageVector

data class Answer(
  val title: String,
  val selected: Boolean = false,
  val icon: ImageVector = Icons.Default.Face
)