package com.sobhandeep.updatify.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sobhandeep.updatify.domain.instances.app_entry.AppEntryInstances
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val appEntryInstances: AppEntryInstances
): ViewModel() {

    fun onEvent(event: OnboardingEvent){
        when(event){
            is OnboardingEvent.SaveAppEntry -> {
                saveUserEntry()
            }
        }
    }

    private fun saveUserEntry(){
        viewModelScope.launch {
            appEntryInstances.saveAppEntry()
        }
    }
}