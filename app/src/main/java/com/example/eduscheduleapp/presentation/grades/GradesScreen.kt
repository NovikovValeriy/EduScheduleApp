package com.example.eduscheduleapp.presentation.grades

import android.graphics.drawable.shapes.Shape
import android.util.Log
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
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.ceil

@Composable
fun GradesScreen(
    viewModel: GradesViewModel = hiltViewModel()
){
    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
        if(state.student != null) {
            Column {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp, horizontal = 10.dp)
                ) {
                    Text(text = DATA.person.name, fontSize = 25.sp, fontWeight = FontWeight.Bold)
                    Text(text = stringResource(id = R.string.group_text) + ": ${DATA.person.group}", fontSize = 20.sp)
                    Text(
                        text = stringResource(id = R.string.GPA_text) + ": ${roundOffDecimal(calculateGPA(state.totalList ?: mutableListOf<Mark>()))}",
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
                        var itemText = ""
                        when (item) {
                            "Белорусский язык" -> {
                                list = state.belLangList ?: mutableListOf<Mark>()
                                itemText = stringResource(id = R.string.bel_lang_text)
                            }
                            "Белорусская литература" -> {
                                list = state.belLitList ?: mutableListOf<Mark>()
                                itemText = stringResource(id = R.string.bel_lit_text)
                            }
                            "Русский язык" -> {
                                list = state.rusLangList ?: mutableListOf<Mark>()
                                itemText = stringResource(id = R.string.rus_lang_text)
                            }
                            "Русская литература" -> {
                                list = state.rusLitList ?: mutableListOf<Mark>()
                                itemText = stringResource(id = R.string.rus_lit_text)
                            }
                            "Иностранный язык" -> {
                                list = state.engLangList ?: mutableListOf<Mark>()
                                itemText = stringResource(id = R.string.eng_lang_text)
                            }
                            "Математика" -> {
                                list = state.mathList ?: mutableListOf<Mark>()
                                itemText = stringResource(id = R.string.math_text)
                            }
                            "Информатика" -> {
                                list = state.infList ?: mutableListOf<Mark>()
                                itemText = stringResource(id = R.string.inf_text)
                            }
                            "История" -> {
                                list = state.historyList ?: mutableListOf<Mark>()
                                itemText = stringResource(id = R.string.history_text)
                            }
                            "Обществоведение" -> {
                                list = state.societyList ?: mutableListOf<Mark>()
                                itemText = stringResource(id = R.string.society_text)
                            }
                            "География" -> {
                                list = state.geographyList ?: mutableListOf<Mark>()
                                itemText = stringResource(id = R.string.geography_text)
                            }
                            "Биология" -> {
                                list = state.biologyList ?: mutableListOf<Mark>()
                                itemText = stringResource(id = R.string.biology_text)
                            }
                            "Физика" -> {
                                list = state.physicsList ?: mutableListOf<Mark>()
                                itemText = stringResource(id = R.string.physics_text)
                            }
                            "Астрономия" -> {
                                list = state.astronomyList ?: mutableListOf<Mark>()
                                itemText = stringResource(id = R.string.astronomy_text)
                            }
                            "Химия" -> {
                                list = state.chemistryList ?: mutableListOf<Mark>()
                                itemText = stringResource(id = R.string.chemistry_text)
                            }
                        }
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            Text(
                                text = itemText,
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
                                        text = stringResource(id = R.string.no_grades_text),
                                        modifier = Modifier.padding(all = 10.dp),
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 20.sp
                                    )
                                } else {
                                    Text(
                                        text = if (list.isEmpty()) stringResource(id = R.string.no_grades_text) else listTostring(
                                            list
                                        ),
                                        modifier = Modifier.padding(all = 10.dp),
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 20.sp
                                    )
                                    Divider(color = Color.Gray, thickness = 2.dp)
                                    Text(
                                        text = stringResource(id = R.string.GPA_text) + ": ${roundOffDecimal(calculateGPA(list))}",
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

fun listTostring(list : MutableList<Mark>) : String{
    var str = ""
    list.forEach {
        str += it.value.toString() + " "
    }
    return str
}

fun calculateGPA(list : MutableList<Mark>) : Double{
    var GPA = 0.0
    var count = 0
    if (list.isEmpty()) return 0.0
    list.forEach {
        GPA += it.value
        count++
    }
    return GPA / count
}

fun roundOffDecimal(number: Double): Double? {
    val df = DecimalFormat("#.##")
    df.roundingMode = RoundingMode.CEILING
    var check = df.format(number)
    check = check.replace(",", ".")
    return check.toDouble()
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


