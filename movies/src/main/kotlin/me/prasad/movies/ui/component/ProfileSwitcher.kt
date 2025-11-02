package me.prasad.movies.ui.component

// Imports you will need
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.distinctUntilChanged
import me.prasad.movies.R

// Simple data model
data class Profile(
    val id: String,
    val name: String,
    val imageUrl: String,
    val resource: Int = R.drawable.fc2_nature_meditations
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProfileSwitcher(
    profiles: List<Profile>,
    modifier: Modifier = Modifier,
    initialIndex: Int = 0,
    visibleItemSize: Int = 80,            // dp size for the visible avatar
    peek: Int = 20,                       // dp peek of neighbor avatars
    onProfileChanged: (Profile) -> Unit = {}
) {
    if (profiles.isEmpty()) return

    val pageCount = profiles.size
    val pagerState = rememberPagerState(initialPage = initialIndex, pageCount = { pageCount })
    val scope = rememberCoroutineScope()

    // notify initial
    LaunchedEffect(Unit) {
        onProfileChanged(profiles[pagerState.currentPage])
    }

    // observe changes to currentPage and call callback
    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }
            .distinctUntilChanged()
            .collect { page ->
                onProfileChanged(profiles[page])
            }
    }

    val itemDp = visibleItemSize.dp
    val horizontalPadding = ((peek).dp)

    Box(
        modifier = modifier
            .height(itemDp)
            .padding(horizontal = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        VerticalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = horizontalPadding),
            pageSpacing = 12.dp,
            modifier = Modifier
                .fillMaxWidth()
                .height(itemDp)
        ) { page ->
            val profile = profiles[page]

            // scale selected page a bit larger for emphasis
            val isSelected = page == pagerState.currentPage
            val scale by animateFloatAsState(targetValue = if (isSelected) 1.05f else 0.9f)

            Surface(
                tonalElevation = if (isSelected) 6.dp else 0.dp,
                shape = CircleShape,
                modifier = Modifier
                    .size(itemDp)
                    .scale(scale)
                    .clip(CircleShape),
                color = MaterialTheme.colorScheme.surface
            ) {
                // Use Coil AsyncImage for network images. For local drawables, use painterResource.
                Image(
                    painter = painterResource(profile.resource),
                    contentDescription = profile.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                )
            }
        }
    }

    // Optional: programmatic change example (how to fling programmatically)
    // scope.launch { pagerState.animateScrollToPage(targetPage) }
}

@Preview
@Composable
fun TopBarWithProfileSwitcher() {
    val profiles = listOf(
        Profile(
            "1",
            "Profile 1",
            "https://example.com/avatar1.jpg",
            R.drawable.fc2_nature_meditations
        ),
        Profile(
            "2",
            "Profile 2",
            "https://example.com/avatar2.jpg",
            resource = R.drawable.fc4_self_massage
        ),
    )

    var active by remember { mutableStateOf(profiles.first()) }

    Column {
        // put this wherever you want in your UI
        ProfileSwitcher(
            profiles = profiles,
            initialIndex = 0,
            onProfileChanged = { profile ->

                active = profile
                // update app state, load profile-specific data, etc.
            }
        )

        // example: show currently selected name
        Text(text = "Active: ${active.name}", modifier = Modifier.padding(16.dp))
    }
}

