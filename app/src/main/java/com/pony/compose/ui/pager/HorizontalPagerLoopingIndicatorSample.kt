package com.pony.compose.ui.pager

import android.os.Bundle
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.pony.compose.base.BaseActivity

/**
 *Created by pony on 2022/6/23
 *Description->
 */
class HorizontalPagerLoopingIndicatorSample :BaseActivity(){

    @Composable
    override fun CreateComposeContent(savedInstanceState: Bundle?) {
        HorizontalPagerLoopingComponent()
    }
}

@Composable
private fun HorizontalPagerLoopingComponent(){
    Column(modifier = Modifier.fillMaxSize()) {
        
    }
}