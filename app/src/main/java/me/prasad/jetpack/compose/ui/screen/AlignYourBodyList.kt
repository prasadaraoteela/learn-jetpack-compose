package me.prasad.jetpack.compose.ui.screen

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.prasad.jetpack.compose.R
import me.prasad.jetpack.compose.ui.theme.LearnJetpackComposeTheme

private val alignYourBodyData = listOf(
  Pair(R.drawable.ab1_inversions, R.string.ab1_inversions),
  Pair(R.drawable.ab2_quick_yoga, R.string.ab2_quick_yoga),
  Pair(R.drawable.ab3_stretching, R.string.ab3_stretching),
  Pair(R.drawable.ab4_tabata, R.string.ab4_tabata),
  Pair(R.drawable.ab5_hiit, R.string.ab5_hiit),
  Pair(R.drawable.ab6_pre_natal_yoga, R.string.ab6_pre_natal_yoga)
)

@Composable
fun AlignYourBodyElement(
  modifier: Modifier = Modifier,
  @DrawableRes drawable: Int,
  @StringRes text: Int,
) {
  Column(
    modifier = modifier,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Image(
      painterResource(drawable),
      contentDescription = null,
      contentScale = ContentScale.Crop,
      modifier = Modifier
          .size(88.dp)
          .clip(CircleShape)
    )
    Text(
      text = stringResource(text),
      style = MaterialTheme.typography.labelMedium,
      modifier = Modifier.padding(top = 24.dp, bottom = 8.dp)
    )
  }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun AlignYourBodyElementPreview() {
  LearnJetpackComposeTheme {
    AlignYourBodyElement(
      modifier = Modifier.padding(8.dp),
      drawable = alignYourBodyData[0].first,
      text = alignYourBodyData[0].second
    )
  }
}

@Composable
fun AlignYourBodyList(modifier: Modifier = Modifier) {
  LazyRow(
    modifier = modifier,
    horizontalArrangement = Arrangement.spacedBy(8.dp),
    contentPadding = PaddingValues(horizontal = 16.dp)
  ) {
    items(alignYourBodyData) { data ->
      AlignYourBodyElement(
        drawable = data.first,
        text = data.second
      )
    }
  }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun AlignYourBodyListPreview() {
  AlignYourBodyList()
}
