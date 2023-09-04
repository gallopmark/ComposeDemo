package com.pony.compose.ui.flow

import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowColumn
import com.google.accompanist.flowlayout.FlowCrossAxisAlignment
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.pony.compose.base.BaseActivity
import com.pony.compose.extension.green700
import com.pony.compose.extension.white

/**
 *Created by pony on 2022/7/5
 *Description->
 */
class FlowLayoutActivity : BaseActivity() {

    @Composable
    override fun CreateComposeContent(savedInstanceState: Bundle?) {
        Column(modifier = Modifier.fillMaxSize()) {
            FlowRow(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .fillMaxWidth()
                    .weight(1f),
                mainAxisAlignment = FlowMainAxisAlignment.Start,
                mainAxisSpacing = 10.dp,
                crossAxisSpacing = 10.dp
            ) {
                for (i in 0 until 10) {
                    val text = if (i % 2 == 0) "今晚老虎不在家" else "今晚打老虎，老虎，老虎！"
                    Text(
                        text = text, modifier = Modifier
                            .background(color = green700)
                            .padding(10.dp), color = white
                    )
                }
            }
            FlowColumn(
                mainAxisAlignment = FlowMainAxisAlignment.Center,
                crossAxisAlignment = FlowCrossAxisAlignment.Start,
                crossAxisSpacing = 10.dp
            ) {
                for (i in 0 until 10) {
                    val text = if (i % 2 == 0) "今晚老虎不在家" else "今晚打老虎，老虎，老虎！今晚打老虎今晚打老虎今晚打老虎"
                    Text(
                        text = text,
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .background(color = green700)
                            .padding(10.dp),
                        color = white,
                    )
                }
            }
        }
    }
}