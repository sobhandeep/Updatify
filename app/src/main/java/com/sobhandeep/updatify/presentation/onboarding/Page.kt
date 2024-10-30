package com.sobhandeep.updatify.presentation.onboarding

import androidx.annotation.DrawableRes
import com.sobhandeep.updatify.R

data class Page(

    val title: String,
    val description: String,
    @DrawableRes val image: Int
)

val pages = listOf(
    Page(
        title = "Stay Informed with Customizable News Feed",
        description = "Browse the latest news tailored to your interests. Choose categories and topics you love to stay updated with only the most relevant news.",
        image = R.drawable.onboarding1
    ),
    Page(
        title = "Get Real-Time Alerts",
        description = "Never miss a story! Receive notifications for breaking news and updates as they happen, keeping you in the loop anytime, anywhere.",
        image = R.drawable.onboarding2
    ),
    Page(
        title = "Explore News by Categories",
        description = "Dive into the topics that matter to you. Browse news by categories to easily find stories on politics, sports, technology, and more.",
        image = R.drawable.onboarding1
    )
)