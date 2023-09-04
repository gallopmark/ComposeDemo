package com.pony.compose.ui.lazy.grid

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pony.compose.base.BaseFragment
import com.pony.compose.extension.blue200
import com.pony.compose.extension.blue500

/**
 *Created by pony on 2022/7/7
 *Description->
 */
class LazyHorizontalGridFragment : BaseFragment() {

    @Composable
    override fun CreateContentView() {
        LazyHorizontalGridComponent()
    }
}

@Composable
private fun LazyHorizontalGridComponent() {
    LazyHorizontalGrid(rows = GridCells.Fixed(2), contentPadding = PaddingValues(start = 8.dp, end = 8.dp), content = {
        items(100) { index ->
            Box(
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp, bottom = 16.dp)
                    .width(100.dp)
                    .fillMaxHeight()
                    .background(if (index % 2 == 0) blue500 else blue200),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "${index + 1}")
            }
        }
    })
}