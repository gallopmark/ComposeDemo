package com.pony.compose.ui.dialog

import android.os.Bundle
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.pony.compose.R
import com.pony.compose.base.BaseActivity

/**
 *Created by pony on 2022/6/7
 *Description->compose dialog
 */
class ComposeDialogActivity : BaseActivity() {

    @Composable
    override fun CreateComposeContent(savedInstanceState: Bundle?) {
        ComposeDialogComponent()
    }

    override fun requireTitle(): String  = "Compose Dialog"
}

@Composable
private fun ComposeDialogComponent() {
    val showAlertDialogFlag = remember {
        mutableStateOf(false)
    }
    if (showAlertDialogFlag.value) {
        AlertDialogComponent(showDialog = showAlertDialogFlag)
    }
    val showCustomDialog = remember {
        mutableStateOf(false)
    }
    if (showCustomDialog.value) {
        DialogCustomComponent(openDialog = showCustomDialog)
    }
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier
            .padding(horizontal = 32.dp)
            .fillMaxWidth()
            .height(50.dp)
            .background(color = colorResource(id = R.color.teal_700))
            .clickable {
                showAlertDialogFlag.value = true
            }) {
            Text(
                text = "AlertDialog", color = colorResource(id = R.color.white)
            )
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
        )
        TextButton(
            onClick = {
                showCustomDialog.value = true
            }, modifier = Modifier
                .padding(horizontal = 32.dp)
                .fillMaxWidth()
                .background(color = colorResource(id = R.color.purple_700), shape = RoundedCornerShape(12.dp))
        ) {
            Text(
                text = "CustomDialog", color = colorResource(id = R.color.white)
            )
        }
    }
}

@Composable
private fun AlertDialogComponent(showDialog: MutableState<Boolean>) {
    val context = LocalContext.current
    if (showDialog.value) {
        AlertDialog(onDismissRequest = {
            // Dismiss the dialog when the user clicks outside the dialog or on the back
            // button. If you want to disable that functionality, simply use an empty
            // onDismissRequest.
            showDialog.value = false
        }, title = {
            Text(text = "AlertDialog Title!", fontSize = 24.sp)
        }, text = {
            Text(
                text = "A dialog is a type of modal window that appears in front of app content to provide critical information,or ask for a decision.",
                fontSize = 16.sp,
                color = Color(0xff666666)
            )
        }, confirmButton = {
            TextButton(onClick = {
                Toast.makeText(context, "OK button clicked!", Toast.LENGTH_SHORT).show()
                showDialog.value = false
            }) {
                Text(text = "确定", color = colorResource(id = R.color.purple_700), fontSize = 20.sp)
            }
        }, dismissButton = {
            TextButton(onClick = {
                Toast.makeText(context, "Cancel button clicked!", Toast.LENGTH_SHORT).show()
                showDialog.value = false
            }) {
                Text(text = "取消", color = colorResource(id = R.color.black_333), fontSize = 20.sp)
            }
        }, shape = RoundedCornerShape(30.dp))
    }
}

@Composable
private fun DialogCustomComponent(openDialog: MutableState<Boolean>) {
    if (openDialog.value) {
        Dialog(onDismissRequest = {
            openDialog.value = false
        }, properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = false)) {
            Column(
                modifier = Modifier
                    .background(color = colorResource(id = R.color.white), shape = RoundedCornerShape(12.dp))
                    .padding(horizontal = 16.dp, vertical = 20.dp), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Use location service?",
                    color = Color(0xff333333),
                    fontSize = 20.sp,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )
                Spacer(
                    modifier = Modifier
                        .height(20.dp)
                        .fillMaxWidth()
                )
                Text(text = "Let us help apps determine location.This means sending anonymous location data to us,even when no apps are running.")
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(30.dp)
                )
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    TextButton(onClick = { openDialog.value = false }) {
                        Text(text = "DISAGREE", color = colorResource(id = R.color.purple_700), fontSize = 18.sp)
                    }
                    Spacer(modifier = Modifier.width(25.dp))
                    TextButton(onClick = { openDialog.value = false }) {
                        Text(text = "AGREE", color = colorResource(id = R.color.purple_700), fontSize = 18.sp)
                    }
                }
            }
        }
    }
}