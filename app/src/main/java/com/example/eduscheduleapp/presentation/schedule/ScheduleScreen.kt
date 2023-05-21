package com.example.eduscheduleapp.presentation.schedule

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
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
import com.example.eduscheduleapp.data.remote.dto.ScheduleSubject
import com.example.eduscheduleapp.ui.theme.EduScheduleAppTheme

@Composable
fun ScheduleScreen(
    viewModel: SheduleViewModel = hiltViewModel()
){
    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
        if(!state.schedule.isNullOrEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 10.dp)
            ) {
                itemsIndexed(
                    listOf("Понедельник", "Вторник", "Среда", "Четверг", "Пятница")
                ) { _, item ->
                    var list = mutableListOf<ScheduleSubject>()
                    var itemText = ""
                    when (item) {
                        "Понедельник" -> {
                            list = state.mondayList ?: mutableListOf<ScheduleSubject>()
                            itemText = stringResource(id = R.string.monday_text)
                        }
                        "Вторник" -> {
                            list = state.tuesdayList ?: mutableListOf<ScheduleSubject>()
                            itemText = stringResource(id = R.string.tuesday_text)
                        }
                        "Среда" -> {
                            list = state.wednesdayList ?: mutableListOf<ScheduleSubject>()
                            itemText = stringResource(id = R.string.wednesday_text)
                        }
                        "Четверг" -> {
                            list = state.thursdayList ?: mutableListOf<ScheduleSubject>()
                            itemText = stringResource(id = R.string.thursday_text)
                        }
                        "Пятница" -> {
                            list = state.fridayList ?: mutableListOf<ScheduleSubject>()
                            itemText = stringResource(id = R.string.friday_text)
                        }
                    }
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            text = itemText,
                            fontSize = 25.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(vertical = 5.dp)
                        )
                        if (list.isEmpty()) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(vertical = 10.dp)
                                    .border(
                                        width = 2.dp,
                                        color = Color.Gray,
                                        shape = RoundedCornerShape(10.dp)
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = stringResource(id = R.string.no_lessons_text),
                                    fontSize = 20.sp,
                                    modifier = Modifier.padding(vertical = 20.dp)
                                )
                            }
                        }
                        list.forEach {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(vertical = 10.dp)
                                    .border(
                                        width = 2.dp,
                                        color = Color.Gray,
                                        shape = RoundedCornerShape(10.dp)
                                    )
                            ) {
                                Text(
                                    text =
                                    when(it.class_name){
                                        "Белорусский язык" -> stringResource(id = R.string.bel_lang_text)
                                        "Белорусская литература" -> stringResource(id = R.string.bel_lit_text)
                                        "Русский язык" -> stringResource(id = R.string.rus_lang_text)
                                        "Русская литература" -> stringResource(id = R.string.rus_lit_text)
                                        "Иностранный язык" -> stringResource(id = R.string.eng_lang_text)
                                        "Математика" -> stringResource(id = R.string.math_text)
                                        "Информатика" -> stringResource(id = R.string.inf_text)
                                        "История" -> stringResource(id = R.string.history_text)
                                        "Обществоведение" -> stringResource(id = R.string.society_text)
                                        "География" -> stringResource(id = R.string.geography_text)
                                        "Биология" -> stringResource(id = R.string.biology_text)
                                        "Физика" -> stringResource(id = R.string.physics_text)
                                        "Астрономия" -> stringResource(id = R.string.astronomy_text)
                                        "Химия" -> stringResource(id = R.string.chemistry_text)
                                        else -> ""
                                    }
                                    /*it.class_name*/,
                                    modifier = Modifier.padding(all = 10.dp),
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Medium
                                )
                                Row(
                                    modifier = Modifier.fillMaxSize(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = "${stringResource(id = R.string.room_text)} ${it.classroom}",
                                        modifier = Modifier.padding(all = 10.dp)
                                    )
                                    Text(
                                        text = it.start_time + " - " + it.end_time,
                                        modifier = Modifier.padding(all = 10.dp)
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(25.dp))
                    }
                }
                item { Spacer(modifier = Modifier.padding(vertical = 20.dp)) }
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SchedulePreview(){
    EduScheduleAppTheme {
        ScheduleScreen()
    }
}