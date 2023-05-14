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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
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
                    when (item) {
                        "Понедельник" -> list = state.mondayList ?: mutableListOf<ScheduleSubject>()
                        "Вторник" -> list = state.tuesdayList ?: mutableListOf<ScheduleSubject>()
                        "Среда" -> list = state.wednesdayList ?: mutableListOf<ScheduleSubject>()
                        "Четверг" -> list = state.thursdayList ?: mutableListOf<ScheduleSubject>()
                        "Пятница" -> list = state.fridayList ?: mutableListOf<ScheduleSubject>()
                    }
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            text = item,
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
                                    text = "Занятий нет",
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
                                    text = it.class_name,
                                    modifier = Modifier.padding(all = 10.dp),
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Medium
                                )
                                Row(
                                    modifier = Modifier.fillMaxSize(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = "Кабинет ${it.classroom}",
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SchedulePreview(){
    EduScheduleAppTheme {
        ScheduleScreen()
    }
}

sealed class SubjectField(val name: String?, val room: Int = 0, val teacherName: String?){

    object math: SubjectField(
        name = "Математика",
        room = 112,
        teacherName = "Копосова П.Г."
    )

    object bel: SubjectField(
        name = "Белорусский язык",
        room = 310,
        teacherName = "Метельский А.А."
    )

    object rus: SubjectField(
        name = "Русский язык",
        room = 220,
        teacherName = "Чаевская Т.А."
    )

    object fiz: SubjectField(
        name = "Физика",
        room = 106,
        teacherName = "Храмович Е.М."
    )

    object ist: SubjectField(
        name = "История",
        room = 104,
        teacherName = "Вашкевич И.В."
    )

    object bio: SubjectField(
        name = "Биология",
        room = 420,
        teacherName = "Перепелко А.С."
    )
}