package me.prasad.jetpack.compose.data.model

import androidx.compose.ui.graphics.vector.ImageVector

data class Section(
  val title: String,
  val tiles: List<Tile>
)

data class Tile(
  val title: String,
  val description: String,
  val icon: ImageVector
)
