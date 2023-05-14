package com.example.eduscheduleapp.presentation.grades

import android.graphics.drawable.shapes.Shape
import android.widget.GridLayout
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.eduscheduleapp.common.DATA
import com.example.eduscheduleapp.data.remote.dto.Mark
import com.example.eduscheduleapp.ui.theme.EduScheduleAppTheme
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.ceil

@Composable
fun GradesScreen(
    viewModel: GradesViewModel = hiltViewModel()
){
    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
        if(!state.totalList.isNullOrEmpty()) {
            Column {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp, horizontal = 10.dp)
                ) {
                    Text(text = DATA.person.name, fontSize = 25.sp, fontWeight = FontWeight.Bold)
                    Text(text = "Класс: ${DATA.person.group}", fontSize = 20.sp)
                    Text(
                        text = "Средний балл: ${roundOffDecimal(calculateGPA(state.totalList ?: mutableListOf<Mark>()))}",
                        fontSize = 20.sp
                    )
                }
                Divider(
                    modifier = Modifier.padding(vertical = 5.dp),
                    color = Color.Black,
                    thickness = 2.dp
                )
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 10.dp)
                ) {
                    itemsIndexed(
                        listOf(
                            "Белорусский язык",
                            "Белорусская литература",
                            "Русский язык",
                            "Русская литература",
                            "Иностранный язык",
                            "Математика",
                            "Информатика",
                            "История",
                            "Обществоведение",
                            "География",
                            "Биология",
                            "Физика",
                            "Астрономия",
                            "Химия",
                        )
                    ) { _, item ->
                        var list = mutableListOf<Mark>()
                        when (item) {
                            "Белорусский язык" -> list = state.belLangList ?: mutableListOf<Mark>()
                            "Белорусская литература" -> list =
                                state.belLitList ?: mutableListOf<Mark>()
                            "Русский язык" -> list = state.rusLangList ?: mutableListOf<Mark>()
                            "Русская литература" -> list = state.rusLitList ?: mutableListOf<Mark>()
                            "Иностранный язык" -> list = state.engLangList ?: mutableListOf<Mark>()
                            "Математика" -> list = state.mathList ?: mutableListOf<Mark>()
                            "Информатика" -> list = state.infList ?: mutableListOf<Mark>()
                            "История" -> list = state.historyList ?: mutableListOf<Mark>()
                            "Обществоведение" -> list = state.societyList ?: mutableListOf<Mark>()
                            "География" -> list = state.geographyList ?: mutableListOf<Mark>()
                            "Биология" -> list = state.biologyList ?: mutableListOf<Mark>()
                            "Физика" -> list = state.physicsList ?: mutableListOf<Mark>()
                            "Астрономия" -> list = state.astronomyList ?: mutableListOf<Mark>()
                            "Химия" -> list = state.chemistryList ?: mutableListOf<Mark>()
                        }
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            Text(
                                text = item,
                                fontSize = 25.sp,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.padding(vertical = 5.dp)
                            )
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .border(
                                        2.dp,
                                        color = Color.Gray,
                                        shape = RoundedCornerShape(7.dp)
                                    )
                            ) {
                                if (list.isEmpty()) {
                                    Text(
                                        text = "Отметок нет",
                                        modifier = Modifier.padding(all = 10.dp),
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 20.sp
                                    )
                                } else {
                                    Text(
                                        text = if (list.isEmpty()) "Отметок нет" else listTostring(
                                            list
                                        ),
                                        modifier = Modifier.padding(all = 10.dp),
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 20.sp
                                    )
                                    Divider(color = Color.Gray, thickness = 2.dp)
                                    Text(
                                        text = "Средний балл: ${roundOffDecimal(calculateGPA(list))}",
                                        modifier = Modifier.padding(all = 10.dp),
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 20.sp
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(25.dp))
                    }
                    item { Spacer(modifier = Modifier.padding(vertical = 20.dp)) }
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

fun listTostring(list : MutableList<Mark>) : String{
    var str = ""
    list.forEach {
        str += it.value.toString() + " "
    }
    return str
}

fun calculateGPA(list : MutableList<Mark>) : Double{
    var GPA = 0.0
    var count = true
    list.forEach {
        if(count) GPA = it.value.toDouble()
        else GPA = (GPA + it.value) / 2
        count = false
    }
    return GPA
}

fun roundOffDecimal(number: Double): Double? {
    val df = DecimalFormat("#.##")
    df.roundingMode = RoundingMode.CEILING
    return df.format(number).toDouble()
}

@Composable
fun LazyGridTest(){
    LazyVerticalGrid(
        columns = GridCells.Fixed(5),
        content = {
            item { Text(text = "popa") }
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Preview(){
    EduScheduleAppTheme {
        //GradesScreen("", 0)
    }
}


