package com.pony.compose.ui.popup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.TextButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.boundsInParent
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.pony.compose.R
import com.pony.compose.base.BaseFragment
import com.pony.compose.common.CommonTextButton
import com.pony.compose.extension.toPx
import com.pony.compose.extension.white
import timber.log.Timber

/**
 *Created by pony on 2022/7/20
 *Description->
 */
class PopupFragment : BaseFragment() {

    @Composable
    override fun CreateContentView() {
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            TopPopupComponent()
            LeftPopupComponent()
        }
        Timber.tag("popup").e("CreateContentView...")
    }

    @Composable
    private fun TopPopupComponent() {
        val showUpPopup = remember {
            mutableStateOf(false)
        }
        if (showUpPopup.value) {
            ShowTopPopupComponent(showPop = showUpPopup)
            Timber.tag("popup").e("showUpPopup...")
        }
        CommonTextButton(onClick = {
            showUpPopup.value = true
        }, text = "UpPopup", modifier = Modifier.padding(top = 50.dp))
    }

    @Composable
    private fun ShowTopPopupComponent(showPop: MutableState<Boolean>) {
        Popup(alignment = Alignment.TopCenter, onDismissRequest = {
            showPop.value = false
        }, properties = PopupProperties(focusable = true)) {
            Box(
                modifier = Modifier
                    .height(50.dp)
                    .background(color = Color(0x30EE1D52))
            ) {
                Text(text = "I'm up popup!", modifier = Modifier.fillMaxSize(), textAlign = TextAlign.Center, color = white)
            }
        }
    }

    @Composable
    private fun LeftPopupComponent() {
        val showLeftPopup = remember {
            mutableStateOf(false)
        }
        val offset = remember {
            mutableStateOf(Offset(0f, 0f))
        }
        if (showLeftPopup.value) {
            ShowLeftPopupComponent(showPop = showLeftPopup, offset.value)
        }
        TextButton(
            onClick = {
                showLeftPopup.value = true
            }, modifier = Modifier
                .padding(top = 10.dp)
                .onGloballyPositioned {
                    offset.value = it.boundsInParent().topLeft
                }
                .height(50.dp)
                .background(color = colorResource(id = R.color.purple_700))
        ) {
            Text(text = "LeftPopup", color = Color.White, fontSize = 20.sp, textAlign = TextAlign.Center)
        }
    }

    @Composable
    private fun ShowLeftPopupComponent(showPop: MutableState<Boolean>, offset: Offset) {
        Popup(alignment = Alignment.TopStart, onDismissRequest = {
            showPop.value = false
        }, offset = IntOffset((offset.x - 100.dp.toPx).toInt(), offset.y.toInt()), properties = PopupProperties(focusable = true)) {
            Box(
                modifier = Modifier
                    .size(width = 100.dp, height = 50.dp)
                    .background(color = Color(0x30EE1D52)),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "I'm left popup!", textAlign = TextAlign.Center, color = white)
            }
        }
    }
}