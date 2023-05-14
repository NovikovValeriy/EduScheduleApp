package com.example.eduscheduleapp

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBar(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Schedule : BottomBar(
        route = "SCHEDULE",
        title = "Расписание",
        icon = Icons.Rounded.Menu
    )

    object Events : BottomBar(
        route = "EVENTS",
        title = "События",
        icon = Icons.Rounded.Language
    )

    object Grades : BottomBar(
        route = "GRADES",
        title = "Отметки",
        icon = Icons.Rounded.Home
    )

    object Profile : BottomBar(
        route = "PROFILE",
        title = "Профиль",
        icon = Icons.Rounded.Person
    )

    object Rating : BottomBar(
        route = "RATING",
        title = "Рейтинг",
        icon = Icons.Rounded.Star
    )
}