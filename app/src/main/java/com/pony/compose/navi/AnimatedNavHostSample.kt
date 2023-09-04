package com.pony.compose.navi

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.pony.compose.base.BaseFragment
import com.pony.compose.common.CommonTextButton
import com.pony.compose.extension.green700
import com.pony.compose.extension.tiktokRed

/**
 *Created by pony on 2022/8/15
 *Description->
 */
class AnimatedNavHostSampleFragment : BaseFragment() {

    @Composable
    override fun CreateContentView() {
        ExperimentalAnimationNav()
    }

    @OptIn(ExperimentalAnimationApi::class)
    @Composable
    private fun ExperimentalAnimationNav() {
        val navController = rememberAnimatedNavController()
        AnimatedNavHost(navController, startDestination = NavScreen.Blue.name) {
            composable(route = NavScreen.Blue.name, popEnterTransition = {
                fadeIn(animationSpec = tween(5000))
            }) {
                BlueComponent(navController)
            }
            composable(route = NavScreen.Red.name, enterTransition = {
                slideIntoContainer(towards = AnimatedContentScope.SlideDirection.Left, tween(700))
            }, exitTransition = {
                slideOutOfContainer(towards = AnimatedContentScope.SlideDirection.Right, tween(700))
            }, popEnterTransition = {
                expandIn(animationSpec = tween(3000))
            }, popExitTransition = {
                slideOutOfContainer(AnimatedContentScope.SlideDirection.Right, tween(700))
            }) {
                RedComponent(navController = navController)
            }
            composable(route = NavScreen.Green.name, enterTransition = {
                slideIntoContainer(towards = AnimatedContentScope.SlideDirection.Up, tween(1000))
            }, exitTransition = {
                slideOutOfContainer(towards = AnimatedContentScope.SlideDirection.Down, tween(500))
            }) {
                GreenComponent(navController = navController)
            }
        }
    }

    enum class NavScreen {
        Blue,
        Red,
        Green
    }

    @OptIn(ExperimentalAnimationApi::class)
    @Composable
    fun AnimatedVisibilityScope.BlueComponent(navController: NavHostController) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Blue)
        ) {
            Box(
                modifier = Modifier
                    .weight(1.0f)
                    .fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                Text(text = NavScreen.Blue.name, color = Color.White, fontSize = 24.sp)
            }
            CommonTextButton(onClick = {
                navController.navigate(route = NavScreen.Red.name)
            }, text = "Nav Red Screen", modifier = Modifier.animateEnterExit(enter = fadeIn(tween(1000 * 10)) + expandIn(tween(1000 * 10))))
        }
    }

    @OptIn(ExperimentalAnimationApi::class)
    @Composable
    fun AnimatedVisibilityScope.RedComponent(navController: NavHostController) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Red)
        ) {
            Box(
                modifier = Modifier
                    .weight(1.0f)
                    .fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                Text(text = NavScreen.Red.name, color = Color.White, fontSize = 24.sp)
            }
            TextButton(
                onClick = {
                    navController.navigate(route = NavScreen.Green.name)
                }, modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .animateEnterExit(enter = fadeIn(tween(10000)) + expandIn(tween(10000)))
                    .background(color = tiktokRed)
            ) {
                Text(
                    text = "Nav Green Screen",
                    color = Color.White,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }

    @OptIn(ExperimentalAnimationApi::class)
    @Composable
    fun AnimatedVisibilityScope.GreenComponent(navController: NavHostController) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Green)
        ) {
            Box(
                modifier = Modifier
                    .weight(1.0f)
                    .fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                Text(text = NavScreen.Green.name, color = Color.White, fontSize = 24.sp)
            }
            TextButton(
                onClick = {
                    navController.popBackStack(route = NavScreen.Blue.name, inclusive = false)
                }, modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .animateEnterExit(enter = fadeIn(tween(10000)) + expandIn(tween(10000)))
                    .background(color = green700)
            ) {
                Text(text = "popBackStack", color = Color.White, fontSize = 20.sp, textAlign = TextAlign.Center, modifier = Modifier.fillMaxSize())
            }
        }
    }
}