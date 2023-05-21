package com.example.eduscheduleapp.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.eduscheduleapp.BottomBar
import com.example.eduscheduleapp.presentation.events.EventsScreen
import com.example.eduscheduleapp.presentation.grades.GradesScreen
import com.example.eduscheduleapp.presentation.rating.RatingScreen
import com.example.eduscheduleapp.presentation.schedule.ScheduleScreen

@Composable
fun HomeNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = BottomBar.Schedule.route
    ) {
        composable(route = BottomBar.Schedule.route) {
            ScheduleScreen()
        }
        composable(route = BottomBar.Grades.route) {
            GradesScreen()
        }
        composable(route = BottomBar.Events.route){
            EventsScreen()
        }
        composable(route = BottomBar.Rating.route){
            RatingScreen()
        }
    }
}

/*fun NavGraphBuilder.detailsNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.DETAILS,
        startDestination = DetailsScreen.Information.route
    ) {
        composable(route = DetailsScreen.Information.route) {
            ScreenContent(name = DetailsScreen.Information.route) {
                navController.navigate(DetailsScreen.Overview.route)
            }
        }
        composable(route = DetailsScreen.Overview.route) {
            ScreenContent(name = DetailsScreen.Overview.route) {
                navController.popBackStack(
                    route = DetailsScreen.Information.route,
                    inclusive = false
                )
            }
        }
    }
}

sealed class DetailsScreen(val route: String) {
    object Information : DetailsScreen(route = "INFORMATION")
    object Overview : DetailsScreen(route = "OVERVIEW")
}*/