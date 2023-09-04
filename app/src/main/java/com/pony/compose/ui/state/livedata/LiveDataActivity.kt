package com.pony.compose.ui.state.livedata

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.pony.compose.R
import com.pony.compose.base.BaseActivity
import com.pony.compose.core.Person
import com.pony.compose.core.getSuperheroList
import com.pony.compose.extension.black333
import kotlinx.coroutines.delay

/**
 *Created by pony on 2022/7/7
 *Description->
 */
class LiveDataActivity : BaseActivity() {

    @Composable
    override fun CreateComposeContent(savedInstanceState: Bundle?) {
        val viewModel = ViewModelProvider(this)[SuperheroesViewModel::class.java]
        LaunchInCompositionComponent(viewModel = viewModel)
    }
}

@Composable
private fun LiveDataComponent(viewModel: SuperheroesViewModel) {
    val personList by viewModel.superheroes.observeAsState(initial = emptyList())
    if (personList.isEmpty()) {
        LoadingComponent()
    } else {
        LiveDataContentListComponent(personList)
    }
}

@Composable
private fun LoadingComponent() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator(modifier = Modifier.size(60.dp))
    }
}

@Composable
private fun LiveDataContentListComponent(personList: List<Person>) {
    val personaData = personList.toMutableList()
    personaData.addAll(personList)
    LazyColumn(content = {
        items(personaData) { person: Person ->
            Card(
                modifier = Modifier
                    .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
                    .fillMaxWidth(),
                border = BorderStroke(1.dp, color = Color.Gray),
                elevation = 12.dp,
                shape = RoundedCornerShape(20.dp)
            ) {
                Row(
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .fillMaxWidth()
                        .padding(vertical = 16.dp), verticalAlignment = Alignment.CenterVertically
                ) {
                    val context = LocalContext.current
                    Image(
                        painter = rememberAsyncImagePainter(
                            model = ImageRequest.Builder(context)
                                .data(person.profilePictureUrl)
                                .placeholder(ColorDrawable(ContextCompat.getColor(context, R.color.purple_500)))
                                .error(ColorDrawable(ContextCompat.getColor(context, R.color.teal_700)))
                                .crossfade(true)
                                .build()
                        ),
                        contentDescription = "avatar",
                        modifier = Modifier
                            .size(100.dp)
                            .clip(shape = RoundedCornerShape(10.dp))
                    )
                    Column(
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .weight(1.0f)
                    ) {
                        Text(text = person.name, fontWeight = FontWeight.W900, color = black333, fontSize = 24.sp)
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(10.dp)
                        )
                        Text(text = "Age:${person.age}", color = Color.DarkGray, fontSize = 20.sp)
                    }
                }
            }
        }
    }, modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(top = 10.dp))
}

@Composable
private fun LaunchInCompositionComponent(viewModel: SuperheroesViewModel) {
    val personList = remember {
        mutableStateListOf<Person>()
    }
    LaunchedEffect(Unit) {
        val list = viewModel.loadSuperheroes()
        personList.addAll(list)
    }
    if (personList.isEmpty()) {
        LoadingComponent()
    } else {
        LiveDataContentListComponent(personList = personList)
    }
}

class SuperheroesViewModel : ViewModel() {

    val superheroes: LiveData<List<Person>> = liveData {
        emit(loadSuperheroes())
    }

    suspend fun loadSuperheroes(): List<Person> {
        delay(3000)
        return getSuperheroList()
    }
}