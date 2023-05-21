package com.example.eduscheduleapp.presentation.events


import android.os.Bundle
import android.graphics.drawable.shapes.Shape
import android.util.Log
import android.widget.GridLayout
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eduscheduleapp.ui.theme.EduScheduleAppTheme
import kotlin.math.ceil
import androidx.compose.foundation.Image
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.eduscheduleapp.R
import com.example.eduscheduleapp.common.Constants

@Composable
fun EventsScreen(
    viewModel: EventsViewModel = hiltViewModel()
){
    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp)
        ) {
            items(state.events){event ->
                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 10.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .border(2.dp, color = Color.Gray, shape = RoundedCornerShape(7.dp))
                    ) {
                        if(event.photo != null) {
                            AsyncImage(
                                model = Constants.BASE_URL + "api/v1/events/photo/${event.id}/",
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(all = 10.dp)
                                    .fillMaxSize()
                                    .clip(RoundedCornerShape(7.dp)),
                                contentScale = ContentScale.Crop
                            )
                        }
                        Text(
                            text = event.date,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp),
                            fontStyle = FontStyle.Italic,
                            fontWeight = FontWeight.Medium
                        )
                        Text(
                            text = event.name,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.padding(horizontal = 10.dp)
                        )
                        Text(
                            text = event.description,
                            modifier = Modifier.padding(all = 10.dp)
                        )
                    }
                }
                //Spacer(modifier = Modifier.height(25.dp))
            }
            item{Spacer(modifier = Modifier.padding(vertical = 25.dp))}
        }
        Spacer(modifier = Modifier.padding(vertical = 20.dp))
        if(state.error.isNotBlank()) {
            Text(
                text = when(state.error){
                    "1" -> stringResource(id = R.string.server_error_text)
                    "2" -> stringResource(id = R.string.connection_error_text)
                    else -> ""
                },
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if(state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EventsPreview(){
    EduScheduleAppTheme {
        EventsScreen()
    }
}


