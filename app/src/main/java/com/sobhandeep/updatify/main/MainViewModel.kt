package com.sobhandeep.updatify.main

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sobhandeep.updatify.domain.instances.app_entry.AppEntryInstances
import com.sobhandeep.updatify.presentation.navgraph.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    appEntryInstances: AppEntryInstances
): ViewModel() {

    private val _splashState = mutableStateOf(true)

    val splashState: State<Boolean> = _splashState

    private val _startDestination = mutableStateOf(Route.AppStartNavigation.route)

    val startDestination: State<String> = _startDestination

    init {
        appEntryInstances.readAppEntry().onEach { shouldStartFromHomeScreen ->
            if(shouldStartFromHomeScreen){
                _startDestination.value = Route.UpdatifyNavigation.route
            }else{
                _startDestination.value = Route.AppStartNavigation.route
            }
            delay(200)
            _splashState.value = false
        }.launchIn(viewModelScope)
    }
}