package com.sobhandeep.updatify.presentation.navgraph

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.sobhandeep.updatify.presentation.bottom_navigation.BottomNavigator
import com.sobhandeep.updatify.presentation.onboarding.OnboardingScreen
import com.sobhandeep.updatify.presentation.onboarding.OnboardingViewModel
import kotlinx.coroutines.delay

sealed class BackPress {
    data object Idle : BackPress()
    data object InitialTouch : BackPress()
}

@Composable
fun NavGraph(
    startDestination: String
){

    val navController = rememberNavController()

    var showToast by remember { mutableStateOf(false) }

    var backPressState by remember { mutableStateOf<BackPress>(BackPress.Idle) }
    val context = LocalContext.current

    if(showToast){
        Toast.makeText(context, "Press again to exit", Toast.LENGTH_SHORT).show()
        showToast= false
    }


    LaunchedEffect(key1 = backPressState) {
        if (backPressState == BackPress.InitialTouch) {
            delay(2000)
            backPressState = BackPress.Idle
        }
    }

    BackHandler(backPressState == BackPress.Idle) {
        backPressState = BackPress.InitialTouch
        showToast = true
    }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnBoardingScreen.route
        ){

            composable(
                route = Route.OnBoardingScreen.route
            ){
                val viewModel: OnboardingViewModel = hiltViewModel()
                OnboardingScreen(
                    event = viewModel::onEvent
                )
            }
        }

        navigation(
            route = Route.UpdatifyNavigation.route,
            startDestination = Route.UpdatifyNavigatorScreen.route
        ){

            composable(
                route = Route.UpdatifyNavigatorScreen.route
            ) {
                BottomNavigator()
            }
        }
    }
}