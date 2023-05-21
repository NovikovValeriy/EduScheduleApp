package com.example.eduscheduleapp.presentation.events


import android.os.Bundle
import android.graphics.drawable.shapes.Shape
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
import com.example.eduscheduleapp.R

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
                            Image(
                                painter = painterResource(id = R.drawable.gordon),
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(horizontal = 10.dp)
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
                            text = event.name,//"Идущий к реке",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.padding(horizontal = 10.dp)
                        )
                        Text(
                            text = event.description,//"Я в своем познании настолько преисполнился что я как будто бы уже сто триллионов миллиардов лет проживаю на триллионах и триллионах таких же планет, понимаешь как эта земля. Мне уже этот мир абсолютно понятен и я здесь ищу только покоя, умиротворения и вот этой гармонии от слияния бесконечного с вечным, созерцания того великого фрактального подобия и от вот этого вот замечательного всеединства существа (бесконечно-вечным). Куда не посмотри, хоть в глубь бесконечно малое, хоть в высь бесконечно большое, понимаешь. Вот ты мне опять со своими это там, иди суетись дальше это твое распределение, это твой путь и твой горизонт познаний, ощущений своей природы, но оно несоизмерим мелок по сравнению с моим, понимаешь, я как-будто бы уже глубокий старец, бессмертный, или там уже почти бессмертный который на этой планете с ее самого зарождения, еще когда только солнце только-только сформировалось как звезда, и вот это газо-пылевое облако сформировалось после взрыва солнца, когда оно вспыхнуло, как звезда, начал формировать эти планеты, понимаешь, я и на этой земле уже как-будто уже почти 5 миллиардов лет живу, ее знаю уже вдоль и поперек этот весь мир. А ты мне там про какие-то абстрактные понятия, мне пофиг, я иду как глубокий старец узривший вечностное, прикоснувшийся к божественному, сам стал богоподобно устремлен в бесконечное, и который умиротворение, гармонии, покоя и благодати, сокровенном блаженстве прибывает, вовлеченный во все и во вся, понимаешь?",
                            //text = item.description,
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


