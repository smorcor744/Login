import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application


@Composable
@Preview
fun Usuario(usuario: String, OnUsuarioChange: (String) -> Unit) {
    OutlinedTextField(
        value = usuario,
        onValueChange = OnUsuarioChange,
        label = { Text("Usuario") }
    )
}


@Composable
@Preview
fun Password(password: String, OnPasswordChange: (String) -> Unit, passVisible: Boolean,  OnPasswordVisibleChange: (Boolean) -> Unit) {
    OutlinedTextField(
        value = password,
        onValueChange = OnPasswordChange,
        label = { Text(("Contraseña")) },
        visualTransformation = if (passVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            IconToggleButton(
                checked = passVisible,
                onCheckedChange = OnPasswordVisibleChange
            ) {
                Icon(
                    imageVector = if (passVisible) Icons.Default.VisibilityOff else  Icons.Default.Visibility,
                    contentDescription = null
                )
            }
        })
}


@Composable
@Preview
fun Boton(buttonEnabled: Boolean, OnBotonChange: () -> Unit) {
    Button(onClick = OnBotonChange,
        enabled = buttonEnabled)
    { Text(text = "Login") }
}


@Composable
@Preview
fun loginScreen() {
    var user by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val buttonEnabled = user.isNotEmpty() && password.isNotEmpty()

    MaterialTheme {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                5.dp, alignment = Alignment.CenterVertically),
            modifier = Modifier.fillMaxSize()
        ) {
            Usuario(user, ){user = it}
            var passVisible by remember { mutableStateOf(false) }

            Password(password,{password = it}, passVisible,{passVisible = it})
            Boton(buttonEnabled) { user = ""; password = "" }
        }
        }
    }


    fun main() = application {
        Window(onCloseRequest = ::exitApplication) {
            loginScreen()
        }
    }
