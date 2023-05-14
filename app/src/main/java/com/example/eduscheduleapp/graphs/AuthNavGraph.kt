package com.example.eduschedule.graphs

import android.util.Log
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.eduscheduleapp.graphs.Graph
import com.example.eduscheduleapp.presentation.login.LoginScreen
import com.example.eduscheduleapp.presentation.authcheck.AuthCheckScreen
import com.example.eduscheduleapp.presentation.screen.ScreenContent

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = AuthScreen.Login.route
    ) {
        composable(route = AuthScreen.Login.route) {
            LoginScreen(navController)
        }
        composable(route = AuthScreen.AuthCheck.route + "/{login}/{password}"){
            AuthCheckScreen(
                navController
                /*successful = {
                    Log.d("ABOBA", "AAAAAAAAAAA")
                    //navController.popBackStack()
                    navController.navigate(Graph.HOME)
                }*/
            )
        }
        composable(route = "penis"){
            Surface() {
                Text(text = "piska")
            }
        }
    }
}

sealed class AuthScreen(val route: String) {
    object Login : AuthScreen(route = "LOGIN")

    object AuthCheck : AuthScreen(route = "AUTHCHECK")
}