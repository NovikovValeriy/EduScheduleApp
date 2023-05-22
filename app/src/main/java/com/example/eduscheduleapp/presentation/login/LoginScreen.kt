package com.example.eduscheduleapp.presentation.login

import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eduscheduleapp.ui.theme.EduScheduleAppTheme
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.material.IconButton
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.eduschedule.graphs.AuthScreen
import com.example.eduscheduleapp.R
import com.example.eduscheduleapp.common.DATA
import com.example.eduscheduleapp.data.remote.dto.AuthRequest
import com.example.eduscheduleapp.graphs.Graph
import kotlinx.coroutines.runBlocking

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    var loginText by rememberSaveable { mutableStateOf("") }
    var passwordText by rememberSaveable { mutableStateOf("") }
    var passwordHidden by rememberSaveable { mutableStateOf(true) }

    LaunchedEffect(state.loginData) {
        state.loginData?.let {
            DATA.person = state.loginData!!
            navController.popBackStack()
            navController.navigate(Graph.HOME)
        }
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(100.dp))
            /*Text(
            text = "EduSchedule",
            fontWeight = FontWeight.Bold,
            fontSize = 40.sp
        )*/
            Image(
                painter = painterResource(id = R.drawable.loginlogo),
                contentDescription = null,
                modifier = Modifier.size(300.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Column() {
                OutlinedTextField(
                    modifier = Modifier
                        .width(300.dp),
                    value = loginText,
                    onValueChange = { loginText = it },
                    label = { Text(stringResource(id = R.string.login_text)) },
                    singleLine = true
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Column() {
                OutlinedTextField(
                    modifier = Modifier
                        .width(300.dp),
                    value = passwordText,
                    onValueChange = { passwordText = it },
                    label = { Text(stringResource(id = R.string.password_text)) },
                    singleLine = true,
                    visualTransformation =
                    if (passwordHidden) PasswordVisualTransformation() else VisualTransformation.None,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    trailingIcon = {
                        IconButton(onClick = { passwordHidden = !passwordHidden }) {
                            val visibilityIcon =
                                if (passwordHidden) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                            Icon(imageVector = visibilityIcon, contentDescription = null)
                        }
                    }
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Column(
                modifier = Modifier.width(300.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = {
                        if (loginText.isNotEmpty() && passwordText.isNotEmpty()) {
                            viewModel.authorization(AuthRequest(loginText, passwordText))
                        }
                    },
                    modifier = Modifier.width(300.dp)
                ) {
                    Text(text = stringResource(id = R.string.button_text))
                }
                if (state.error.isNotBlank()) {
                    Text(
                        text = when (state.error) {
                            "1" -> stringResource(id = R.string.incorrect_credentials_text)
                            "2" -> stringResource(id = R.string.connection_error_text)
                            else -> ""
                        },
                        modifier = Modifier.width(300.dp),
                        textAlign = TextAlign.Center
                    )
                } else if (state.isLoading) {
                    Text(text = stringResource(id = R.string.loading_text))
                } else {
                    Text(
                        text = ""
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewFunction() {
    EduScheduleAppTheme() {
        LoginScreen(navController = NavController(context = LocalContext.current))
    }
}