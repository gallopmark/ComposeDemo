package com.pony.compose.ui.text

import android.os.Bundle
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.pony.compose.base.BaseActivity
import com.pony.compose.common.TitleComponent

/**
 *Created by pony on 2022/6/17
 *Description->Compose TextField输入框
 */
class CustomTextFieldActivity : BaseActivity() {

    @Composable
    override fun CreateComposeContent(savedInstanceState: Bundle?) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .scrollable(state = rememberScrollState(), orientation = Orientation.Vertical)
        ) {
            TitleComponent("This is a Simple Text Input field")
        }
    }

    override fun requireTitle(): String  = "Compose Custom TextField"
}


@Composable
private fun SimpleTextInputComponent(){

}
