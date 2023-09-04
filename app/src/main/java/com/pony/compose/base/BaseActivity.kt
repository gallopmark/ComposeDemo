package com.pony.compose.base

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import com.pony.compose.R
import com.pony.compose.common.AppToolbar
import com.pony.compose.theme.AppCommonTheme

/**
 *Created by pony on 2022/6/15
 *Description->
 */
abstract class BaseActivity : AppCompatActivity() {

    private val toolbarState = mutableStateOf(true)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppCommonTheme(content = {
                Column(modifier = Modifier.fillMaxSize()) {
                    CreateToolbar()
                    CreateComposeContent(savedInstanceState)
                }
            })
        }
    }

    @Composable
    open fun CreateToolbar() {
        toolbarState.value = requireToolbar()
        if (toolbarState.value) {
            AppToolbar(title = requireTitle(), navigationIcon = requireNavigationIcon(), actions = createAppbarActions(), backAction = {
                onBackPressedAction()
            })
        }
    }

    /**
     * 设置
     */
    open fun setToolbarState(isShow: Boolean) {
        toolbarState.value = isShow
    }

    open fun requireToolbar() = true

    open fun requireTitle(): String = this.javaClass.simpleName ?: ""

    @DrawableRes
    open fun requireNavigationIcon() = R.drawable.ic_arrow_back

    open fun createAppbarActions(): @Composable RowScope.() -> Unit = {

    }

    open fun onBackPressedAction() {
        super.onBackPressed()
    }

    @Composable
    abstract fun CreateComposeContent(savedInstanceState: Bundle?)
}