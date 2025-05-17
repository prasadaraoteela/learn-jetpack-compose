package me.prasad.jetpack.compose.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.prasad.jetpack.compose.R
import me.prasad.jetpack.compose.ui.theme.LearnJetpackComposeTheme

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
