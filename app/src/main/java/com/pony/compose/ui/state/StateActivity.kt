package com.pony.compose.ui.state

import android.content.Intent
import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pony.compose.base.BaseActivity
import com.pony.compose.extension.purple200
import com.pony.compose.extension.white
import com.pony.compose.ui.state.livedata.LiveDataActivity

/**
 *Created by pony on 2022/7/7
 *Description->
 */
class StateActivity : BaseActivity() {

    @Composable
    override fun CreateComposeContent(savedInstanceState: Bundle?) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .scrollable(state = rememberScrollState(), orientation = Orientation.Vertical)
        ) {
            TextButton(
                onClick = {
                    startActivity(Intent(this@StateActivity, LiveDataActivity::class.java))
                }, modifier = Modifier
                    .fillMaxWidth()
                    .background(color = purple200)
                    .height(50.dp)
            ) {
                Text(text = "LiveData", color = white, fontSize = 20.sp, textAlign = TextAlign.Center)
            }
        }
    }
}