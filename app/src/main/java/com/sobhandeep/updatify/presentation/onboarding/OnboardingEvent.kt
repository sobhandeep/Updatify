package com.sobhandeep.updatify.presentation.onboarding

sealed class OnboardingEvent {

    data object SaveAppEntry: OnboardingEvent()
}