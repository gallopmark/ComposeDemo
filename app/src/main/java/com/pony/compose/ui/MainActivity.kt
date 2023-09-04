package com.pony.compose.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.pony.compose.R
import com.pony.compose.base.BaseActivity
import com.pony.compose.common.CommonHorizontalSpacer
import com.pony.compose.common.CommonTextButton
import com.pony.compose.common.NO_NAVIGATION_ICON
import com.pony.compose.ui.dialog.ComposeDialogActivity
import com.pony.compose.ui.drawer.DrawerAppActivity
import com.pony.compose.ui.flow.FlowLayoutActivity
import com.pony.compose.ui.fragment.ComposeAnimationFragment
import com.pony.compose.ui.frame.FrameActivity
import com.pony.compose.ui.lazy.LazyCommonFragment
import com.pony.compose.ui.pager.ComposePagerMain
import com.pony.compose.ui.permission.SystemPermissionFragment
import com.pony.compose.ui.popup.PopupFragment
import com.pony.compose.ui.state.StateActivity
import com.pony.compose.ui.tab.TabLayoutActivity
import com.pony.compose.ui.text.CustomTextActivity
import com.pony.compose.ui.web.WebViewActivity

@ExperimentalPagerApi
@ExperimentalMaterialApi
class MainActivity : BaseActivity() {

    @Composable
    override fun CreateComposeContent(savedInstanceState: Bundle?) {
        MainContent()
    }

    override fun requireNavigationIcon(): Int = NO_NAVIGATION_ICON

    override fun requireTitle(): String = "Compose Demo"

    override fun createAppbarActions(): @Composable RowScope.() -> Unit {

        return {
            val context = LocalContext.current
            Image(painter = painterResource(id = R.drawable.ic_menu), contentDescription = "菜单", modifier = Modifier.clickable {
                Toast.makeText(context, "点击了菜单", Toast.LENGTH_SHORT).show()
            })
        }
    }
}

@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
fun MainContent() {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CommonTextButton(onClick = { context.startActivity(Intent(context, ComposeDialogActivity::class.java)) }, text = "Compose Dialog")
        CommonHorizontalSpacer()
        CommonTextButton(onClick = { context.startActivity(Intent(context, BackdropScaffoldActivity::class.java)) }, text = "BackdropScaffold")
        CommonHorizontalSpacer()
        CommonTextButton(onClick = { context.startActivity(Intent(context, ComposePagerMain::class.java)) }, text = "ViewPager")
        CommonHorizontalSpacer()
        CommonTextButton(onClick = { context.startActivity(Intent(context, CustomTextActivity::class.java)) }, text = "CustomText")
        CommonHorizontalSpacer()
        CommonTextButton(onClick = { context.startActivity(Intent(context, WebViewActivity::class.java)) }, text = "WebView")
        CommonHorizontalSpacer()
        CommonTextButton(onClick = { context.startActivity(Intent(context, FlowLayoutActivity::class.java)) }, text = "FlowLayout")
        CommonHorizontalSpacer()
        CommonTextButton(onClick = { context.startActivity(Intent(context, DrawerAppActivity::class.java)) }, text = "Drawer")
        CommonHorizontalSpacer()
        CommonTextButton(
            onClick = { FrameActivity.navigate(context, LazyCommonFragment::class.java.canonicalName, "LazyCommon") },
            text = "LazyComponent"
        )
        CommonHorizontalSpacer()
        CommonTextButton(onClick = { context.startActivity(Intent(context, StateActivity::class.java)) }, text = "State")
        CommonHorizontalSpacer()
        CommonTextButton(onClick = { context.startActivity(Intent(context, TabLayoutActivity::class.java)) }, text = "TabLayout")
        CommonHorizontalSpacer()
        CommonTextButton(
            onClick = { FrameActivity.navigate(context, ComposeAnimationFragment::class.java.canonicalName, title = "RotateAnimation") },
            text = "Compose Animation"
        )
        CommonHorizontalSpacer()
        CommonTextButton(onClick = { FrameActivity.navigate(context, PopupFragment::class.java.canonicalName, "Popup") }, text = "Popup")
        CommonHorizontalSpacer()
        CommonTextButton(
            onClick = { FrameActivity.navigate(context, SystemPermissionFragment::class.java.canonicalName, "Permission") },
            text = "Permission"
        )
        CommonHorizontalSpacer()
        CommonTextButton(
            onClick = { FrameActivity.navigate(context, SampleMainFragment::class.java.canonicalName, "SampleMain") },
            text = "SampleMain"
        )
    }
}