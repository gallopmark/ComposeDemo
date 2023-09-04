package com.pony.compose.ui.fragment.animation

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pony.compose.base.BaseFragment
import com.pony.compose.extension.blue700
import com.pony.compose.extension.orange700
import com.pony.compose.extension.purple700

/**
 *Created by pony on 2022/7/15
 *Description->
 */
class AnimatedContentFragment : BaseFragment() {


    @Composable
    override fun CreateContentView() {
        Column(Modifier.padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            MinusPlusAnimationContentComponent()
            ContentStateAnimationComponent()
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun MinusPlusAnimationContentComponent() {
    var count by remember { mutableStateOf(0) }
    // The `AnimatedContent` below uses an integer count as its target state. So when the
    // count changes, it will animate out the content associated with the previous count, and
    // animate in the content associated with the target state.
    AnimatedContent(
        targetState = count,
        transitionSpec = {
            // We can define how the new target content comes in and how initial content
            // leaves in the ContentTransform. Here we want to create the impression that the
            // different numbers have a spatial relationship - larger numbers are
            // positioned (vertically) below smaller numbers.
            if (targetState > initialState) {
                // If the incoming number is larger, new number slides up and fades in while
                // the previous (smaller) number slides up to make room and fades out.
                slideInVertically { it }.plus(fadeIn()) with slideOutVertically { -it }.plus(fadeOut())
            } else {
                // If the incoming number is smaller, new number slides down and fades in while
                // the previous number slides down and fades out.
                slideInVertically { -it }.plus(fadeIn()) with slideOutVertically { it }.plus(fadeOut())
                // Disable clipping since the faded slide-out is desired out of bounds, but
                // the size transform is still needed from number getting longer
            }.using(SizeTransform(clip = false))
        }
    ) { targetCount ->
        // This establishes a mapping between the target state and the content in the form of a
        // Composable function. IMPORTANT: The parameter of this content lambda should
        // *always* be used. During the content transform, the old content will be looked up
        // using this lambda with the old state, until it's fully animated out.

        // Since AnimatedContent differentiates the contents using their target states as the
        // key, the same composable function returned by the content lambda like below will be
        // invoked under different keys and therefore treated as different entities.
        Text("$targetCount", fontSize = 20.sp)
    }
    Spacer(Modifier.size(20.dp))
    Row(horizontalArrangement = Arrangement.SpaceAround) {
        Button(onClick = { count-- }) { Text("Minus") }
        Spacer(Modifier.size(60.dp))
        Button(onClick = { count++ }) { Text("Plus ") }
    }
}

private enum class ContentState {
    Foo, Bar, Baz
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun ContentStateAnimationComponent() {
    var contentState by remember {
        mutableStateOf(ContentState.Foo)
    }
    AnimatedContent(targetState = contentState) { state ->
        when (state) {
            ContentState.Foo -> Foo()
            ContentState.Bar -> Bar()
            ContentState.Baz -> Baz()
        }
    }
    Spacer(Modifier.size(20.dp))
    Button(onClick = {
        contentState = when (contentState) {
            ContentState.Foo -> {
                ContentState.Bar
            }
            ContentState.Bar -> {
                ContentState.Baz
            }
            else -> {
                ContentState.Foo
            }
        }
    }) { Text("Change State") }
}

@Composable
private fun Foo() {
    Box(
        modifier = Modifier
            .size(200.dp)
            .background(color = orange700)
    )
}

@Composable
private fun Bar() {
    Box(
        modifier = Modifier
            .size(40.dp)
            .background(color = purple700)
    )
}

@Composable
private fun Baz() {
    Box(
        modifier = Modifier
            .size(80.dp, 20.dp)
            .background(color = blue700)
    )
}