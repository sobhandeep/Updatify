package com.sobhandeep.updatify.presentation.composables

import android.annotation.SuppressLint
import android.view.View
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
        factory = { context ->

            WebView(context).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )

                setLayerType(View.LAYER_TYPE_SOFTWARE, null)

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