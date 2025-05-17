package me.prasad.jetpack.compose.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.prasad.jetpack.compose.R
import me.prasad.jetpack.compose.data.model.Section
import me.prasad.jetpack.compose.data.model.Tile
import me.prasad.jetpack.compose.ui.theme.Typography

@Composable
fun Section(section: Section, modifier: Modifier = Modifier) {

  Column(
    modifier = modifier
        .fillMaxWidth()
        .padding(16.dp)
  ) {

    Text(
      text = section.title,
      style = Typography.titleLarge,
    )

    Column {
      section.tiles.forEach { tile ->
        Tile(tile = tile)
      }
    }

  }

}

@Preview(showBackground = true)
@Composable
private fun SectionPreview() {
  Section(
    section = Section(
      title = "Money Management",
      tiles = listOf(
        Tile(
          title = stringResource(R.string.cash_flow_view_title),
          description = stringResource(R.string.cash_flow_view_description),
          icon = Icons.Rounded.Home
        ),
        Tile(
          title = stringResource(R.string.cash_flow_view_title),
          description = stringResource(R.string.cash_flow_view_description),
          icon = Icons.Rounded.Home
        )
      )
    )
  )
}
