package com.cognizant.ortegapatricia.brownbagsession_uitesting.jetpackcompose

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cognizant.ortegapatricia.brownbagsession_uitesting.data.LoginDataSource
import com.cognizant.ortegapatricia.brownbagsession_uitesting.data.LoginRepository
import com.cognizant.ortegapatricia.brownbagsession_uitesting.utils.TopAppBarWithBackButton
import com.cognizant.ortegapatricia.brownbagsession_uitesting.viewmodel.LoginViewModel
import com.cognizant.ortegapatricia.brownbagsession_uitesting.viewmodel.LoginViewModelFactory

class LoginJetpackComposeActivity : ComponentActivity() {

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        var showLoginFailedDialog by mutableStateOf(false)
        loginViewModel = ViewModelProvider(this, LoginViewModelFactory())[LoginViewModel::class.java]

        setContent {
            var isDataValid by remember { mutableStateOf(false) }

            loginViewModel.loginFormState.observe(this, Observer {
                val loginState = it ?: return@Observer
                isDataValid = loginState.isDataValid
            })

            Column {
                TopAppBarWithBackButton(title = "Jetpack Compose Layout") {
                    onBackPressedDispatcher.onBackPressed()
                }
                LoginJetpackComposeView(
                    loginViewModel = loginViewModel,
                    isDataValid = isDataValid
                )
            }

            if (showLoginFailedDialog) {
                LoginFailedDialog(
                    title = "Login Failed",
                    message = "Username or password does not match",
                    onDismiss = { showLoginFailedDialog = false }
                )
            }
        }

        loginViewModel.loginResult.observe(this, Observer {
            val loginResult = it ?: return@Observer

            if (loginResult.error != null) {
                showLoginFailedDialog = true
            }
            if (loginResult.success != null) {
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            }
        })
    }
}

@Composable
fun LoginJetpackComposeView(
    loginViewModel: LoginViewModel,
    isDataValid: Boolean
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 48.dp, start = 28.dp, end = 28.dp)
    ) {
        OutlinedTextField(
            value = username,
            onValueChange = {
                username = it
                loginViewModel.loginDataChanged(username, password)
            },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(28.dp))
        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                loginViewModel.loginDataChanged(username, password)
            },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = { loginViewModel.login(username, password) },
            enabled = isDataValid,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Login")
        }
    }
}

@Composable
fun LoginFailedDialog(
    title: String,
    message: String,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(text = title)
        },
        text = {
            Text(text = message)
        },
        confirmButton = {
            Button(onClick = onDismiss) {
                Text("OK")
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun LoginJetpackComposePreview() {
    val mockLoginViewModel = LoginViewModel(LoginRepository(LoginDataSource()))
    LoginJetpackComposeView(
        loginViewModel = mockLoginViewModel,
        isDataValid = true
    )
}