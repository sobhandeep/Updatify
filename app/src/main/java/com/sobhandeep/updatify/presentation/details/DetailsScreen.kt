package com.sobhandeep.updatify.presentation.details

import android.annotation.SuppressLint
import android.content.Intent
import android.content.Intent.ACTION_SEND
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.sobhandeep.updatify.domain.model.Article
import com.sobhandeep.updatify.presentation.composables.DetailsTopBar

@Composable
fun DetailsScreenWeb(
    article: Article,
    event: (DetailsEvent) -> Unit,
    navigateUp: () -> Unit,
    viewModel: DetailsViewModel
){
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize()
            .statusBarsPadding()
    ) {
        DetailsTopBar(

            onShareClick = {
                Intent(ACTION_SEND).also {
                    it.putExtra(Intent.EXTRA_TEXT, article.url)
                    it.type = "text/plain"
                    if(it.resolveActivity(context.packageManager) != null){
                        context.startActivity(it)
                    }
                }
            },
            onBackClick = navigateUp,
            onBookmarkClick = {
                event(DetailsEvent.UpsertDeleteArticle(article))
            },
            isBookmarked = viewModel.isBookmarked.value || article.isBookmarked
        )

        DetailsWebView(url = article.url)
    }
}

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