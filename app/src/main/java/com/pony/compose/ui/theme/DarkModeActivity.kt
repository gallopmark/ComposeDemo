package com.pony.compose.ui.theme

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.pony.compose.base.BaseActivity
import com.pony.compose.core.LOREM_IPSUM_1
import com.pony.compose.core.LOREM_IPSUM_2
import com.pony.compose.core.LOREM_IPSUM_3
import com.pony.compose.extension.*
import kotlinx.coroutines.launch

/**
 *Created by pony on 2022/8/3
 *Description->
 */
class DarkModeActivity : BaseActivity() {

    override fun requireToolbar(): Boolean = false

    @Composable
    override fun CreateComposeContent(savedInstanceState: Bundle?) {
        val systemInDarkTheme = isSystemInDarkTheme()
        val enableDarkMode = remember {
            mutableStateOf(systemInDarkTheme)
        }
        CustomTheme(enableDarkMode) {
            ThemeDrawerAppComponent(enableDarkMode)
        }
    }
}

@SuppressLint("ConflictingOnColor")
@Composable
private fun CustomTheme(enableDarkMode: MutableState<Boolean>, content: @Composable () -> Unit) {
    val lightColors = lightColorScheme(
        primary = orange,
        primaryContainer = yellow,
        onPrimary = white,
        secondary = teal200,
        secondaryContainer = twitterColor,
        onSecondary = black333,
        background = white,
        onBackground = black333,
        surface = white,
        onSurface = graySurface,
        error = tiktokRed
    )
    val darkColors = darkColorScheme(
        surface = black333, //TopAppBar 默认背景颜色
        onSurface = white,  // TopAppBar title、Navigation、Menu等默认颜色
        onPrimary = white,
        primaryContainer = graySurface,
        onPrimaryContainer = white
    )
    val colors = if (enableDarkMode.value) darkColors else lightColors
    val typography = Typography(
        bodyMedium = TextStyle(
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Normal,
            fontSize = 20.sp,
            textIndent = TextIndent(firstLine = 16.sp),
            textAlign = TextAlign.Justify
        )
    )
    MaterialTheme(colorScheme = colors, content = content, typography = typography)
}

enum class ThemedDrawerAppScreen {
    Screen1,
    Screen2,
    Screen3
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ThemeDrawerAppComponent(enableDarkMode: MutableState<Boolean>) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val currentScreen = remember {
        mutableStateOf(ThemedDrawerAppScreen.Screen1)
    }
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                scope.launch { drawerState.close() }
            },
        drawerContent = {
            ThemeDrawerContentComponent(currentScreen) {
                scope.launch { drawerState.close() }
            }
        },
        drawerState = drawerState,
        gesturesEnabled = drawerState.isOpen,
        content = {
            ThemedDrawerBodyComponent(currentScreen, enableDarkMode) {
                scope.launch { drawerState.open() }
            }
        },
        drawerTonalElevation = DrawerDefaults.ModalDrawerElevation,
        drawerContainerColor = transparent
    )
}

@Composable
private fun ThemeDrawerContentComponent(currentScreen: MutableState<ThemedDrawerAppScreen>, closeDrawer: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .background(color = orange200)
            .clickable { closeDrawer() }
    ) {
        Text(
            text = ThemedDrawerAppScreen.Screen1.name, modifier = Modifier
                .padding(16.dp)
                .clickable {
                    currentScreen.value = ThemedDrawerAppScreen.Screen1
                    closeDrawer()
                }, color = white, fontSize = 20.sp
        )
        Text(
            text = ThemedDrawerAppScreen.Screen2.name, modifier = Modifier
                .padding(16.dp)
                .clickable {
                    currentScreen.value = ThemedDrawerAppScreen.Screen2
                    closeDrawer()
                }, color = white, fontSize = 20.sp
        )
        Text(
            text = ThemedDrawerAppScreen.Screen3.name, modifier = Modifier
                .padding(16.dp)
                .clickable {
                    currentScreen.value = ThemedDrawerAppScreen.Screen3
                    closeDrawer()
                }, color = white, fontSize = 20.sp
        )
    }
}

@Composable
private fun ThemedDrawerBodyComponent(
    currentScreen: MutableState<ThemedDrawerAppScreen>,
    enableDarkMode: MutableState<Boolean>,
    openDrawer: () -> Unit
) {
    //System UI Controller for Jetpack Compose
//    val systemUiController = rememberSystemUiController()
//    SideEffect {
//        // Update all of the system bar colors to be transparent, and use
//        // dark icons if we're in light theme
//        systemUiController.statusBarDarkContentEnabled = enableDarkMode.value //状态栏，深色模式：白底黑字 正常模式：黑底白字
//        val statusBarColor = if (enableDarkMode.value) black333 else transparent  //状态栏颜色
//        systemUiController.setStatusBarColor(color = statusBarColor)
//    }
    val mode = if(enableDarkMode.value) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY
    AppCompatDelegate.setDefaultNightMode(mode)
    val onCheckChanged = { _: Boolean ->
        enableDarkMode.value = !enableDarkMode.value
    }
    when (currentScreen.value) {
        ThemedDrawerAppScreen.Screen1 -> ThemedScreen1Component(
            enableDarkMode.value,
            openDrawer,
            onCheckChanged
        )
        ThemedDrawerAppScreen.Screen2 -> ThemedScreen2Component(
            enableDarkMode.value,
            openDrawer,
            onCheckChanged
        )
        ThemedDrawerAppScreen.Screen3 -> ThemedScreen3Component(
            enableDarkMode.value,
            openDrawer,
            onCheckChanged
        )
    }
}

@Composable
private fun ThemedScreen1Component(enableDarkMode: Boolean, openDrawer: () -> Unit, onCheckChanged: (Boolean) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.surface)
    ) {
        CenterAlignedTopAppBar(title = {
            Text(
                text = ThemedDrawerAppScreen.Screen1.name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }, navigationIcon = {
            IconButton(onClick = { openDrawer() }) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = "OpenDrawer")
            }
        })
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Switch(checked = enableDarkMode, onCheckedChange = onCheckChanged)
            Text(
                text = "Enable Dark Mode",
                modifier = Modifier.padding(start = 20.dp),
                fontSize = 22.sp
            )
        }
        Text(
            text = LOREM_IPSUM_1,
            modifier = Modifier
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            fontSize = 20.sp,
        )
    }
}

@Composable
private fun ThemedScreen2Component(enableDarkMode: Boolean, openDrawer: () -> Unit, onCheckChanged: (Boolean) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.surface)
    ) {
        CenterAlignedTopAppBar(title = {
            Text(
                text = ThemedDrawerAppScreen.Screen2.name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = if (enableDarkMode) white else black333,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }, navigationIcon = {
            IconButton(onClick = { openDrawer() }) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = "OpenDrawer")
            }
        })
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Switch(checked = enableDarkMode, onCheckedChange = onCheckChanged)
            Text(
                text = "Enable Dark Mode",
                modifier = Modifier.padding(start = 20.dp),
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 22.sp)
            )
        }
        Text(
            text = LOREM_IPSUM_2,
            modifier = Modifier
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            fontSize = 20.sp,
            color = if (enableDarkMode) white else black333
        )
    }
}

@Composable
private fun ThemedScreen3Component(enableDarkMode: Boolean, openDrawer: () -> Unit, onCheckChanged: (Boolean) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.surface)
    ) {
        CenterAlignedTopAppBar(title = {
            Text(
                text = ThemedDrawerAppScreen.Screen3.name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = if (enableDarkMode) white else black333,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }, navigationIcon = {
            IconButton(onClick = { openDrawer() }) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = "OpenDrawer")
            }
        })
        Surface(modifier = Modifier.fillMaxWidth(), content = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Switch(checked = enableDarkMode, onCheckedChange = onCheckChanged)
                Text(
                    text = "Enable Dark Mode",
                    modifier = Modifier.padding(start = 20.dp),
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 22.sp)
                )
            }
        })
        Surface(modifier = Modifier.fillMaxSize()) {
            Text(
                text = LOREM_IPSUM_3,
                modifier = Modifier
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                fontSize = 20.sp,
            )
        }
    }
}