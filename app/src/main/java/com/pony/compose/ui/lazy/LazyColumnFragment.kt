package com.pony.compose.ui.lazy

import android.graphics.drawable.ColorDrawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.pony.compose.R
import com.pony.compose.base.BaseFragment
import com.pony.compose.core.getSuperheroList
import com.pony.compose.extension.black333
import com.pony.compose.extension.tiktokRed

/**
 *Created by pony on 2022/7/20
 *Description->
 */
class LazyColumnFragment : BaseFragment() {

    @Composable
    override fun CreateContentView() {
        LazyColumnComponent()
    }

    @Composable
    private fun LazyColumnComponent() {
        val data = getSuperheroList()
        LazyColumn(modifier = Modifier.fillMaxSize(), content = {
            items(count = data.size) { index ->
                val context = LocalContext.current
                val item = data[index]
                Column(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp, vertical = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(
                                model = ImageRequest.Builder(context)
                                    .data(item.profilePictureUrl)
                                    .placeholder(ColorDrawable(ContextCompat.getColor(context, R.color.purple_500)))
                                    .error(ColorDrawable(ContextCompat.getColor(context, R.color.teal_700)))
                                    .crossfade(true)
                                    .build()
                            ), contentDescription = "avatar", modifier = Modifier
                                .size(80.dp)
                                .clip(CircleShape)
                        )
                        Column(
                            modifier = Modifier
                                .padding(start = 12.dp)
                                .weight(1.0f)
                        ) {
                            Text(text = item.name, color = black333, fontSize = 20.sp)
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(text = "年龄:${item.age}", color = tiktokRed, fontSize = 16.sp)
                        }
                    }
                    Divider(color = Color(0xfff2f2f2))
                }

            }
        })
    }
}