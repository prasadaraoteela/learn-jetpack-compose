package me.prasad.jetpack.compose.ui.screen

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.prasad.jetpack.compose.R
import java.util.Locale

@Composable
fun HomeSection(
  modifier: Modifier = Modifier,
  @StringRes title: Int,
  content: @Composable () -> Unit
) {
  Column(modifier = modifier) {
    Text(
      text = stringResource(title).uppercase(Locale.getDefault()),
      style = MaterialTheme.typography.titleSmall,
      modifier = Modifier
          .paddingFromBaseline(top = 40.dp, bottom = 8.dp)
          .padding(horizontal = 16.dp)
    )
    content()
  }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
private fun HomeSectionPreview() {
  HomeSection(title = R.string.align_your_body) {
    AlignYourBodyList()
  }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
  Column(
    modifier = modifier
      .verticalScroll(rememberScrollState())
  ) {
    SearchBar(Modifier.padding(8.dp))
    HomeSection(title = R.string.align_your_body) {
      AlignYourBodyList()
    }
    HomeSection(title = R.string.favorite_collections) {
      FavoriteCollectionsGrid()
    }
  }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
private fun HomeScreenPreview() {
  HomeScreen()
}
