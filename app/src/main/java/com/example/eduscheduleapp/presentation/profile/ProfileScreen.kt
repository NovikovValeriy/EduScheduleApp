package com.example.eduscheduleapp.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eduscheduleapp.ui.theme.Shapes
import com.example.eduscheduleapp.R
import com.example.eduscheduleapp.ui.theme.EduScheduleAppTheme

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.student),
            contentDescription = null,
            modifier = Modifier
                .padding(all = 10.dp)
                .border(2.dp, color = Color.Gray, shape = CircleShape)
                .clip(CircleShape)
        )
        Text(
            text = /*DATA.person.login*/"",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = /*DATA.person.group*/"",
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun ProfilePreview() {
    EduScheduleAppTheme {
        ProfileScreen()
    }
}