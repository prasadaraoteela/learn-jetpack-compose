package me.prasad.jetpack.compose.ui.screen

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import java.util.Locale


private val alignYourBodyData = listOf(
  Pair(R.drawable.ab1_inversions, R.string.ab1_inversions),
  Pair(R.drawable.ab2_quick_yoga, R.string.ab2_quick_yoga),
  Pair(R.drawable.ab3_stretching, R.string.ab3_stretching),
  Pair(R.drawable.ab4_tabata, R.string.ab4_tabata),
  Pair(R.drawable.ab5_hiit, R.string.ab5_hiit),
  Pair(R.drawable.ab6_pre_natal_yoga, R.string.ab6_pre_natal_yoga)
)

private val favoriteCollectionData = listOf(
  Pair(R.drawable.fc1_short_mantras, R.string.fc1_short_mantras),
  Pair(R.drawable.fc2_nature_meditations, R.string.fc2_nature_meditations),
  Pair(R.drawable.fc3_stress_and_anxiety, R.string.fc3_stress_and_anxiety),
  Pair(R.drawable.fc4_self_massage, R.string.fc4_self_massage),
  Pair(R.drawable.fc5_overwhelmed, R.string.fc5_overwhelmed),
  Pair(R.drawable.fc6_nightly_wind_down, R.string.fc6_nightly_wind_down)
)

@Composable
fun SearchBar(modifier: Modifier = Modifier) {
//  OutlinedTextField(
  TextField(
    value = "",
    onValueChange = {},
    leadingIcon = {
      Icon(imageVector = Icons.Default.Search, contentDescription = null)
    },
    colors = TextFieldDefaults.colors(
      unfocusedContainerColor = MaterialTheme.colorScheme.surface,
      focusedContainerColor = MaterialTheme.colorScheme.surface
    ),
    placeholder = {
      Text(text = stringResource(R.string.placeholder_search))
    },
    modifier = modifier
      .fillMaxWidth()
      .heightIn(min = 56.dp)
  )
}

@Preview
@Composable
private fun SearchBarPreview() {
  SearchBar()
}

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

@Composable
fun FavoriteCollectionCard(
  modifier: Modifier = Modifier,
  @DrawableRes drawable: Int,
  @StringRes text: Int
) {
  Surface(
    shape = MaterialTheme.shapes.small,
    modifier = modifier
  ) {
    Row(
      modifier = Modifier.width(192.dp),
      verticalAlignment = Alignment.CenterVertically,
    ) {
      Image(
        painterResource(drawable),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
          .size(56.dp)
          .clip(
            RoundedCornerShape(
              topStart = 4.dp,
              bottomStart = 4.dp
            )
          )
      )
      Text(
        text = stringResource(text),
        modifier = Modifier.padding(horizontal = 16.dp),
        style = MaterialTheme.typography.labelMedium
      )
    }
  }
}

@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
private fun FavoriteCollectionCardPreview() {
  FavoriteCollectionCard(
    modifier = Modifier.padding(8.dp),
    drawable = R.drawable.fc2_nature_meditations,
    text = R.string.fc2_nature_meditations
  )
}

@Composable
fun FavoriteCollectionsGrid(modifier: Modifier = Modifier) {
  LazyHorizontalGrid(
    modifier = modifier.height(120.dp),
    rows = GridCells.Fixed(2),
    horizontalArrangement = Arrangement.spacedBy(8.dp),
    verticalArrangement = Arrangement.spacedBy(8.dp),
    contentPadding = PaddingValues(horizontal = 16.dp)
  ) {
    items(favoriteCollectionData) { data ->
      FavoriteCollectionCard(
        drawable = data.first,
        text = data.second
      )
    }
  }
}

@Preview
@Composable
private fun FavoriteCollectionsGridPreview() {
  FavoriteCollectionsGrid()
}

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

@Composable
fun SootheBottomNavigation(modifier: Modifier = Modifier) {
  var selectedIndex by rememberSaveable { mutableIntStateOf(0) }
  NavigationBar(modifier = modifier) {
    NavigationBarItem(
      selected = selectedIndex == 0,
      onClick = { selectedIndex = 0 },
      icon = {
        Icon(imageVector = Icons.Default.Home, contentDescription = null)
      },
      label = {
        Text(text = stringResource(R.string.bottom_navigation_home))
      })
    NavigationBarItem(
      selected = selectedIndex == 1,
      onClick = { selectedIndex = 1 },
      icon = {
        Icon(imageVector = Icons.Default.Person, contentDescription = null)
      },
      label = {
        Text(text = stringResource(R.string.bottom_navigation_profile))
      })
  }
}

@Preview
@Composable
private fun SootheBottomNavigationPreview() {
  SootheBottomNavigation()
}

@Composable
fun MySootheApp() {
  LearnJetpackComposeTheme {
    Scaffold(
      modifier = Modifier.fillMaxSize(),
      bottomBar = { SootheBottomNavigation() },
    ) { innerPadding ->
      HomeScreen(Modifier.padding(innerPadding))
    }
  }
}

@Preview
@Composable
private fun MySootheAppPreview() {
  MySootheApp()
}