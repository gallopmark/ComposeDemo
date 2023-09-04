package com.pony.compose.ui.web


import android.os.Bundle
import androidx.compose.runtime.Composable
import com.pony.compose.base.BaseActivity

/**
 *Created by pony on 2022/6/23
 *Description->Compose WebView
 */
class WebViewActivity : BaseActivity() {

    @Composable
    override fun CreateComposeContent(savedInstanceState: Bundle?) {
        WebViewComponentSample()
    }


    @Composable
    private fun WebViewComponentSample() {

    }
}

