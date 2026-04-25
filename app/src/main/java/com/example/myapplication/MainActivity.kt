package com.example.myapplication
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme

// ENUMS
enum class Pantalla {
    LOGIN,
    REGISTRO,
    OFERTA,
    CONTENIDO,
    CUESTIONARIOS,
    TAREAS,
    CERRAR
}

enum class Seccion {
    PERFIL,
    FOTOS,
    VIDEO,
    WEB,
    BOTONES
}
// MAIN ACTIVITY

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyApplicationTheme {

                var pantalla by remember { mutableStateOf(Pantalla.LOGIN) }

                when (pantalla) {

                    Pantalla.LOGIN ->
                        Login { pantalla = Pantalla.REGISTRO }

                    Pantalla.REGISTRO ->
                        Registro { pantalla = Pantalla.OFERTA }

                    Pantalla.OFERTA ->
                        Oferta { pantalla = Pantalla.CONTENIDO }

                    Pantalla.CONTENIDO ->
                        Contenido(
                            irCuestionarios = { pantalla = Pantalla.CUESTIONARIOS },
                            irTareas = { pantalla = Pantalla.TAREAS },
                            cerrar = { pantalla = Pantalla.CERRAR }
                        )

                    Pantalla.CUESTIONARIOS ->
                        Cuestionarios { pantalla = Pantalla.CONTENIDO }

                    Pantalla.TAREAS ->
                        Tareas { pantalla = Pantalla.CONTENIDO }

                    Pantalla.CERRAR ->
                        CerrarSesion { pantalla = Pantalla.LOGIN }
                }
            }
        }
    }
}
// PANTALLAS

@Composable
fun Login(onContinuar: () -> Unit) {
    PantallaBase("Aula Virtual") {
        Text("Correo electrónico")
        Text("Contraseña")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onContinuar) {
            Text("Iniciar sesión")
        }
    }
}

@Composable
fun Registro(onContinuar: () -> Unit) {
    PantallaBase("Crear nueva cuenta") {
        Text("Nombre")
        Text("Correo electrónico")
        Text("Contraseña")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onContinuar) {
            Text("Registrarse")
        }
    }
}

@Composable
fun Oferta(onContinuar: () -> Unit) {
    PantallaBase("Oferta Académica") {
        Text("• Programación")
        Text("• Bases de Datos")
        Text("• Desarrollo Web")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onContinuar) {
            Text("Ingresar al Aula")
        }
    }
}
// CONTENIDO PRINCIPAL DEL AULA VIRTUAL

@Composable
fun Contenido(
    irCuestionarios: () -> Unit,
    irTareas: () -> Unit,
    cerrar: () -> Unit
) {

    var seccionSeleccionada by remember { mutableStateOf(Seccion.PERFIL) }

    Column(modifier = Modifier.padding(16.dp)) {

        Text("Panel Principal", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(16.dp))

        Row {

            // MENÚ IZQUIERDO
            Column(modifier = Modifier.weight(1f)) {

                Button(onClick = { seccionSeleccionada = Seccion.PERFIL }) {
                    Text("Perfil")
                }

                Button(onClick = { seccionSeleccionada = Seccion.FOTOS }) {
                    Text("Fotos")
                }

                Button(onClick = { seccionSeleccionada = Seccion.VIDEO }) {
                    Text("Video")
                }

                Button(onClick = { seccionSeleccionada = Seccion.WEB }) {
                    Text("Web")
                }

                Button(onClick = { seccionSeleccionada = Seccion.BOTONES }) {
                    Text("Botones")
                }

                Spacer(modifier = Modifier.height(20.dp))

                Button(onClick = irCuestionarios) {
                    Text("Cuestionarios")
                }

                Button(onClick = irTareas) {
                    Text("Tareas")
                }

                Button(onClick = cerrar) {
                    Text("Cerrar sesión")
                }
            }
            // CONTENIDO DERECHO DE LA APP
            Column(
                modifier = Modifier
                    .weight(2f)
                    .padding(start = 16.dp)
            ) {

                when (seccionSeleccionada) {
                    Seccion.PERFIL -> Perfil()
                    Seccion.FOTOS -> Fotos()
                    Seccion.VIDEO -> Video()
                    Seccion.WEB -> Web()
                    Seccion.BOTONES -> Botones()
                }
            }
        }
    }
}
// SECCIONES

@Composable
fun Perfil() {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        Text("Nombre:Maria paula Martinez ")
        Text("Estudios: Ingeniería de computacion")
        Text("Experiencia: Desarrollo App Android")
        Text("Habilidades:Analista de datos")
    }
}

@Composable
fun Fotos() {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        Text("Imagen 1 - Aula")
        Text("Imagen 2 - Clases")
        Text("Imagen 3 - Biblioteca")
    }
}

@Composable
fun Video() {
    Text("Video educativo con controles")
}

@Composable
fun Web() {
    var url by remember { mutableStateOf("") }

    Column {
        TextField(
            value = url,
            onValueChange = { url = it },
            label = { Text("Ingrese URL") }
        )
        Text("Página cargada: $url")
    }
}

@Composable
fun Botones() {
    Column {
        Button(onClick = {}) { Text("Aceptar") }
        Button(onClick = {}) { Text("Cancelar") }
    }
}

//OTRAS PANTALLAS SECCION CUESTIONARIOS
@Composable
fun Cuestionarios(onVolver: () -> Unit) {
    PantallaBase("Cuestionarios") {
        Text("Pregunta 1")
        Text("Pregunta 2")
        Text("Pregunta 3")
        Button(onClick = onVolver) {
            Text("Volver")
        }
    }
}

@Composable
fun Tareas(onVolver: () -> Unit) {
    PantallaBase("Tareas") {
        Text("Tarea 1")
        Text("Tarea 2")
        Button(onClick = onVolver) {
            Text("Volver")
        }
    }
}

@Composable
fun CerrarSesion(onSalir: () -> Unit) {
    PantallaBase("Cerrar sesión") {
        Button(onClick = onSalir) {
            Text("Volver al inicio")
        }
    }
}

/*BASE*/

@Composable
fun PantallaBase(
    titulo: String,
    contenido: @Composable () -> Unit
) {
    Column(modifier = Modifier.padding(24.dp)) {
        Text(titulo, style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        contenido()
    }
}
