package com.sobhandeep.updatify.presentation.composables

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun DetailsWebView(
    url: String
){
    AndroidView(
        factory = {

            WebView(it).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )

                webChromeClient = WebChromeClient()

                settings.javaScriptEnabled = true

                loadUrl(url)
            }
        },

        update = {
            it.loadUrl(url)
        }
    )
}