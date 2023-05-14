package com.example.eduscheduleapp.presentation.rating

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.eduscheduleapp.data.remote.dto.Mark
import com.example.eduscheduleapp.ui.theme.EduScheduleAppTheme

@Composable
fun RatingScreen(
    viewModel: RatingViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val list = state.students.sortedByDescending { it.GPA }
    Box(modifier = Modifier.fillMaxSize()){
        if(!state.students.isNullOrEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all = 10.dp)
            ) {
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(vertical = 2.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Divider(
                            modifier = Modifier.weight(0.2f),
                            thickness = 0.dp,
                            color = Color.White
                        )
                        Text(text = "ФИО", modifier = Modifier.weight(0.47f), fontSize = 18.sp)
                        Text(
                            text = "Класс",
                            modifier = Modifier.weight(0.45f),
                            fontSize = 18.sp
                        )
                        Text(
                            text = "Ср. балл",
                            fontSize = 18.sp,
                            modifier = Modifier.weight(0.3f)
                        )
                    }
                    Divider(color = Color.LightGray, thickness = 2.dp, modifier = Modifier.padding(2.dp))
                }
                itemsIndexed(
                    list
                ) { index, item ->
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(vertical = 2.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = (index + 1).toString() + ". ", fontSize = 18.sp)
                        Text(text = item.name, modifier = Modifier.weight(1f), fontSize = 18.sp)
                        Divider(
                            modifier = Modifier.width(40.dp),
                            thickness = 0.dp,
                            color = Color.White
                        )
                        Text(
                            text = item.group_name,
                            modifier = Modifier.weight(1f),
                            fontSize = 18.sp
                        )
                        Text(
                            text = item.GPA.toString(),
                            fontSize = 18.sp,
                            modifier = Modifier.weight(0.4f)
                        )
                    }
                    Divider(
                        color = Color.LightGray, thickness = 1.dp, modifier = Modifier.clip(
                            RoundedCornerShape(1.dp)
                        )
                    )
                }
            }
        }
        if(state.error.isNotBlank()) {
            Text(
                text = state.error,
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

@Preview(showSystemUi = true)
@Composable
fun RatingPreview() {
    EduScheduleAppTheme {
        //RatingScreen()
    }
}