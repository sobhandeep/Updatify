package com.sobhandeep.updatify.main

 import android.os.Bundle
 import androidx.activity.ComponentActivity
 import androidx.activity.compose.setContent
 import androidx.activity.enableEdgeToEdge
 import androidx.activity.viewModels
 import androidx.compose.foundation.isSystemInDarkTheme
 import androidx.compose.foundation.layout.Box
 import androidx.compose.foundation.layout.fillMaxSize
 import androidx.compose.runtime.SideEffect
 import androidx.compose.ui.Modifier
 import androidx.compose.ui.graphics.Color
 import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
 import com.google.accompanist.systemuicontroller.rememberSystemUiController
 import com.sobhandeep.updatify.presentation.navgraph.NavGraph
 import com.sobhandeep.updatify.ui.theme.UpdatifyTheme
 import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        actionBar?.hide()

        installSplashScreen().apply {
            setKeepOnScreenCondition{
                viewModel.splashState.value
            }
        }

        setContent {
            UpdatifyTheme(
                dynamicColor = false
            ) {

                val isSystemDarkMode = isSystemInDarkTheme()
                val systemController = rememberSystemUiController()

                SideEffect {
                    systemController.setSystemBarsColor(
                        color = Color.Transparent,
                        darkIcons = !isSystemDarkMode
                    )
                }

                Box(modifier = Modifier.fillMaxSize()) {
                    val startDestination = viewModel.startDestination.value
                    NavGraph(
                        startDestination = startDestination
                    )
                }
            }
        }
    }
}