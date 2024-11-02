package com.sobhandeep.updatify.presentation.bottom_navigation

import android.widget.Toast
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sobhandeep.updatify.R
import com.sobhandeep.updatify.domain.model.Article
import com.sobhandeep.updatify.presentation.bookmark.BookmarkScreen
import com.sobhandeep.updatify.presentation.bookmark.BookmarkViewModel
import com.sobhandeep.updatify.presentation.composables.BottomNavigationBar
import com.sobhandeep.updatify.presentation.details.DetailsEvent
import com.sobhandeep.updatify.presentation.details.DetailsScreenWeb
import com.sobhandeep.updatify.presentation.details.DetailsViewModel
import com.sobhandeep.updatify.presentation.home.HomeScreen
import com.sobhandeep.updatify.presentation.home.HomeViewModel
import com.sobhandeep.updatify.presentation.navgraph.Route
import com.sobhandeep.updatify.presentation.search.SearchScreen
import com.sobhandeep.updatify.presentation.search.SearchViewModel

@Composable
fun BottomNavigator(){

    val bottomNavigationItems = remember {
        listOf(
            BottomNavigationItem(icon = R.drawable.ic_home, text = "Home"),
            BottomNavigationItem(icon = R.drawable.ic_search, text = "Search"),
            BottomNavigationItem(icon = R.drawable.ic_bookmark, text = "Bookmark")
        )
    }

    val navController = rememberNavController()

    val backStackState = navController.currentBackStackEntryAsState().value

    var selectedItem by rememberSaveable {
        mutableIntStateOf(0)
    }

    var lastSelectedTab by remember { mutableStateOf<String?>(null) }

    val bottomBarkVisibility = remember(key1 = backStackState) {
        backStackState?.destination?.route == Route.HomeScreen.route ||
                backStackState?.destination?.route == Route.SearchScreen.route ||
                backStackState?.destination?.route == Route.BookmarkScreen.route
    }

    selectedItem = remember(key1 = backStackState){
        when (backStackState?.destination?.route) {
            Route.HomeScreen.route -> 0
            Route.SearchScreen.route -> 1
            Route.BookmarkScreen.route -> 2
            else -> 0
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {

            if(bottomBarkVisibility){
                BottomNavigationBar(
                    items = bottomNavigationItems,
                    selected = selectedItem,
                    onItemClick = { index ->
                        when (index) {
                            0 -> navigateTab(
                                navController = navController,
                                route = Route.HomeScreen.route
                            )

                            1 -> navigateTab(
                                navController = navController,
                                route = Route.SearchScreen.route
                            )

                            2 -> navigateTab(
                                navController = navController,
                                route = Route.BookmarkScreen.route
                            )
                        }
                    }
                )
            }
        }
    ){

        val bottomPadding = it.calculateBottomPadding()

        NavHost(
            navController = navController,
            startDestination = Route.HomeScreen.route,
            modifier = Modifier.padding(bottom = bottomPadding)
        ){

            composable(
                route = Route.HomeScreen.route,
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        tween(1000)
                    )
                },
            ) {

                val viewModel: HomeViewModel = hiltViewModel()
                lastSelectedTab = Route.HomeScreen.route

                HomeScreen(
                    viewModel = viewModel,
                    navigateToSearch = {
                        navigateTab(
                            navController = navController,
                            route = Route.SearchScreen.route
                        )
                    },
                    navigateToDetails = { article ->
                        navigateToDetails(
                            navController = navController,
                            article = article
                        )
                    },
                )
            }

            composable(
                route = Route.SearchScreen.route,
                enterTransition = {
                    when(lastSelectedTab){
                        Route.HomeScreen.route ->
                            slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Left,
                            tween(1000)
                        )
                    Route.BookmarkScreen.route ->
                        slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Right,
                            tween(1000)
                        )
                    else ->
                        null
                    }
                },
            ) {

                val viewModel: SearchViewModel = hiltViewModel()

                val state = viewModel.state.value

                lastSelectedTab = Route.SearchScreen.route

                SearchScreen(
                    state = state,
                    event = viewModel::onEvent,
                    navigateToDetails = { article ->
                        navigateToDetails(
                            article = article,
                            navController = navController
                        )
                    },
                    navigateToHome = {
                        navigateTab(
                            navController = navController,
                            route = Route.HomeScreen.route
                        )
                    },
                    navigateToBookmark = {
                        navigateTab(
                            navController = navController,
                            route = Route.BookmarkScreen.route
                        )
                    }
                )
            }

            composable(
                route = Route.DetailsScreen.route,
            ) {

                val viewModel: DetailsViewModel = hiltViewModel()

                if(viewModel.sideEffect != null){
                    Toast.makeText(LocalContext.current,viewModel.sideEffect, Toast.LENGTH_SHORT).show()
                    viewModel.onEvent(DetailsEvent.RemoveSideEffect)
                }

                navController.previousBackStackEntry?.savedStateHandle?.get<Article>("article")?.let { article ->
                    DetailsScreenWeb(
                        article = article,
                        event = viewModel::onEvent,
                        navigateUp = {
                            navController.navigateUp()
                        },
                        viewModel = viewModel
                    )
                }
            }

            composable(
                route = Route.BookmarkScreen.route,
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        tween(1000)
                    )
                },
            ) {

                val viewModel: BookmarkViewModel = hiltViewModel()

                val state = viewModel.state.value

                lastSelectedTab = Route.BookmarkScreen.route

                BookmarkScreen(
                    state = state,
                    navigateToDetails = { article ->
                        navigateToDetails(
                            article = article,
                            navController = navController
                        )
                    },
                    navigateToHome = {
                        navigateTab(
                            navController = navController,
                            route = Route.HomeScreen.route
                        )
                    },
                    navigateToSearch = {
                        navigateTab(
                            navController = navController,
                            route = Route.SearchScreen.route
                        )
                    }
                )
            }
        }
    }
}

private fun navigateTab(
    navController: NavController,
    route: String
){
    navController.navigate(route){
        navController.graph.startDestinationRoute?.let { homeScreen ->
            popUpTo(homeScreen){
                saveState = true
            }
            restoreState = true
            launchSingleTop = true
        }
    }
}

private fun navigateToDetails(
    navController: NavController,
    article: Article
){
    navController.currentBackStackEntry?.savedStateHandle?.set("article", article)
    navController.navigate(Route.DetailsScreen.route)
}