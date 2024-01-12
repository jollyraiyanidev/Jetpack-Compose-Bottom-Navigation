package com.app.navigationsample.uiscreen

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.app.navigationsample.R
import com.app.navigationsample.ui.theme.NavigationSampleTheme
import com.app.navigationsample.ui.theme.fonts
import com.app.navigationsample.uiscreen.model.State
import com.app.navigationsample.uiscreen.model.sliding.SlidingResponse
import com.app.navigationsample.uiscreen.viewmodel.DataViewModel

@Composable
fun RecipeScreen(userModel :DataViewModel= hiltViewModel()) {

    val usersState by userModel.data.observeAsState()

    val activity = LocalContext.current as Activity
    LaunchedEffect(Unit) {
        userModel.fetchData(activity)

    }
    when (usersState) {
        is State.Loading -> {
            // Show loading indicator
            Box(modifier = Modifier) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }

        is State.Success -> {
            val users = (usersState as State.Success<SlidingResponse>).data
            LazyVerticalGrid(
                columns = GridCells.Adaptive(140.dp),
                contentPadding = PaddingValues(
                    start = 12.dp,
                    top = 16.dp,
                    end = 12.dp,
                    bottom = 16.dp
                ),
                modifier = Modifier.padding(bottom = 50.dp),

                content = {
                    items(users) { item ->
                        SliderVertical(
                            drawable = item.image.toString(),
                            text = item.name.toString()
                        )
                    }

                })
        }

        is State.Error -> {
            val error = (usersState as State.Error<SlidingResponse>).message
            // Show error message
            Box(modifier = Modifier) {
                Text(
                    text = "Failed to fetch data: $error",
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .align(Alignment.Center)
                )
            }
        }

        else -> {

        }
    }

}

@Composable
fun SliderVertical(drawable: String, text: String) {
    Column( horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(5.dp).clip(shape = RoundedCornerShape(16.dp))
            .background(colorResource(id = R.color.transPrimary)).height(320.dp)) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(drawable)
                .crossfade(true)
                .build(),
            modifier = Modifier.fillMaxWidth().height(250.dp),
           // placeholder = painterResource(R.drawable.placeholder),
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium,
            color = Color.White,
            fontFamily = fonts,
            fontWeight =  FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp, top = 15.dp, bottom = 10.dp)

        )

    }

}

    @Composable
    @Preview
    fun ReportPreview() {
        NavigationSampleTheme {
            RecipeScreen()

        }

}