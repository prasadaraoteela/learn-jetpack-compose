package me.prasad.movies.ui.component

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.forEachGesture
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.*
import androidx.compose.ui.input.pointer.util.VelocityTracker
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.launch
import me.prasad.movies.R
import kotlin.math.roundToInt
import kotlin.math.abs

@Composable
fun FlingableVerticalImage(
    modifier: Modifier = Modifier,
    size: Dp = 96.dp,
    maxTravel: Dp = 240.dp, // how far user can drag in either direction
    onSwipedUp: (() -> Unit)? = null,
    onSwipedDown: (() -> Unit)? = null
) {
    val density = LocalDensity.current
    val maxTravelPx = with(density) { maxTravel.toPx() }
    val offsetY = remember { Animatable(0f) } // px
    val decay = rememberSplineBasedDecay<Float>()
    val scope = rememberCoroutineScope()

    var profile by remember { mutableIntStateOf(R.drawable.fc4_self_massage) }

    Box(modifier = modifier.size(size)) {
        Image(
            painter = painterResource(id = profile),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .offset { IntOffset(0, offsetY.value.roundToInt()) }
                .pointerInput(Unit) {
                    forEachGesture {
                        val velocityTracker = VelocityTracker()
                        awaitPointerEventScope {
                            // wait for initial down
                            val down = awaitFirstDown(requireUnconsumed = false)
                            val pointerId = down.id

                            var pointerEvent: PointerEvent
                            do {
                                pointerEvent = awaitPointerEvent()
                                val change = pointerEvent.changes.firstOrNull { it.id == pointerId }
                                    ?: continue
                                val delta = change.positionChange()
                                if (delta != Offset.Zero) {
                                    // immediate snap for responsive dragging
                                    val new = (offsetY.value + delta.y).coerceIn(
                                        -maxTravelPx,
                                        maxTravelPx
                                    )
                                    scope.launch { offsetY.snapTo(new) }
                                    velocityTracker.addPosition(
                                        change.uptimeMillis,
                                        change.position
                                    )
                                    change.consumePositionChange()
                                }
                            } while (!pointerEvent.changes.all { it.changedToUpIgnoreConsumed() || it.changedToDownIgnoreConsumed() })

                            // compute velocity in px/sec (y axis)
                            val velocityY = velocityTracker.calculateVelocity().y

                            // animate decay (fling) with initial velocity
                            scope.launch {
                                try {
                                    offsetY.animateDecay(velocityY, decay)
                                } finally {
                                    // decide threshold for confirming swipe action
                                    val swipeThreshold = maxTravelPx * 0.45f
                                    if (offsetY.value <= -swipeThreshold) {
                                        // swiped up
                                        onSwipedUp?.invoke()
                                        profile = R.drawable.fc4_self_massage
                                        offsetY.snapTo(0f)
                                    } else if (offsetY.value >= swipeThreshold) {
                                        // swiped down
                                        onSwipedDown?.invoke()
                                        profile = R.drawable.fc2_nature_meditations
                                        offsetY.snapTo(0f)
                                    } else {
                                        // restore to center
                                        offsetY.animateTo(
                                            0f,
                                            animationSpec = tween(durationMillis = 220)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
        )
    }
}


@Preview
@Composable
fun DemoFlingable() {
    FlingableVerticalImage(
        onSwipedUp = {
            println("========== Swipe up =========")
        },
        onSwipedDown = {
            println("========== Swipe down =========")
        }
    )
}
