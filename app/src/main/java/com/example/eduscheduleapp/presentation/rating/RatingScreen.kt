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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.eduscheduleapp.R
import com.example.eduscheduleapp.common.DATA
import com.example.eduscheduleapp.data.remote.dto.Mark
import com.example.eduscheduleapp.ui.theme.EduScheduleAppTheme

@Composable
fun RatingScreen(
    viewModel: RatingViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    var list = state.students.sortedByDescending { it.GPA }
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
                        Text(text = stringResource(id = R.string.FIO_text), modifier = Modifier.weight(0.47f), fontSize = 18.sp)
                        Text(
                            text = stringResource(id = R.string.group_text),
                            modifier = Modifier.weight(0.4f),
                            fontSize = 18.sp
                        )
                        Text(
                            text = stringResource(id = R.string.GPA_rating_text),
                            fontSize = 18.sp,
                            modifier = Modifier.weight(0.3f)
                        )
                    }
                    Divider(color = Color.LightGray, thickness = 2.dp, modifier = Modifier.padding(2.dp))
                }
                itemsIndexed(
                    list
                ) { index, item ->
                    var clr = Color.Black
                    var wght = FontWeight.Normal
                    if(item.name == DATA.person.name) {
                        clr = Color.Red
                        wght = FontWeight.SemiBold
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(vertical = 2.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = (index + 1).toString() + ". ", fontSize = 18.sp, color = clr, fontWeight = wght)
                        Text(text = item.name, modifier = Modifier.weight(0.6f), fontSize = 18.sp, color = clr, fontWeight = wght)
                        Divider(
                            modifier = Modifier.width(40.dp),
                            thickness = 0.dp,
                            color = Color.White
                        )
                        Text(
                            text = item.group_name,
                            modifier = Modifier.weight(0.5f),
                            fontSize = 18.sp,
                            color = clr,
                            fontWeight = wght
                        )
                        Text(
                            text = if(item.GPA.toString() != "0.0") {
                                item.GPA.toString()
                            }
                            else{
                                stringResource(id = R.string.no_grades_text)
                            },
                            fontSize = 18.sp,
                            modifier = Modifier.weight(0.4f),
                            textAlign = TextAlign.Center,
                            color = clr,
                            fontWeight = wght
                        )
                    }
                    Divider(
                        color = Color.LightGray, thickness = 1.dp, modifier = Modifier.clip(
                            RoundedCornerShape(1.dp)
                        )
                    )
                }
                item {
                    Spacer(modifier = Modifier.padding(vertical = 25.dp))
                }
            }
        }
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

@Preview(showSystemUi = true)
@Composable
fun RatingPreview() {
    EduScheduleAppTheme {
        //RatingScreen()
    }
}