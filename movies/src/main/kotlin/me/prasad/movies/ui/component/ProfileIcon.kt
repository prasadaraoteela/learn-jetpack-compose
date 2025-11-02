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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import me.prasad.movies.R
import kotlin.math.roundToInt

@Composable
fun ProfileIcon(
    painter: Int,
    modifier: Modifier = Modifier,
    size: Dp = 64.dp,
    // called when fling crosses threshold; velocity is pixels/sec
    onFlingConfirmed: (velocityX: Float) -> Unit = {}
) {
    // horizontal offset in px
    val offsetX = remember { Animatable(0f) }
    val decay = rememberSplineBasedDecay<Float>()
    val scope = rememberCoroutineScope()

    // maximum allowed translation (px)
    val maxOffsetPx = with(receiver = LocalDensity.current) { 200.dp.toPx() }

    Box(modifier = modifier.size(size)) {
        Image(
            painter = painterResource(painter),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                // apply offset
                .offset { IntOffset(offsetX.value.roundToInt(), 0) }
                // handle drag + fling
                .pointerInput(Unit) {
                    forEachGesture {
                        // velocity tracker for fling
                        val velocityTracker = VelocityTracker()
                        awaitPointerEventScope {
                            // wait for down
                            val down = awaitFirstDown(requireUnconsumed = false)
                            val pointerId = down.id

                            // process moves until pointer up/cancel
                            var pointerEvent: PointerEvent
                            do {
                                pointerEvent = awaitPointerEvent()
                                // find the change for our pointer
                                val change = pointerEvent.changes.firstOrNull { it.id == pointerId } ?: continue

                                // only handle movement
                                val delta = change.positionChange()
                                if (delta != Offset.Zero) {
                                    // update offset immediately for snappy UI
                                    val new = (offsetX.value + delta.x).coerceIn(-maxOffsetPx, maxOffsetPx)
                                    scope.launch { offsetX.snapTo(new) }
                                    velocityTracker.addPosition(change.uptimeMillis, change.position)
                                    change.consumePositionChange()
                                }
                            } while (!pointerEvent.changes.all { it.changedToUpIgnoreConsumed() || it.changedToDownIgnoreConsumed() })

                            // compute fling velocity in px/sec
                            val velocity = velocityTracker.calculateVelocity().x

                            // run decay animation (physics fling) and then decide final state
                            scope.launch {
                                try {
                                    offsetX.animateDecay(velocity, decay)
                                } finally {
                                    // threshold to consider it a confirmed fling action
                                    val threshold = maxOffsetPx * 0.5f
                                    if (kotlin.math.abs(offsetX.value) >= threshold) {
                                        // trigger action (e.g., remove, open profile, clear)
                                        onFlingConfirmed(velocity)
                                        // optional: reset immediately or animate out
                                        offsetX.snapTo(0f)
                                    } else {
                                        // restore to center
                                        offsetX.animateTo(0f, animationSpec = tween(durationMillis = 250))
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
fun Demo() {

    var profile by remember { mutableIntStateOf(R.drawable.fc4_self_massage) }

    ProfileIcon(
        painter = profile,
        size = 72.dp,
        onFlingConfirmed = { velocityX ->
            // velocityX > 0 => fling to the right, velocityX < 0 => fling to the left
            if (velocityX > 0f) {
                // example action: open profile
                println("Flinged right with velocity $velocityX")
                profile = R.drawable.fc4_self_massage
            } else {
                // example action: delete or show options
                println("Flinged left with velocity $velocityX")
                R.drawable.fc2_nature_meditations
            }
        }
    )
}
